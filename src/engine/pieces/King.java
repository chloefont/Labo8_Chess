package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.rules.Movements.MoveCol;
import engine.rules.Movements.Movement;
import game.GameBoard;
import game.Vector;

public class King extends Piece implements LinearMovement {
    int maxMove = getGameBoard().getWidth();
    public King(GameBoard gameBoard, PlayerColor color, Vector position) {
        super(gameBoard, color, position);

        Movement[] movementRules = {new MoveCol()};
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
