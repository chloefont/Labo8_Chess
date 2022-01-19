package engine.rules;

import engine.pieces.Piece;
import game.GameBoard;
import game.Vector;

public abstract class Rule {
    private final GameBoard BOARD;
    private final Piece PIECE;
    private boolean canBeApplied = true;


    protected Rule(GameBoard board, Piece piece) {
        assert board != null && piece != null;
        this.BOARD = board;
        this.PIECE = piece;
    }

    /**
     * Permet de vérifier si cette règle est applicable.
     * @param to Position finale
     * @return Vrai si accepté
     */
    public abstract boolean check(Vector to);

    /**
     * Permet d'appliquer la règle. Ne fait rien par défaut.
     */
    public void apply(){/*does nothing by default*/};

    /**
     * Informe sur la capacité d'une pièce de se déplacer en employant cette règle
     * @return Vrai si peut se déplacer
     */
    public boolean canMove() {
        return false;
    }

    // Getters et setters
    public GameBoard getBoard() {
        return BOARD;
    }

    public Piece getPiece() {
        return PIECE;
    }

    public boolean getCanBeApplied(){
        return canBeApplied;
    }

    public void setCanBeApplied(boolean canBeApplied){
        this.canBeApplied = canBeApplied;
    }
}
