package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.rules.DoubleForward;
import engine.rules.Movements.MoveLinearWithPromotion;
import engine.rules.EnPassant;
import engine.rules.EatDiag;
import engine.rules.Rule;
import game.GameBoard;
import game.Vector;

public class Pawn extends Piece implements LimitedMovement {
    private final int MAX_MOVE = 1;

    public Pawn(GameBoard board, PlayerColor color, Vector position) {
        super(board, color, position);

        int dir;
        if (getColor() == PlayerColor.WHITE) {
            dir = 1;
        } else {
            dir = -1;
        }

        Rule[] rules = {
                new MoveLinearWithPromotion(new Vector(0, dir), true, false, board, this),
                new EatDiag(board, this),
                new DoubleForward(board, this),
                new EnPassant(board, this)
        };

        setRules(rules);
    }

    public String toString() {
        return "Pawn";
    }

    // Getters et setters
    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }

    @Override
    public int getMaxMove() {
        return MAX_MOVE;
    }
}
