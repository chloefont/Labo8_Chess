package engine.rules.Movements.specials;

import chess.PlayerColor;
import engine.pieces.King;
import engine.pieces.Piece;
import engine.pieces.Rook;
import engine.rules.Movements.Movement;
import game.GameBoard;
import game.Vector;

public abstract class Roque extends Movement {
    private boolean canBeApplyed = false;
    private boolean done = false; // Si ce mouvement à déjà été fais
    private Rook rook;
    private King king;

    private final Vector POSITION_BLACK;
    private final Vector POSITION_WHITE;
    private final Vector POSITION_ROCK_WHITE;
    private final Vector POSITION_ROCK_BLACK;
    private final Vector NEW_POSITION_ROCK_WHITE;
    private final Vector NEW_POSITION_ROCK_BLACK;

    Roque(Vector positionWhite, Vector positionBlack, Vector positionRockWhite, Vector positionRockBlack, Vector newPositionRockWhite, Vector newPositionRockBlack){
//        POSITION_BLACK = new Vector(1,7);
//        POSITION_WHITE = new Vector(1,0);
//        POSITION_ROCK_WHITE = new Vector(0,0);
//        POSITION_ROCK_BLACK = new Vector(0,7);
//        NEW_POSITION_ROCK_WHITE = new Vector(2,0);
//        NEW_POSITION_ROCK_BLACK = new Vector(2,7);

        POSITION_BLACK = positionBlack;
        POSITION_WHITE = positionWhite;
        POSITION_ROCK_WHITE = positionRockWhite;
        POSITION_ROCK_BLACK = positionRockBlack;
        NEW_POSITION_ROCK_WHITE = newPositionRockWhite;
        NEW_POSITION_ROCK_BLACK = newPositionRockBlack;

    }

    @Override
    public boolean check(GameBoard board, Piece piece, Vector to) {
        if(done) return false;
        if(!(to.equals(POSITION_WHITE) && piece.getColor() == PlayerColor.WHITE) && !(to.equals(POSITION_BLACK) && piece.getColor() == PlayerColor.BLACK)) return false;

        // Si le roi et la tour n'as pas bougé
        King king = (King) piece;
        if(king.hasMoved()) return false;

        Vector positionRook = piece.getColor() == PlayerColor.WHITE ? POSITION_ROCK_WHITE : POSITION_ROCK_BLACK;
        Piece rook = board.getPiece(positionRook);

        if(rook == null || !(rook instanceof Rook)) return false;
        if(((Rook)rook).hasMoved()) return false;

        // Si aucune pièce se trouve entre le roi et la tour
        if(!checkNoPieceBetween(board, king, positionRook)) return false;

        // modifier la place de la tour
        canBeApplyed = true;
        this.rook = (Rook)rook;
        this.king = king;

        return true;
    }

    @Override
    public void apply() {
        if(!canBeApplyed) return;

        Vector newRookPosition = king.getColor() == PlayerColor.WHITE ? NEW_POSITION_ROCK_WHITE : NEW_POSITION_ROCK_BLACK;
        this.rook.move(newRookPosition);

        done = true;
        canBeApplyed = false;
        this.rook = null;
    }
}
