package engine.rules.Movements.specials;

import chess.PlayerColor;
import engine.pieces.HasMoved;
import engine.pieces.King;
import engine.pieces.Piece;
import engine.pieces.Rook;
import engine.rules.Movements.Movement;
import game.GameBoard;
import game.Vector;

public class PetitRoque extends Movement {
    private boolean canBeApplyed = false;
    private boolean done = false; // Si ce mouvement à déjà été fais
    private Rook rook;
    private King king;

    @Override
    public boolean check(GameBoard board, Piece piece, Vector to) {
        if(done) return false;
        if(!(to.equals(new Vector(1,0)) && piece.getColor() == PlayerColor.WHITE) && !(to.equals(new Vector(1,7)) && piece.getColor() == PlayerColor.BLACK)) return false;

        // Si le roi et la tour n'as pas bougé
        King king = (King) piece;
        if(king.hasMoved()) return false;

        Vector positionRook = piece.getColor() == PlayerColor.WHITE ? new Vector(0, 0) : new Vector(0, 7);
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

        Vector newRookPosition = king.getColor() == PlayerColor.WHITE ? new Vector(2, 0) : new Vector(2, 7);
        this.rook.move(newRookPosition);

        done = true;
        canBeApplyed = false;
        this.rook = null;
    }
}
