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
            Vector diffOther = piece.getPosition().sub(other.getPosition());
            if (other != piece && (diff.colinear(diffOther) && diff.sameDirection(diffOther) && diff.norm() > diffOther.norm()))
                return false;
        }

        return true;
    }
}
