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

    protected Piece(Piece copyFrom){
        color = copyFrom.color;
        gameBoard = copyFrom.gameBoard;
        position = new Vector(copyFrom.position.getX(), copyFrom.position.getY());
        movementRules = copyFrom.movementRules;
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
        if(checkMove(to)){
            position = new Vector(to);
            return true;
        }
        return false;
    }

    public Piece copy(){
        return new Piece(this);
    }

    public boolean checkMove(Vector to){
        if (movementRules == null)
            return false;

        for (Movement movement : movementRules) {
            if (movement.check(gameBoard, this, to)) {
                return true;
            }
        }

        return false;
    }

    public void onDeath() {

    }
}
