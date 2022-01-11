package engine.rules;

import chess.ChessView;
import engine.pieces.Piece;
import game.GameBoard;
import game.Vector;

public interface Rule {
    boolean check (GameBoard board, Piece piece, Vector to);
    void apply();
}
