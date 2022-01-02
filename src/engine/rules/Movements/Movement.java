package engine.rules.Movements;

import engine.pieces.LinearMovement;
import engine.pieces.Piece;
import engine.rules.Rule;
import game.GameBoard;
import game.Vector;

public class Movement implements Rule {
    @Override
    public boolean check(GameBoard board, Piece piece, Vector to) {
        return (checkDiag(board, piece, to) || checkLines(board, piece, to)) && checkNoPieceBetween(board, piece, to);
    }

    public boolean checkLines(GameBoard board, Piece piece, Vector to) {
        if (!(piece instanceof LinearMovement))
            return false;

        Vector diff = piece.getPosition().sub(to);
        return ((diff.getY() <= ((LinearMovement) piece).getMaxMove() && diff.getY() >= -((LinearMovement) piece).getMaxMove()) && diff.getX() == 0)
                || ((diff.getX() <= ((LinearMovement) piece).getMaxMove() && diff.getX() >= -((LinearMovement) piece).getMaxMove()) && diff.getY() == 0);
    }

    public boolean checkDiag(GameBoard board, Piece piece, Vector to) {
        if (!(piece instanceof LinearMovement))
            return false;

        Vector diff = piece.getPosition().sub(to);
        return (diff.getY() == diff.getX() || -diff.getY() == diff.getX())
                && (diff.getX() <= ((LinearMovement) piece).getMaxMove() && diff.getX() >= -((LinearMovement) piece).getMaxMove());
    }

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
