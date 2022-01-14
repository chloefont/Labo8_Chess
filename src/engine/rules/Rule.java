package engine.rules;

import engine.pieces.Piece;
import game.GameBoard;
import game.Vector;

public abstract class Rule {
    private final GameBoard BOARD;
    private final Piece PIECE;
    private boolean canBeApplyed = false;


    protected Rule(GameBoard board, Piece piece) {
        this.BOARD = board;
        this.PIECE = piece;
    }

    /**
     * Permet de vérifier si cette règle est applicable.
     * @param to
     * @return
     */
    public abstract boolean check(Vector to);

    /**
     * Permet d'appliquer la règle. Ne fait rien par défaut.
     */
    public void apply(){/*does nothing by default*/};

    public boolean canMove() {
        return false;
    }

    public GameBoard getBoard() {
        return BOARD;
    }

    public Piece getPiece() {
        return PIECE;
    }

    public boolean getCanBeApplyed(){
        return canBeApplyed;
    }

    public void setCanBeApplyed(boolean canBeApplyed){
        this.canBeApplyed = canBeApplyed;
    }
}
