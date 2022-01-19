package engine.rules.Movements;

import engine.pieces.Piece;
import game.GameBoard;
import game.Vector;

public class LongCastling extends Castling {

    public LongCastling(GameBoard board, Piece piece) {
        super(board,
                piece,
                Vector.RIGHT,
                piece.getPosition().add(Vector.RIGHT.mult(3))
        );
    }
}
