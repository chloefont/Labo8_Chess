package engine.rules;

import chess.PieceType;
import chess.PlayerColor;
import engine.pieces.Piece;
import engine.rules.Movements.MoveLinear;
import engine.rules.Movements.Movement;
import game.GameBoard;
import game.Vector;

public class EnPassant extends Rule {

    public EnPassant(GameBoard board, Piece piece) {
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
                    true, false, getBoard(), getPiece());
            Piece other = getBoard().getPieceAt(to.add(new Vector(0, -deltaY)));

            if (movement.check(to)
                    && other != null && other.getType() == PieceType.PAWN
                    && other.getColor() != getPiece().getColor()
                    && getBoard().getLastPieceMoved() == other
                    && new Vector(0, 2 * deltaY * -1).equals(other.getPosition().sub(other.getLastPosition()))) {

                getBoard().killPiece(other);
                return true;
            }
        }

        return false;
    }
}
