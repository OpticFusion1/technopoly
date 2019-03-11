package com.qub.Technopoly.board;

import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.config.FieldConfig;
import com.qub.Technopoly.tile.Property;
import com.qub.Technopoly.tile.Start;
import com.qub.Technopoly.tile.Tile;
import com.qub.Technopoly.util.CircularBuffer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.qub.Technopoly.io.IOHelper.getOutputSource;

/**
 * Representation of a game board containing all game elements
 */
public class Board {

    private CircularBuffer<Actor> actorQueue =
        new CircularBuffer<>(Actor.class, Config.getConfig().getPlayerConfig().getMaxPlayers());

    private Map<Actor, Integer> actorPositions = new HashMap<>();

    private CircularBuffer<Tile> tiles;

    public Board() {
        var propertyConfigs =
            Arrays.stream(Config.getConfig().getFieldConfigs()).map(FieldConfig::getPropertyConfigs)
                .flatMap(Stream::of);
        var properties = propertyConfigs.map(Property::new).collect(Collectors.toList());

        tiles = new CircularBuffer<>(Tile.class, properties.size() + 1);
        tiles.add(new Start(Config.getConfig().getStartConfig()));

        for (var property : properties) {
            tiles.add(property);
        }
    }

    /**
     * Add a new {@link Actor} to the board
     * @param actor The actor to add
     */
    public void addActor(Actor actor) {
        actorQueue.add(actor);
        actorPositions.put(actor, -1);
    }

    /**
     * Add an array of {@link Actor} to the board
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
     * @param actor The {@link Actor} to mvoe
     * @param steps How many steps the actor should move
     */
    public void moveActor(Actor actor, int steps) {
        tiles.setCurrentPosition(actorPositions.get(actor));

        for (var i = 0; i < steps - 1; i++) {
            var nextTile = tiles.getNext();
            nextTile.onPass(actor);
        }
        var lastTile = tiles.getNext();
        var actionCategory = lastTile.onLand(actor, this);

        actorPositions.replace(actor, tiles.getCurrentPosition());

        if (actionCategory != null) {
            actionCategory.describe();
            while (!actionCategory.execute());
        } else {
            getOutputSource().writeTitle("You landed on " + lastTile.getName() + "!");
        }
    }

    /**
     * Gets the next actor in the queue
     * @return
     */
    public Actor getNextActor() {
        return actorQueue.getNext();
    }

    /**
     * Gets the board {@link Actor} capacity
     * @return
     */
    public int getBoardActorCapacity() {
        return actorQueue.length;
    }

    /**
     * Gets the underlying board {@link Tile} array
     * @return
     */
    public Tile[] getTiles() {
        return tiles.getBuffer();
    }

    /**
     * Helper method to get all {@link Actor}s at a specific {@link Tile}
     * @param tile The tile to get actors from
     * @return All the actors on the tile, or if there are none, a null or empty array
     */
    public Actor[] getActorsAtTile(Tile tile) {
        var tiles = Arrays.asList(getTiles());
        var tileIndex = tiles.indexOf(tile);
        return actorPositions.keySet().stream().filter(a -> actorPositions.get(a) == tileIndex)
            .collect(Collectors.toList()).toArray(Actor[]::new);
    }

    /**
     * Get all the actors in the game
     * @return
     */
    public Actor[] getActors(){
        return actorQueue.getBuffer();
    }
}
