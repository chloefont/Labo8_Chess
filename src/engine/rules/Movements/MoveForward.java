package engine.rules.Movements;

import chess.PlayerColor;
import engine.pieces.Piece;
import game.GameBoard;
import game.Vector;

public class MoveForward extends Movement {
    @Override
    public boolean check(GameBoard board, Piece piece, Vector to) {
        Vector diff = piece.getPosition().sub(to);

        if (piece.getColor() == PlayerColor.WHITE)
            return diff.getY() < 0;
        else
            return diff.getY() > 0;
    }
}
