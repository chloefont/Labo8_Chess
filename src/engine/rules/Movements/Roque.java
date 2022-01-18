package engine.rules.Movements;

import chess.PieceType;
import engine.pieces.King;
import engine.pieces.Piece;
import engine.pieces.Rook;
import engine.rules.Movements.Movement;
import game.GameBoard;
import game.Vector;

public abstract class Roque extends Movement {
    private boolean done = false; // Si ce mouvement à déjà été fait
    private boolean applyRule = false;
    private Rook rook;
    private final Vector DEFAULT_KING_POSITION;
    private final Vector DIRECTION;
    private final Vector ROOK_POSITION;

    Roque(GameBoard board, Piece piece, Vector direction, Vector rookPosition){
        super(board, piece);
        assert direction != null && rookPosition != null;

        DEFAULT_KING_POSITION = piece.getPosition();
        DIRECTION = direction;
        ROOK_POSITION = rookPosition;
    }

    @Override
    public boolean check(Vector to) {
        if(done) return false;
        if(!to.equals(DEFAULT_KING_POSITION.add(DIRECTION.mult(2)))) return false;

        // Si le roi et la tour n'as pas bougé
        King king = (King) getPiece();
        Piece rook = getBoard().getPieceAt(ROOK_POSITION);
        if(king.hasMoved()) return false;
        if(rook == null || rook.getType() != PieceType.ROOK) return false;
        if(((Rook)rook).hasMoved()) return false;

        // Si aucune pièce se trouve entre le roi et la tour
        if(!checkNoPieceBetween(ROOK_POSITION)) return false;

        //si le roi ne peut pas être mis en echec sur le chemin
        Vector initPos = getPiece().getPosition();
        Vector initLastPos = getPiece().getLastPosition();


        getPiece().move(getPiece().getPosition().add(DIRECTION));
        if(getBoard().isCheck(getPiece().getColor())){
            getPiece().setLastPosition(initLastPos);
            getPiece().setPosition(initPos);
            return false;
        }


        getPiece().setLastPosition(initLastPos);
        getPiece().setPosition(initPos);

        // La règle est donc applicable.
        applyRule = getCanBeApplyed();
        this.rook = (Rook)rook;

        return true;
    }

    /**
     * Permet de bouger la tour si le GrandRoque ou PetitRoque a été fait.
     */
    @Override
    public void apply() {
        if(!getCanBeApplyed() || !applyRule) return;

        Vector newRookPosition = DEFAULT_KING_POSITION.add(DIRECTION);
        this.rook.move(newRookPosition);

        done = true;
        setCanBeApplyed(false);
        this.rook = null;
    }

    @Override
    public boolean canMove() {
        return check(getPiece().getPosition().add(DIRECTION));
    }
}
