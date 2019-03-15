package com.qub.Technopoly.board;

import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.config.FieldConfig;
import com.qub.Technopoly.tile.FreeParking;
import com.qub.Technopoly.tile.Property;
import com.qub.Technopoly.tile.Start;
import com.qub.Technopoly.tile.Tile;
import com.qub.Technopoly.util.CircularBuffer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.qub.Technopoly.config.Config.getConfig;
import static com.qub.Technopoly.io.IOHelper.getOutputSource;

/**
 * Representation of a game board containing all game elements
 */
public class Board {

    private CircularBuffer<Actor> actorQueue =
        new CircularBuffer<>(Actor.class, getConfig().getPlayerConfig().getMaxPlayers());

    private Map<Actor, Integer> currentActorPositions = new HashMap<>();
    private Map<Actor, Integer> nextActorPositions = new HashMap<>();

    private CircularBuffer<Tile> tiles;

    public Board() {
        var propertyConfigs =
            Arrays.stream(getConfig().getFieldConfigs()).map(FieldConfig::getPropertyConfigs)
                .flatMap(Stream::of);
        var properties = propertyConfigs.map(Property::new).collect(Collectors.toList());

        tiles = new CircularBuffer<>(Tile.class, properties.size() + 2);
        tiles.add(new Start(getConfig().getStartConfig()));

        for (int i = 0; i < properties.size(); i++) {
            var property = properties.get(i);
            tiles.add(property);

            if (i == getConfig().getFreeParkingConfig().getTilePosition()) {
                tiles.add(new FreeParking(getConfig().getFreeParkingConfig()));
            }
        }
    }

    /**
     * Add a new {@link Actor} to the board
     *
     * @param actor The actor to add
     */
    public void addActor(Actor actor) {
        actorQueue.add(actor);
        currentActorPositions.put(actor, 0);
        nextActorPositions.put(actor, 1);
    }

    /**
     * Add an array of {@link Actor} to the board
     *
     * @param actors The actors to add
     */
    public void addActors(Actor[] actors) {
        actorQueue = new CircularBuffer<>(Actor.class, actors.length);
        for (var i = 0; i < actors.length; i++) {
            addActor(actors[i]);
        }
    }

    /**
     * Move an Actor on the board
     *
     * @param actor The {@link Actor} to mvoe
     * @param steps How many steps the actor should move
     */
    public void moveActor(Actor actor, int steps) {

        tiles.setCurrentPosition(nextActorPositions.get(actor));

        for (var i = 0; i < steps - 1; i++) {
            var nextTile = tiles.getNext();
            nextTile.onPass(actor);
        }
        var lastTile = tiles.getNext();
        lastTile.onPass(actor);
        var actionCategory = lastTile.onLand(actor, this);

        nextActorPositions.replace(actor, tiles.getCurrentPosition());
        currentActorPositions.replace(actor, tiles.getPreviousPosition());

        if (actionCategory != null) {
            actionCategory.describe();
            while (!actionCategory.execute())
                ;
        } else {
            getOutputSource().writeTitle("You landed on " + lastTile.getName() + "!");
        }
    }

    /**
     * Gets the next actor in the queue
     *
     * @return The next actor in the queue
     */
    public Actor getNextActor() {
        return actorQueue.getNext();
    }

    /**
     * Gets the board {@link Actor} capacity
     *
     * @return The actor capacity of the board
     */
    public int getBoardActorCapacity() {
        return actorQueue.length;
    }

    /**
     * Gets the underlying board {@link Tile} array
     *
     * @return All the tiles that make up the board
     */
    public Tile[] getTiles() {
        return tiles.getBuffer();
    }

    /**
     * Helper method to get all {@link Actor}s at a specific {@link Tile}
     *
     * @param tile The tile to get actors from
     * @return All the actors on the tile, or if there are none, a null or empty array
     */
    public Actor[] getActorsAtTile(Tile tile) {
        var tiles = Arrays.asList(getTiles());
        var tileIndex = tiles.indexOf(tile);
        return nextActorPositions.keySet().stream()
            .filter(a -> currentActorPositions.get(a) == tileIndex).collect(Collectors.toList())
            .toArray(Actor[]::new);
    }

    /**
     * Get all the actors in the game
     *
     * @return All the actors currently on the board
     */
    public Actor[] getActors() {
        return actorQueue.getBuffer();
    }

    /**
     * Gets the actors with the most amount of money
     *
     * @return The actors with the most amount of money
     */
    public List<Actor> getActorsWithMostMoney() {
        var highestScore =
            Arrays.stream(getActors()).mapToInt(a -> a.getInventory().getCurrentBalance()).max()
                .orElse(-1);

        var winners = Arrays.stream(getActors())
            .filter(a -> a.getInventory().getCurrentBalance() == highestScore)
            .collect(Collectors.toList());

        return winners;
    }
}
