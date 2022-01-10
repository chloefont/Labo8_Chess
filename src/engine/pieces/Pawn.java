package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.rules.Movements.MoveLinear;
import engine.rules.Movements.Movement;
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


        Movement[] movementRules = {
                new MoveLinear(new Vector(0, dir), true, false),
                new MoveLinear(new Vector(1, dir), true, true),
                new MoveLinear(new Vector(-1, dir), true, true)
        };
        setMovementRules(movementRules);
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }

    @Override
    public int getMaxMove() {
        return maxMove;
    }
}
