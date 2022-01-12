package engine.rules.Movements.specials;

import engine.pieces.Piece;
import game.GameBoard;
import game.Vector;

public class GrandRoque extends Roque {

    public GrandRoque(GameBoard board, Piece piece){
        super(  board,
                piece,
                Vector.RIGHT,
                piece.getPosition().add(Vector.RIGHT.mult(3))
        );
    }
}
