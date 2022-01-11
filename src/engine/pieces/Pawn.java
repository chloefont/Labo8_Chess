package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.rules.Movements.MoveLinear;
import engine.rules.Movements.specials.DoubleForward;
import engine.rules.Movements.specials.MoveLinearWithPromotion;
import engine.rules.Movements.specials.PriseEnPassant;
import engine.rules.Movements.specials.eatDiag;
import engine.rules.Rule;
import game.GameBoard;
import game.Vector;

public class Pawn extends Piece implements LinearMovement {
    private int maxMove = 1;

    public Pawn(GameBoard gameBoard, PlayerColor color, Vector position) {
        super(gameBoard, color, position);

        int dir;
        if (getColor() == PlayerColor.WHITE) {
            dir = 1;
        } else {
            dir = -1;
        }

        Rule[] obligRules = {
                new MoveLinearWithPromotion(new Vector(0, dir), true, false),
                new eatDiag(),
                new DoubleForward(),
                new PriseEnPassant()
        };

        setObligatoryRules(obligRules);
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }

    @Override
    public int getMaxMove() {
        return maxMove;
    }

    public String toString() {
        return "Pawn";
    }
}
