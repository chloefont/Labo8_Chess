package engine.rules.Movements;

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
     *
     * @param to La cible.
     * @return Vrai si aucune pièce.
     */
    public boolean checkNoPieceBetween(Vector to) {
        assert to != null;

        // Chemin pour aller à la position voulue
        Vector diffTo = getPiece().getPosition().sub(to);

        for (Piece other : getBoard().getPieces()) {
            if (other != null) {
                // Chemin pour aller à l'autre pièce
                Vector diffOther = getPiece().getPosition().sub(other.getPosition());

                // Si les 2 chemins sont colinéaires, de même sens et que la distance
                // séparant les 2 pièces est plus petite que celle séparant la pièce
                // et la destination souhaitée
                if (other != getPiece()
                        && !other.getPosition().equals(getPiece().getPosition())
                        && (diffTo.colinear(diffOther)
                        && diffTo.sameDirection(diffOther)
                        && diffTo.norm() > diffOther.norm()))
                    return false;
            }
        }

        return true;
    }

    /**
     * Permet de vérifier si une certaine pièce se trouve sur la case où l'on
     * souhaite déplacer notre pièce.
     *
     * @param other           Pièce qui pourrait être sur la case en question
     * @param desiredPosition Position où l'on souhaite déplacer notre pièce.
     * @param canKill         Capacité de notre pièce à "tuer" une autre pièce
     * @return
     */
    public boolean checkPieceAtSamePlace(Piece other, Vector desiredPosition,
                                         boolean canKill) {
        assert getBoard() == null || getPiece() == null;

        if (other == null) {
            return true;
        }

        // Les pions ne peuvent pas manger en utilisant leurs mouvements classiques
        if (other.getPosition().equals(desiredPosition)
                && (other.getColor() == getPiece().getColor() || !canKill)) {
            return false;
        }
        return true;
    }
}
