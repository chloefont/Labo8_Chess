package engine.rules.Movements;

import chess.PlayerColor;
import engine.pieces.Piece;
import engine.rules.Movements.MoveLinear;
import game.GameBoard;
import game.Vector;

public class MoveLinearWithPromotion extends MoveLinear {

    public MoveLinearWithPromotion(Vector direction, boolean shouldBeInDirection, boolean canEat, GameBoard board, Piece piece) {
        super(direction, shouldBeInDirection, canEat, board, piece);
    }

    @Override
    public boolean check(Vector to) {
        assert to != null;

        if (!super.check(to))
            return false;

        int pos;
        if (getPiece().getColor() == PlayerColor.WHITE)
            pos = getBoard().getWidth() - 1;
        else
            pos = 0;

        if (to.getY() == pos) {
            getBoard().promotion(getPiece(), to);
        }
        return true;
    }
}
