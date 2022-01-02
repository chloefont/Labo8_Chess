package engine.rules.Movements;

import engine.pieces.LinearMovement;
import engine.pieces.Piece;
import game.GameBoard;
import game.Vector;

public class MoveLinear extends Movement {
    @Override
    public boolean check(GameBoard board, Piece piece, Vector to) {
        return super.checkLines(piece, to);
    }
}
