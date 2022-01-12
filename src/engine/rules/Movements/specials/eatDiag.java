package engine.rules.Movements.specials;

import chess.PlayerColor;
import engine.pieces.Piece;
import engine.rules.Movements.MoveLinear;
import engine.rules.Movements.Movement;
import engine.rules.Rule;
import game.GameBoard;
import game.Vector;

public class eatDiag implements Rule {
    @Override
    public boolean check(GameBoard board, Piece piece, Vector to) {
        int deltaY;
        if (piece.getColor() == PlayerColor.WHITE) {
            deltaY = 1;
        } else {
            deltaY = -1;
        }

        int[] sides = {-1, 1};
        for (int side : sides) {
            Movement movement = new MoveLinear(new Vector(side, deltaY), true, true);
            if (movement.check(board, piece, to) && board.getPiece(to) != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void apply() {

    }
}
