package engine.rules.Movements.specials;

import engine.pieces.Piece;
import engine.rules.Rule;
import game.GameBoard;
import game.Vector;

public class Promotion extends Rule {
    @Override
    public boolean check(GameBoard board, Piece piece, Vector to) {
        return false;
    }
}
