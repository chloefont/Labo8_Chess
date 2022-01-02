package engine.pieces;
import chess.*;
import engine.rules.Movements.Movement;
import engine.rules.Rule;
import game.*;

public class Piece {
    private PlayerColor color;
    private GameBoard gameBoard;
    private Vector position;
    private Movement[] movementRules;

    protected Piece(GameBoard gameBoard, PlayerColor color, Vector position) {
        this.gameBoard = gameBoard;
        this.color = color;
        this.position = position;
    }

    protected GameBoard getGameBoard() {
        return gameBoard;
    }

    public PlayerColor getColor() {
        return color;
    }

    public PieceType getType() {
        return null;
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    protected void setMovementRules(Movement[] movementRules) {
        this.movementRules = movementRules;
    }

    public boolean move(Vector to) {
        if (movementRules == null)
            return false;

        boolean moveOk = true;

        for (Movement movement : movementRules)
            if (movement.check(gameBoard, this, to))
                moveOk = false;

        if (moveOk)
            position = to;

        return moveOk;
    }
}
