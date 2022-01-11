package engine.pieces;
import chess.*;
import engine.rules.Rule;
import game.*;

public class Piece implements ChessView.UserChoice {
    private PlayerColor color;
    private GameBoard gameBoard;
    private Vector position;
    private Vector lastPosition;
    private Rule[] obligatoryRules;

    protected Piece(GameBoard gameBoard, PlayerColor color, Vector position) {
        this.gameBoard = gameBoard;
        this.color = color;
        this.position = position;
        this.lastPosition = position;
    }

    protected Piece(Piece copyFrom){
        color = copyFrom.color;
        gameBoard = copyFrom.gameBoard;
        position = new Vector(copyFrom.position.getX(), copyFrom.position.getY());
        obligatoryRules = copyFrom.obligatoryRules;
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

    public Vector getLastPosition() {
        return lastPosition;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    protected void setObligatoryRules(Rule[] obligatoryRules) {
        this.obligatoryRules = obligatoryRules;
    }

    public void move(Vector to) {

        for (Rule rule : rules) {
            rule.apply();
        }

        lastPosition = position;
        position = to;
        gameBoard.setLastPieceToMove(this);
    }

    public Piece copy(){
        return new Piece(this);
    }

    public boolean checkMove(Vector to){
        if (obligatoryRules == null)
            return false;

        for (Rule rule : obligatoryRules) {
            if (rule.check(gameBoard, this, to)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String textValue() {
        return toString();
    }

    @Override
    public String toString() {
        return "Piece";
    }
}
