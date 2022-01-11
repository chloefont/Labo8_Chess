package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.rules.Movements.MoveLinear;
import engine.rules.Movements.Movement;
import game.GameBoard;
import game.Vector;

public class Rook extends Piece implements LinearMovement, HasMoved {
    int maxMove = getGameBoard().getWidth();
    private boolean hasMoved = false;

    public Rook(GameBoard gameBoard, PlayerColor color, Vector position) {
        super(gameBoard, color, position);

        Movement[] movementRules = {
                new MoveLinear(new Vector(1, 0)),
                new MoveLinear(new Vector(0, 1)),
        };
        setObligatoryRules(movementRules);
    }

    @Override
    public void move(Vector to){
        super.move(to);
        hasMoved = true;
    }

    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }

    @Override
    public int getMaxMove() {
        return maxMove;
    }

    public String toString() {
        return "Rook";
    }

    @Override
    public boolean hasMoved() {
        return hasMoved;
    }
}
