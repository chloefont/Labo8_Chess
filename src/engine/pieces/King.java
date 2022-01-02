package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.rules.Movements.MoveLinear;
import engine.rules.Movements.Movement;
import game.GameBoard;
import game.Vector;

public class King extends Piece implements LinearMovement {
    int maxMove = getGameBoard().getWidth();
    public King(GameBoard gameBoard, PlayerColor color, Vector position) {
        super(gameBoard, color, position);

        Movement[] movementRules = {new MoveLinear()};
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
