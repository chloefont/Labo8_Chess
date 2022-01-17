package engine.rules;

import chess.PlayerColor;
import engine.pieces.Piece;
import engine.rules.Movements.MoveLinear;
import engine.rules.Movements.Movement;
import game.GameBoard;
import game.Vector;

/**
 * Cette class permet le mouvement de 2 cases pour les pions.
 */
public class DoubleForward extends Rule {

    public DoubleForward(GameBoard board, Piece piece) {
        super(board, piece);
    }

    public boolean check(Vector to) {
        int deltaY, posY;
        if (getPiece().getColor() == PlayerColor.WHITE) {
            deltaY = 2;
            posY = 1;
        } else {
            deltaY = -2;
            posY = getBoard().getWIDTH() - 2;
        }

        Movement movement = new MoveLinear(new Vector(0, deltaY), true, false, getBoard(), getPiece());

        return movement.check(to) && getPiece().getPosition().getY() == posY;
    }

}
