package engine.rules.Movements.specials;

import chess.PieceType;
import engine.pieces.Piece;
import game.GameBoard;
import game.Vector;

public class GrandRoque extends SpecialMovement {
    @Override
    public boolean check(GameBoard board, Piece piece, Vector to) {
        return false;
    }
}
