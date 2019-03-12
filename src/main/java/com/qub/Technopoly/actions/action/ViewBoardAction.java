package com.qub.Technopoly.actions.action;

import com.qub.Technopoly.board.Board;
import com.qub.Technopoly.config.FieldConfig;
import com.qub.Technopoly.io.OutputSource;
import com.qub.Technopoly.tile.Ownable;
import com.qub.Technopoly.tile.Property;
import com.qub.Technopoly.tile.Tile;
import com.qub.Technopoly.util.Field;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static java.nio.charset.Charset.defaultCharset;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * {@inheritDoc}
 * Used to view the game board
 */
@RequiredArgsConstructor
public class ViewBoardAction implements Action {

    private static final String NAME = "View Board";
    private static final String DESCRIPTION = "View your position on the board";

    //private static final String FIELD_BORDER = "#####################";
    private static final String FIELD_BORDER = "---------------------";
    private static final String SPACE = " ";
    private static final String NEWLINE = "\n";
    private static final String UNICODE_HOUSE = "\u2302";
    private static final String UNICODE_HOTEL = "\u26EB";

    @NonNull
    private final Board board;

    @NonNull
    private final OutputSource outputSource;

    /**
     * {@inheritDoc}
     */
    @Override
    public @NonNull String getName() {
        return NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean execute() {
        var tiles = board.getTiles();
        outputSource.writeBody(getBoardDescription(tiles));
        return false;
    }

    private String getBoardDescription(Tile[] all) {

        var builder = new StringBuilder();

        FieldConfig currentField = null;
        for (var i = 0; i < all.length; i++) {
            var tile = all[i];

            if (tile instanceof Property) {
                var newField = Field.getFieldForProperty((Property) tile);
                if (!newField.equals(currentField)) {
                    currentField = newField;
                    builder.append(NEWLINE);
                    builder.append(currentField.getName());
                    builder.append(NEWLINE);
                    builder.append(FIELD_BORDER);
                    builder.append(NEWLINE);
                }
            } else {
                builder.append(NEWLINE);
                builder.append(FIELD_BORDER);
                builder.append(NEWLINE);
            }

            builder.append(tile.getName());

            if (tile instanceof Property) {
                var property = ((Property) tile);
                var houseCount = property.getCurrentHouses();

                if (houseCount > 0) {
                    builder.append(SPACE);

                    if (!property.canUpgrade()) {
                        var hotelBytes = UNICODE_HOTEL.getBytes(UTF_8);
                        var hotelString = new String(hotelBytes, defaultCharset());
                        builder.append(hotelString);
                    } else {
                        var houseBytes = UNICODE_HOUSE.getBytes(UTF_8);
                        var houseString = new String(houseBytes, defaultCharset());
                        for (var j = 0; j < houseCount; j++) {
                            builder.append(houseString);
                        }
                    }
                }
            }

            var actorsAtTile = board.getActorsAtTile(tile);

            if (tile instanceof Ownable) {
                var owner = ((Ownable) tile).getOwner();
                var ownerName = owner != null ? owner.getActorName() : "None";
                builder.append(" (Owner: ");
                builder.append(ownerName);
                builder.append(")");
            }

            if (!isEmpty(actorsAtTile)) {
                builder.append(" (Visiting: ");
                for (var j = 0; j < actorsAtTile.length; j++) {
                    var tileActor = actorsAtTile[j];
                    builder.append(tileActor.getActorName());
                    if (j < actorsAtTile.length - 1) {
                        builder.append(", ");
                    }
                }
                builder.append(")");
            }
            builder.append(NEWLINE);
        }
        return builder.toString();
    }
}
