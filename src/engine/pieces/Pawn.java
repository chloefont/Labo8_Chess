package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.rules.DoubleForward;
import engine.rules.Movements.specials.MoveLinearWithPromotion;
import engine.rules.Movements.specials.PriseEnPassant;
import engine.rules.EatDiag;
import engine.rules.Rule;
import game.GameBoard;
import game.Vector;

public class Pawn extends Piece implements LinearMovement {
    private final int MAX_MOVE = 1;

    public Pawn(GameBoard board, PlayerColor color, Vector position) {
        super(board, color, position);

        int dir;
        if (getColor() == PlayerColor.WHITE) {
            dir = 1;
        } else {
            dir = -1;
        }

        Rule[] obligRules = {
                new MoveLinearWithPromotion(new Vector(0, dir), true, false, board, this),
                new EatDiag(board, this),
                new DoubleForward(board, this),
                new PriseEnPassant(board, this)
        };

        setRules(obligRules);
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }

    @Override
    public int getMaxMove() {
        return MAX_MOVE;
    }

    public String toString() {
        return "Pawn";
    }
}
