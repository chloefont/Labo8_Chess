package engine.rules;

import chess.ChessView;
import engine.pieces.Piece;
import game.GameBoard;
import game.Vector;

public abstract class Rule {
    public abstract boolean check(GameBoard board, Piece piece, Vector to);
    public void apply(){};
}
