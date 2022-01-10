package engine.rules.Movements;

import engine.pieces.LinearMovement;
import engine.pieces.Piece;
import engine.rules.Rule;
import game.GameBoard;
import game.Vector;

abstract public class Movement implements Rule {

    public boolean checkNoPieceBetween(GameBoard board, Piece piece, Vector to) {
        Vector diff = piece.getPosition().sub(to);
        for (Piece other : board.getPieces()) {
            if (other != null) {
                Vector diffOther = piece.getPosition().sub(other.getPosition());
                if (other != piece && (diff.colinear(diffOther) && diff.sameDirection(diffOther) && diff.norm() > diffOther.norm()))
                    return false;
            }
        }

        return true;
    }

    public boolean checkPieceAtSamePlace (GameBoard board, Piece piece, Piece other, Vector disiredPosition) {
        assert board == null || piece == null;
        if (other == null) {
            return true;
        }

        if (other.getPosition().equals(disiredPosition)) {
            if (other.getColor() == piece.getColor()) {
                return false;
            } else {
                board.onDeath(other);
            }
        }
        return true;
    }
}
