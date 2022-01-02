package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.rules.Movements.MoveDiag;
import engine.rules.Movements.MoveLinear;
import engine.rules.Movements.Movement;
import game.GameBoard;
import game.Vector;

public class Bishop extends Piece implements LinearMovement {
    int maxMove = getGameBoard().getWidth();

    public Bishop(GameBoard gameBoard, PlayerColor color, Vector position) {
        super(gameBoard, color, position);

        Movement[] movementRules = {new MoveDiag()};
        setMovementRules(movementRules);
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }

    @Override
    public int getMaxMove() {
        return maxMove;
    }
}
