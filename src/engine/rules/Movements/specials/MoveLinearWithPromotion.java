package engine.rules.Movements.specials;

import chess.PlayerColor;
import engine.pieces.Piece;
import engine.rules.Movements.MoveLinear;
import engine.rules.Movements.Movement;
import game.GameBoard;
import game.Vector;

public class MoveLinearWithPromotion extends MoveLinear {

    public MoveLinearWithPromotion(Vector direction, boolean shouldBeInDirection, boolean canEat) {
        super(direction, shouldBeInDirection, canEat);
    }

    @Override
    public boolean check(GameBoard board, Piece piece, Vector to) {
        if (!super.check(board, piece, to))
            return false;

        int pos;
        if (piece.getColor() == PlayerColor.WHITE)
            pos = board.getWidth() - 1;
        else
            pos = 0;

        if (to.getY() == pos) {
            board.promotion(piece, to);
        }
        return true;
    }
}
