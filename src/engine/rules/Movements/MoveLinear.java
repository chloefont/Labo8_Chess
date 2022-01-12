package engine.rules.Movements;

import engine.pieces.LinearMovement;
import engine.pieces.Piece;
import game.GameBoard;
import game.Vector;

public class MoveLinear extends Movement {
    private final Vector direction;
    private boolean shouldBeInDirection;
    private boolean canEat = true;

    public MoveLinear(Vector direction, GameBoard board, Piece piece){
        super(board, piece);
        this.direction = direction;
    }

    public MoveLinear(Vector direction, boolean shouldBeInDirection, boolean canEat, GameBoard board, Piece piece) {
        this(direction, board, piece);
        this.shouldBeInDirection = shouldBeInDirection;
        this.canEat = canEat;
    }

    public Vector getDirection() {
        return direction;
    }

    private boolean canGo(Vector to) {
        if (getPiece() instanceof LinearMovement) {
            Vector diff = to.sub(getPiece().getPosition());
            // Le déplacement est refusé si la pièce se déplace de plus que son
            // déplacement maximal
            if (diff.norm() / direction.norm() > ((LinearMovement) getPiece()).getMaxMove())
                return false;
        }
        // On check que le destination se trouve bien dans la bonne direction et
        // qu'aucun pion ne se trouve entre la piece et la destination.
        return direction.colinear(to.sub(getPiece().getPosition())) &&
                checkPieceAtSamePlace(getBoard().getPieceAt(to), to, canEat) &&
                checkNoPieceBetween(to);
    }

    @Override
    public boolean check(Vector to) {
        if (shouldBeInDirection) {
            Vector diff = to.sub(getPiece().getPosition());
            return canGo(to) && diff.sameDirection(direction);
        }

        return canGo(to);
    }
}
