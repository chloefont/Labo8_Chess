package engine.pieces;
import chess.*;
import engine.rules.Rule;
import game.*;

public class Piece implements ChessView.UserChoice {
    final private PlayerColor color;
    final private GameBoard board;
    private Vector position;
    private Vector lastPosition;
    private Rule[] rules;
    private boolean isDead = false;

    protected Piece(GameBoard board, PlayerColor color, Vector position) {
        this.board = board;
        this.color = color;
        this.position = position;
        this.lastPosition = position;
    }

    protected Piece(Piece copyFrom){
        color = copyFrom.color;
        board = copyFrom.board;
        position = new Vector(copyFrom.position.getX(), copyFrom.position.getY());
        rules = copyFrom.rules;
    }

    protected GameBoard getBoard() {
        return board;
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

    public void setLastPosition(Vector lastPosition) {
        this.lastPosition = lastPosition;
    }

    public Vector getLastPosition() {
        return lastPosition;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    protected void setRules(Rule[] rules) {
        this.rules = rules;
    }

    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }

    public void move(Vector to) {

        for (Rule rule : rules) {
            rule.apply();
        }

        lastPosition = position;
        position = to;
        board.setLastPieceToMove(this);
    }

    public Piece copy(){
        return new Piece(this);
    }

    public boolean checkMove(Vector to){
        if (rules == null)
            return false;

        for (Rule rule : rules) {
            if (rule.check(to)) {
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

    public boolean isDead() {
        return isDead;
    }
}
