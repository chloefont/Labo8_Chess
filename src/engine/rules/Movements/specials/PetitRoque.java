package engine.rules.Movements.specials;

import engine.pieces.Piece;
import game.GameBoard;
import game.Vector;

public class PetitRoque extends Roque {

    public PetitRoque(GameBoard board, Piece piece){
        super(  board,
                piece,
                new Vector(6,0),
                new Vector(6,7),
                new Vector(7,0),
                new Vector(7,7),
                new Vector(5,0),
                new Vector(5,7),
                new Vector(1,0)
                );
    }
}
