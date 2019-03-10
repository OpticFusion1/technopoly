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

    public void addActor(Actor actor) {
        actorQueue.add(actor);
        actorPositions.put(actor, 0);
    }

    public void addActors(Actor[] actors) {
        actorQueue = new CircularBuffer<>(Actor.class, actors.length);
        for (var i = 0; i < actors.length; i++) {
            addActor(actors[i]);
        }
    }

    public void moveActor(Actor actor, int steps) {
        tiles.setCurrentPosition(actorPositions.get(actor));

        // Skip the current tile.
        tiles.getNext();

        for (var i = 0; i < steps - 1; i++) {
            var nextTile = tiles.getNext();
            nextTile.onPass(actor);
        }
        var lastTile = tiles.getNext();
        var actionCategory = lastTile.onLand(actor);

        actorPositions.replace(actor, tiles.getCurrentPosition());

        if (actionCategory != null) {
            actionCategory.describe();
            while (!actionCategory.execute())
                ;
        } else {
            getOutputSource().writeTitle("You landed on " + lastTile.getName() + "!");
        }
    }

    public Actor getNextActor() {
        return actorQueue.getNext();
    }

    public int getBoardActorCapacity() {
        return actorQueue.length;
    }

    public Tile[] getTiles() {
        return tiles.getBuffer();
    }

    public Actor[] getActorsAtTile(Tile tile) {
        var tiles = Arrays.asList(getTiles());
        var tileIndex = tiles.indexOf(tile);
        return actorPositions.keySet().stream().filter(a -> actorPositions.get(a) == tileIndex)
            .collect(Collectors.toList()).toArray(Actor[]::new);
    }
}
