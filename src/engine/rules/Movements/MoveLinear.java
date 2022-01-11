package engine.rules.Movements;

import engine.pieces.LinearMovement;
import engine.pieces.Piece;
import game.GameBoard;
import game.Vector;

public class MoveLinear extends Movement {
    private final Vector direction;
    private boolean shouldBeInDirection;
    private boolean canEat = true;

    // pour ne pas tout casser;
    public MoveLinear() {
        direction = new Vector(0,0);
    }

    public MoveLinear(Vector direction){
        this.direction = direction;
    }

    public MoveLinear(Vector direction, boolean shouldBeInDirection, boolean canEat) {
        this(direction);
        this.shouldBeInDirection = shouldBeInDirection;
        this.canEat = canEat;
    }

    public Vector getDirection() {
        return direction;
    }

    private boolean canGo(GameBoard board, Piece piece, Vector to) {
        if (piece instanceof LinearMovement) {
            Vector diff = to.sub(piece.getPosition());
            // Le déplacement est refusé si la pièce se déplace de plus que son
            // déplacement maximal
            if (diff.norm() / direction.norm() > ((LinearMovement) piece).getMaxMove())
                return false;
        }
        // On check que le destination se trouve bien dans la bonne direction et
        // qu'aucun pion ne se trouve entre la piece et la destination.
        return direction.colinear(to.sub(piece.getPosition())) &&
                checkPieceAtSamePlace(board, piece, board.getPiece(to), to, canEat) &&
                checkNoPieceBetween(board, piece, to);
    }

    @Override
    public boolean check(GameBoard board, Piece piece, Vector to) {
        if (shouldBeInDirection) {
            Vector diff = to.sub(piece.getPosition());
            return canGo(board, piece, to) && diff.sameDirection(direction);
        }

        return canGo(board, piece, to);
    }

    @Override
    public void apply() {

    }
}
