package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.rules.Movements.MoveLinear;
import engine.rules.Movements.Movement;
import game.GameBoard;
import game.Vector;

public class King extends Piece implements LinearMovement {
    int maxMove = 1;
    public King(GameBoard gameBoard, PlayerColor color, Vector position) {
        super(gameBoard, color, position);

        Movement[] movementRules = {new Movement()};
        setMovementRules(movementRules);
    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }

    @Override
    public int getMaxMove() {
        return maxMove;
    }
}
