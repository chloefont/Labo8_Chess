package engine.rules.Movements;

import engine.pieces.Piece;
import game.GameBoard;
import game.Vector;

public class MoveRow extends Movement {
    @Override
    public boolean check(GameBoard board, Piece piece, Vector to) {
        return false;
    }
}
