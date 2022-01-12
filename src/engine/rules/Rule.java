package engine.rules;

import engine.pieces.Piece;
import game.GameBoard;
import game.Vector;

public abstract class Rule {
    private final GameBoard BOARD;
    private final Piece PIECE;

    protected Rule(GameBoard board, Piece piece) {
        this.BOARD = board;
        this.PIECE = piece;
    }

    public abstract boolean check(Vector to);
    public void apply(){/*does nothing by default*/};

    public GameBoard getBoard() {
        return BOARD;
    }

    public Piece getPiece() {
        return PIECE;
    }
}
