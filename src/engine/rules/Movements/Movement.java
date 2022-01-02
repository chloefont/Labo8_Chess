package engine.rules.Movements;

import engine.pieces.LinearMovement;
import engine.pieces.Piece;
import engine.rules.Rule;
import game.GameBoard;
import game.Vector;

public class Movement implements Rule {
    @Override
    public boolean check(GameBoard board, Piece piece, Vector to) {
        return false;
    }

    public boolean checkLines(Piece piece, Vector to) {
        Vector diff = piece.getPosition().sub(to);
        return ((diff.getY() <= ((LinearMovement) piece).getMaxMove() && diff.getY() >= -((LinearMovement) piece).getMaxMove()) && diff.getX() == 0)
                || ((diff.getX() <= ((LinearMovement) piece).getMaxMove() && diff.getX() >= -((LinearMovement) piece).getMaxMove()) && diff.getY() == 0);
    }

    public boolean checkDiag(Piece piece, Vector to) {
        return false;
    }
}
