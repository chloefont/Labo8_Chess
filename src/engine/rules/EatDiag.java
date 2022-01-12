package engine.rules;

import chess.PlayerColor;
import engine.pieces.Piece;
import engine.rules.Movements.MoveLinear;
import engine.rules.Movements.Movement;
import engine.rules.Rule;
import game.GameBoard;
import game.Vector;

public class EatDiag extends Rule {

    public EatDiag(GameBoard board, Piece piece) {
        super(board, piece);
    }

    @Override
    public boolean check(Vector to) {
        int deltaY;
        if (getPiece().getColor() == PlayerColor.WHITE) {
            deltaY = 1;
        } else {
            deltaY = -1;
        }

        int[] sides = {-1, 1};
        for (int side : sides) {
            Movement movement = new MoveLinear(new Vector(side, deltaY), true, true, getBoard(), getPiece());
            if (movement.check(to) && getBoard().getPiece(to) != null) {
                return true;
            }
        }
        return false;
    }
}
