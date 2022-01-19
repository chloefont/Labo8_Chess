package engine.rules.Movements;

import engine.pieces.Piece;
import game.GameBoard;
import game.Vector;

public class ShortCastling extends Castling {

    public ShortCastling(GameBoard board, Piece piece){
        super(  board,
                piece,
                Vector.LEFT,
                piece.getPosition().add(Vector.RIGHT.mult(-4))
        );
    }
}
