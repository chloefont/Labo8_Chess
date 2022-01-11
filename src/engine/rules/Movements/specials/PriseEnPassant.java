package engine.rules.Movements.specials;

import chess.PlayerColor;
import engine.pieces.Piece;
import engine.rules.Movements.MoveLinear;
import engine.rules.Movements.Movement;
import game.GameBoard;
import game.Vector;

public class PriseEnPassant extends SpecialMovement {
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
            Movement movement = new MoveLinear(new Vector(side, deltaY), true, false);
            Piece other = board.getPiece(to.add(new Vector(0, -deltaY)));

            //TODO + vérifier dernier movement = doubleforwards
            if (movement.check(board, piece, to) && other != null && other.getColor() != piece.getColor()
                    &&board.getLastPieceToMove() == other && new Vector(0, 2 * deltaY * -1).equals(other.getPosition().sub(other.getLastPosition()))) {
                board.onDeath(other);
                return true;
            }
        }

        return false;
    }
}
