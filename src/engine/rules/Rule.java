package engine.rules;

import chess.ChessView;
import engine.pieces.Piece;
import game.GameBoard;
import game.Vector;

public abstract class Rule {
    private GameBoard board;
    private Piece piece;

    protected Rule(GameBoard board, Piece piece) {
        this.board = board;
        this.piece = piece;
    }

    public abstract boolean check(Vector to);
    public void apply(){};

    public GameBoard getBoard() {
        return board;
    }

    public Piece getPiece() {
        return piece;
    }
}
