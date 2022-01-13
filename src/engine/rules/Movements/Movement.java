package engine.rules.Movements;

import chess.PieceType;
import engine.pieces.LinearMovement;
import engine.pieces.Piece;
import engine.rules.Rule;
import game.GameBoard;
import game.Vector;

abstract public class Movement extends Rule {
    protected Movement(GameBoard board, Piece piece) {
        super(board, piece);
    }

    /**
     * Permet de vérifier qu'aucune pièce se trouve entre la cible et la pièce.
     * @param to La cible.
     * @return Vrai si aucune pièce.
     */
    public boolean checkNoPieceBetween(Vector to) {
        Vector diff = getPiece().getPosition().sub(to);
        for (Piece other : getBoard().getPieces()) {
            if (other != null) {
                Vector diffOther = getPiece().getPosition().sub(other.getPosition());
                if (other != getPiece() && !other.getPosition().equals(getPiece().getPosition()) && (diff.colinear(diffOther) && diff.sameDirection(diffOther)
                        && diff.norm() > diffOther.norm()))
                    return false;
            }
        }

        return true;
    }

    /**
     * TODO
     * @param other
     * @param desiredPosition
     * @param canEat
     * @return
     */
    public boolean checkPieceAtSamePlace (Piece other, Vector desiredPosition, boolean canEat) {
        assert getBoard() == null || getPiece() == null;
        if (other == null) {
            return true;
        }

        // Les pions ne peuvent pas manger en utilisant leurs mouvements classiques
        if (other.getPosition().equals(desiredPosition) && (other.getColor() == getPiece().getColor() || !canEat)) {
            return false;
        }
        return true;
    }
}
