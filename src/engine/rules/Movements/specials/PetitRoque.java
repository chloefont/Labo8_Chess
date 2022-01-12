package engine.rules.Movements.specials;

import engine.pieces.Piece;
import game.GameBoard;
import game.Vector;

public class PetitRoque extends Roque {

    public PetitRoque(GameBoard board, Piece piece){
        super(  board,
                piece,
                Vector.LEFT,
                piece.getPosition().add(Vector.RIGHT.mult(-4))
        );
    }
}
