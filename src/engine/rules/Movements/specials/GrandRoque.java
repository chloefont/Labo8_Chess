package engine.rules.Movements.specials;

import engine.pieces.Piece;
import game.GameBoard;
import game.Vector;

public class GrandRoque extends Roque {

    public GrandRoque(GameBoard board, Piece piece){
        super(  board,
                piece,
                new Vector(2,0),
                new Vector(2,7),
                new Vector(0,0),
                new Vector(0,7),
                new Vector(3,0),
                new Vector(3,7),
                Vector.RIGHT
                );
    }
}
