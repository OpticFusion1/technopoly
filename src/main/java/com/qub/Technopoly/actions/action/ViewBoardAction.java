package com.qub.Technopoly.actions.action;

import com.qub.Technopoly.board.Board;
import com.qub.Technopoly.config.FieldConfig;
import com.qub.Technopoly.io.IOHelper;
import com.qub.Technopoly.io.OutputSource;
import com.qub.Technopoly.tile.Ownable;
import com.qub.Technopoly.tile.Property;
import com.qub.Technopoly.tile.Tile;
import com.qub.Technopoly.util.Field;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static org.apache.commons.lang3.ArrayUtils.isEmpty;

/**
 * {@inheritDoc}
 * Used to view the game board
 */
@RequiredArgsConstructor
public class ViewBoardAction implements Action {

    private static final String NAME = "View Board";
    private static final String DESCRIPTION = "View your position on the board";

    private static final String FIELD_BORDER = "#####################";

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
                    builder.append('\n');
                    builder.append(currentField.getName());
                    builder.append('\n');
                    builder.append(FIELD_BORDER);
                    builder.append('\n');
                }
            }
            else{
                builder.append('\n');
                builder.append(FIELD_BORDER);
                builder.append('\n');
            }

            builder.append(tile.getName());
            var actorsAtTile = board.getActorsAtTile(tile);

            if(tile instanceof Ownable){
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
            builder.append('\n');
        }
        return builder.toString();
    }
}
