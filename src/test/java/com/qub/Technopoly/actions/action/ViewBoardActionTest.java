package com.qub.Technopoly.actions.action;

import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.actor.Player;
import com.qub.Technopoly.board.Board;
import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.config.FieldConfig;
import com.qub.Technopoly.config.PropertyConfig;
import com.qub.Technopoly.inventory.Inventory;
import com.qub.Technopoly.io.OutputSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ViewBoardActionTest {

    private Board testBoard;
    private OutputSource mockOutputSource;
    private ViewBoardAction viewBoardAction;

    @BeforeEach
    public void setup() {
        testBoard = new Board();
        mockOutputSource = mock(OutputSource.class);
        viewBoardAction = new ViewBoardAction(testBoard, mockOutputSource);
    }

    @Test
    public void executeOutputHasAllFieldNames() {
        viewBoardAction.execute();

        var allFieldNames =
            Arrays.stream(Config.getConfig().getFieldConfigs()).map(FieldConfig::getName)
                .collect(Collectors.toList());

        for (var fieldName : allFieldNames) {
            verify(mockOutputSource).writeBody(Mockito.contains(fieldName));
        }
    }

    @Test
    public void executeOutputHasAllPropertyNames() {
        viewBoardAction.execute();

        var allPropertyNames =
            Arrays.stream(Config.getConfig().getFieldConfigs()).map(FieldConfig::getPropertyConfigs)
                .flatMap(Stream::of).map(PropertyConfig::getPropertyName)
                .collect(Collectors.toList());

        for (var propertyName : allPropertyNames) {
            verify(mockOutputSource).writeBody(Mockito.contains(propertyName));
        }
    }

    @Test
    public void executeOutputHasAllActorNames() {
        var testActors = new Actor[testBoard.getBoardActorCapacity()];
        for (var i = 0; i < testActors.length; i++) {
            testActors[i] = new Player("Test Actor " + i, new Inventory());
        }
        testBoard.addActors(testActors);

        viewBoardAction.execute();

        for (var testActor : testActors) {
            verify(mockOutputSource).writeBody(Mockito.contains(testActor.getActorName()));
        }
    }
}
