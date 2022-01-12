package engine.rules.Movements;

import engine.pieces.Piece;
import game.GameBoard;
import game.Vector;

public class MoveL extends Movement {

    public MoveL(GameBoard board, Piece piece) {
        super(board, piece);
    }

    @Override
    public boolean check(Vector to) {
        Vector diff = getPiece().getPosition().sub(to);

        return ((diff.getX() == 2 || diff.getX() == -2) && (diff.getY() == 1 || diff.getY() == -1))
                || ((diff.getY() == 2 || diff.getY() == -2) && (diff.getX() == 1 || diff.getX() == -1));
    }
}
