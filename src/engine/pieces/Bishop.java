package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.rules.Movements.MoveLinear;
import engine.rules.Rule;
import game.GameBoard;
import game.Vector;

public class Bishop extends Piece implements LimitedMovement {
    private final int MAX_MOVE = getBoard().getWidth();

    public Bishop(GameBoard board, PlayerColor color, Vector position) {
        super(board, color, position);

        Rule[] rules = {
                new MoveLinear(new Vector(1, 1),board, this),
                new MoveLinear(new Vector(-1, 1), board, this),
        };
        setRules(rules);
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }

    @Override
    public int getMaxMove() {
        return MAX_MOVE;
    }

    public String toString() {
        return "Bishop";
    }
}
