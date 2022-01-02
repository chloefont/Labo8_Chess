package engine.rules.Movements;

import engine.pieces.LinearMovement;
import engine.pieces.Piece;
import game.GameBoard;
import game.Vector;

public class MoveCol extends Movement {
    @Override
    public boolean check(GameBoard board, Piece piece, Vector to) {
        if (!(piece instanceof LinearMovement))
            return false;

        Vector diff = piece.getPosition().sub(to);
        return diff.getX() == 0 && (diff.getY() <= ((LinearMovement) piece).getMaxMove() && diff.getY() >= -((LinearMovement) piece).getMaxMove());
    }
}
