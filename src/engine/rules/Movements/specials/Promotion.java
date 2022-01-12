package engine.rules.Movements.specials;

import engine.pieces.Piece;
import engine.rules.Rule;
import game.GameBoard;
import game.Vector;

public class Promotion extends Rule {
     public Promotion(GameBoard board, Piece piece) {
         super(board, piece);
     }

    @Override
    public boolean check(Vector to) {
        return false;
    }
}
