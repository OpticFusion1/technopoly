package com.qub.Technopoly.actions.action;

import com.qub.Technopoly.actor.Actor;
import com.qub.Technopoly.actor.Player;
import com.qub.Technopoly.board.Board;
import com.qub.Technopoly.config.Config;
import com.qub.Technopoly.config.PlayerConfig;
import com.qub.Technopoly.inventory.Inventory;
import com.qub.Technopoly.io.IOHelper;
import com.qub.Technopoly.io.InputSource;
import com.qub.Technopoly.io.OutputSource;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;

import static java.lang.String.format;

@RequiredArgsConstructor
public class StartNewGameAction implements Action {

    private static final String EXECUTE_DESCRIPTION_FORMAT =
        "How many players will be playing? (%s - %s)";
    private static final String EXECUTE_PLAYERNAME_FORMAT = "Please enter Player %s's name:";
    private static final String MIN_PLAYER_ERROR_FORMAT =
        "A minimum of %s players are needed to play.";
    private static final String MAX_PLAYER_ERROR_FORMAT = "Can't have more than %s players.";
    private static final String UNIQUE_PLAYERNAME_ERROR =
        "Sorry, this name has already been picked.";

    private static final String NAME = "Start New Game";
    private static final String DESCRIPTION = null;

    @NonNull
    private final Board board;

    @NonNull
    private final PlayerConfig playerConfig = Config.getConfig().getPlayerConfig();

    private final OutputSource outputSource = IOHelper.getOutputSource();

    private final InputSource inputSource = IOHelper.getInputSource();

    @Override
    public @NonNull String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public boolean execute() {
        outputSource.writeBody(format(EXECUTE_DESCRIPTION_FORMAT, playerConfig.getMinPlayers(),
            playerConfig.getMaxPlayers()));

        int selectedPlayerCount = inputSource.getNextInt();
        if (selectedPlayerCount < playerConfig.getMinPlayers()) {
            outputSource.writeBody(format(MIN_PLAYER_ERROR_FORMAT, playerConfig.getMinPlayers()));
            return false;
        }
        if (selectedPlayerCount > playerConfig.getMaxPlayers()) {
            outputSource.writeBody(format(MAX_PLAYER_ERROR_FORMAT, playerConfig.getMaxPlayers()));
            return false;
        }

        var playerNames = new HashSet<String>();
        for (var i = 0; i < selectedPlayerCount; i++) {
            outputSource.writeBody(format(EXECUTE_PLAYERNAME_FORMAT, i + 1));
            var selectedPlayerName = inputSource.getNextString();

            if (playerNames.contains(selectedPlayerName)) {
                outputSource.writeBody(UNIQUE_PLAYERNAME_ERROR);
                i--;
                continue;
            }
            playerNames.add(selectedPlayerName);
        }

        board.addActors(playerNames.stream()
            .map(name -> new Player(name, new Inventory()))
            .toArray(Actor[]::new));
        return true;
    }
}
