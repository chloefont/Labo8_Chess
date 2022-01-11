package engine.rules.Movements;

import engine.pieces.Piece;
import game.GameBoard;
import game.Vector;

public class MoveL extends Movement {
    @Override
    public boolean check(GameBoard board, Piece piece, Vector to) {
        Vector diff = piece.getPosition().sub(to);

        return ((diff.getX() == 2 || diff.getX() == -2) && (diff.getY() == 1 || diff.getY() == -1))
                || ((diff.getY() == 2 || diff.getY() == -2) && (diff.getX() == 1 || diff.getX() == -1));
    }

    @Override
    public void apply() {

    }
}
