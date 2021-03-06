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
        assert board != null && color != null && position != null;

        this.board = board;
        this.color = color;
        this.position = position;
        this.lastPosition = position;
    }

    /**
     * Permet de déplacer une pièce.
     *
     * @param to Vecteur indiquant le nouvel emplacement.
     */
    public void move(Vector to) {
        assert to != null;

        for (Rule rule : rules) {
            rule.apply();
        }

        lastPosition = position;
        position = to;
        board.setLastPieceMoved(this);
    }

    /**
     * Permet de vérifier qu'un déplacement est applicable selon les règles définies par le constructeur.
     *
     * @param to Vecteur indiquant le nouvel emplacement.
     * @return Vrai s'il est possible de se déplacer à cet emplacement.
     */
    public boolean checkMove(Vector to) {
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

    /**
     * Permet de savoir si cette pièce est morte.
     *
     * @return Vrai si mort.
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Informe de la capacité de la pièce à se déplacer (ne prend pas en compte
     * l'échec).
     *
     * @return Vrai si peut se déplacer
     */
    public boolean canMove() {
        for (Rule rule : rules) {
            if (rule.canMove()) {
                return true;
            }
        }
        return false;
    }

    // Getters et setters
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
        assert position != null;
        this.position = position;
    }

    protected void setRules(Rule[] rules) {
        assert rules != null;
        this.rules = rules;
    }

    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }
}
