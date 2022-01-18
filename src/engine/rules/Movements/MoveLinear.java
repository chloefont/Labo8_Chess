package engine.rules.Movements;

import engine.pieces.LimitedMovement;
import engine.pieces.Piece;
import game.GameBoard;
import game.Vector;

public class MoveLinear extends Movement {
    private final Vector VECTOR;
    private boolean shouldBeInDirection;
    private boolean canKill = true;

    /**
     * Constructeur
     * @param vector Vecteur directionnel (avec x et y égaux à 0 ou 1)
     * @param board Plateau de jeu
     * @param piece Pièce
     */
    public MoveLinear(Vector vector, GameBoard board, Piece piece){
        super(board, piece);
        assert  (vector.getX() == 0 || vector.getX() == 1) &&
                (vector.getY() == 0 || vector.getY() == 1);

        this.VECTOR = vector;
    }

    /**
     *
     * @param vector Vecteur directionnel (avec x et y égaux à 0 ou 1)
     * @param shouldBeInDirection Vrai si le déplacement doit respecter le sens
     *                            du vecteur
     * @param canKill Vrai si la pièce peut tuer en utilisant ce déplacement
     * @param board Plateau de jeu
     * @param piece Pièce
     */
    public MoveLinear(Vector vector, boolean shouldBeInDirection, boolean canKill, GameBoard board, Piece piece) {
        this(vector, board, piece);
        this.shouldBeInDirection = shouldBeInDirection;
        this.canKill = canKill;
    }

    /**
     * Permet de vérifier si le déplacement jusqu'à la case to est accepté dans
     * les 2 sens du vecteur directeur.
     * @param to Case finale
     * @return Vrai si accepté
     */
    private boolean bothDirectionOk(Vector to) {
        if (getPiece() instanceof LimitedMovement) {
            Vector diff = to.sub(getPiece().getPosition());
            // Le déplacement est refusé si la pièce se déplace de plus que son
            // déplacement maximal
            if (diff.norm() / VECTOR.norm() > ((LimitedMovement) getPiece()).getMaxMove())
                return false;
        }
        // On check que la destination se trouve bien dans la bonne direction et
        // qu'aucun pion ne se trouve entre la piece et la destination.
        return VECTOR.colinear(to.sub(getPiece().getPosition())) &&
                checkPieceAtSamePlace(getBoard().getPieceAt(to), to, canKill) &&
                checkNoPieceBetween(to);
    }

    @Override
    public boolean check(Vector to) {
        if (shouldBeInDirection) {
            Vector diff = to.sub(getPiece().getPosition());
            return bothDirectionOk(to) && diff.sameDirection(VECTOR);
        }

        return bothDirectionOk(to);
    }

    @Override
    public boolean canMove() {
        Vector vecSameDir = getPiece().getPosition().add(VECTOR);
        Vector vecOppDir = getPiece().getPosition().sub(VECTOR);

        if (shouldBeInDirection) {
            return getBoard().isOnBoard(vecSameDir) && getBoard().getPieceAt(vecSameDir) == null;
        }

        return (getBoard().isOnBoard(vecSameDir) && getBoard().getPieceAt(vecSameDir) == null)
                || (getBoard().isOnBoard(vecOppDir) && getBoard().getPieceAt(vecOppDir) == null);
    }
}
