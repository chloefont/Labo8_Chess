package engine.rules.Movements;

import chess.PieceType;
import engine.pieces.LinearMovement;
import engine.pieces.Piece;
import engine.rules.Rule;
import game.GameBoard;
import game.Vector;

abstract public class Movement extends Rule {

    public boolean checkNoPieceBetween(GameBoard board, Piece piece, Vector to) {
        Vector diff = piece.getPosition().sub(to);
        for (Piece other : board.getPieces()) {
            if (other != null) {
                Vector diffOther = piece.getPosition().sub(other.getPosition());
                if (other != piece && !other.getPosition().equals(piece.getPosition()) && (diff.colinear(diffOther) && diff.sameDirection(diffOther)
                        && diff.norm() > diffOther.norm()))
                    return false;
            }
        }

        return true;
    }

    public boolean checkPieceAtSamePlace (GameBoard board, Piece piece, Piece other, Vector desiredPosition, boolean canEat) {
        assert board == null || piece == null;
        if (other == null) {
            return true;
        }

        // Les pions ne peuvent pas manger en utilisant leurs mouvements classiques
        if (other.getPosition().equals(desiredPosition) && (other.getColor() == piece.getColor() || !canEat)) {
            return false;
        }
        return true;
    }
}
