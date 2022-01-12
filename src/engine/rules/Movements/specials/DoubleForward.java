package engine.rules.Movements.specials;

import chess.PlayerColor;
import engine.pieces.Piece;
import engine.rules.Movements.MoveLinear;
import engine.rules.Movements.Movement;
import engine.rules.Rule;
import game.GameBoard;
import game.Vector;

public class DoubleForward implements Rule {
    @Override
    public boolean check(GameBoard board, Piece piece, Vector to) {
        int deltaY, posY;
        if (piece.getColor() == PlayerColor.WHITE) {
            deltaY = 2;
            posY = 1;
        } else {
            deltaY = -2;
            posY = board.getWidth() - 2;
        }

        Movement movement = new MoveLinear(new Vector(0, deltaY), true, false);

        return movement.check(board, piece, to) && piece.getPosition().getY() == posY;
    }

    @Override
    public void apply() {

    }
}
