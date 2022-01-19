package engine.rules;

import chess.PlayerColor;
import engine.pieces.Piece;
import engine.rules.Movements.MoveLinear;
import engine.rules.Movements.Movement;
import game.GameBoard;
import game.Vector;

/**
 * Cette classe permet de savoir s'il est possible de manger une pièce en diagonal.
 */
public class EatDiag extends Rule {

    public EatDiag(GameBoard board, Piece piece) {
        super(board, piece);
    }

    @Override
    public boolean check(Vector to) {
        assert to != null;

        int deltaY;
        if (getPiece().getColor() == PlayerColor.WHITE) {
            deltaY = 1;
        } else {
            deltaY = -1;
        }

        int[] sides = {-1, 1};
        for (int side : sides) {
            Movement movement = new MoveLinear(new Vector(side, deltaY),
                    true, true, getBoard(), getPiece());
            if (movement.check(to) && getBoard().getPieceAt(to) != null) {
                return true;
            }
        }
        return false;
    }
}
