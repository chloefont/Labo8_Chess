package engine.pieces;
import chess.*;
import engine.rules.Movements.specials.SpecialMovement;
import engine.rules.Rule;
import game.*;

public class Piece {
    private PlayerColor color;
    private GameBoard gameBoard;
    private Vector position;
    private Vector lastPosition;
    private Rule[] rules;
    private SpecialMovement[] specialMovementsRules;

    protected Piece(GameBoard gameBoard, PlayerColor color, Vector position) {
        this.gameBoard = gameBoard;
        this.color = color;
        this.position = position;
    }

    protected Piece(Piece copyFrom){
        color = copyFrom.color;
        gameBoard = copyFrom.gameBoard;
        position = new Vector(copyFrom.position.getX(), copyFrom.position.getY());
        rules = copyFrom.rules;
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

    public Vector getLastPosition(){
        return lastPosition;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    protected void setRules(Rule[] rules) {
        this.rules = rules;
    }

    public boolean move(Vector to) {
        if(checkMove(to)){
            lastPosition = position;
            position = to;
            return true;
        }
        return false;
    }

    public Piece copy(){
        return new Piece(this);
    }

    public boolean checkMove(Vector to){
        if (rules == null)
            return false;

        for (Rule rule : rules) {
            if (rule.check(gameBoard, this, to)) {
                return true;
            }
        }
        return false;
    }

    public void onDeath() {

    }
}
