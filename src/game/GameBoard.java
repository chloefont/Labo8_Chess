package game;

import chess.PieceType;
import chess.PlayerColor;
import engine.pieces.*;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private final int width = 8;
    private Piece[] pieces = new Piece[width * 4];
    Piece lastPieceToMove;
    private final PromotionQuestion promotionQuestion;

    public GameBoard(PromotionQuestion callback) {
        this.promotionQuestion = callback;
    }

    public Piece getPieceAt(Vector vector) {
        for (Piece piece : pieces) {
            if (piece != null && piece.getPosition().equals(vector))
                return piece;
        }
        return null;
    }

    public void setLastPieceToMove(Piece lastPieceToMove) {
        this.lastPieceToMove = lastPieceToMove;
    }

    public Piece getLastPieceToMove() {
        return lastPieceToMove;
    }

    public Piece[] getPiecesWithColor(PlayerColor color){
        List<Piece> piecesSameColor = new ArrayList<Piece>();

        for (Piece piece : pieces) {
            if(piece != null && piece.getColor() == color){
                piecesSameColor.add(piece);
            }
        }

        return piecesSameColor.toArray(new Piece[0]);
    }

    /**
     * Récupère le roi d'une couleur.
     * @param color La couleur souhaité.
     * @return  Référence sur le roi.
     */
    public Piece getKing(PlayerColor color){
        for (Piece piece : pieces) {
            if(piece != null && piece.getColor() == color && piece.getType() == PieceType.KING){
                return piece;
            }
        }
        return null;
    }

    /**
     * Place les pièces nécessaires pour jouer aux échecs.
     */
    void init() {
        PlayerColor color = PlayerColor.WHITE;
        int i = 0;
        int height = 1;

        for (int j = 0; j < 2; j++) {

            if (color == PlayerColor.BLACK)
                height = width - 2;


            for (int n = 0; n < width; n++) {
                pieces[i++] = new Pawn(this, color, new Vector(n, height));
            }

            if (color == PlayerColor.WHITE)
                height--;
            else
                height++;
            // Rooks
            pieces[i++] = new Rook(this, color, new Vector(0, height));
            pieces[i++] = new Rook(this, color, new Vector(width - 1, height));

            // Knights
            //pieces[i++] = new Knight(this, color, new Vector(1, height));
            //pieces[i++] = new Knight(this, color, new Vector(width - 2, height));

            // Bishops
            //pieces[i++] = new Bishop(this, color, new Vector(2, height));
            //pieces[i++] = new Bishop(this, color, new Vector(width - 3, height));

            //Queen
            //pieces[i++] = new Queen(this, color, new Vector(3, height));

            // King
            pieces[i++] = new King(this, color, new Vector(4, height));

            color = PlayerColor.getOpposite(color);
        }

    }

    public Piece[] getPieces() {
        return pieces;
    }

    public int getWidth() {
        return width;
    }


    /**
     * Permet de retirer une pièce du jeu.
     * @param piece
     */
    public void killPiece(Piece piece) {
        changePieceOnBoard(piece, null);
        piece.setDead(true);
    }

    /**
     * Permet de savoir si le roi d'une couleur est en échec.
     * @param color La couleur du roi.
     * @return Vrai si en échec.
     */
    public boolean isEchec(PlayerColor color){

        PlayerColor oppositeColor = PlayerColor.getOpposite(color);

        Piece[] oppositePieces = getPiecesWithColor(oppositeColor);

        Piece king = getKing(color);
        for (int i = 0; i < oppositePieces.length; i++ ) {
            Piece oppositePiece = oppositePieces[i];

            if(oppositePiece.checkMove(king.getPosition())){
                return true;
            }
        }
        return false;
    }

    /**
     * TODO
     * @param pawn
     * @param newPos
     */
    public void promotion(Piece pawn, Vector newPos) {
        Piece[] promotionPieces = {
                new Queen(this, pawn.getColor(), newPos),
                new Bishop(this, pawn.getColor(), newPos),
                new Knight(this, pawn.getColor(), newPos),
                new Rook(this, pawn.getColor(), newPos)
        };

        Piece newPiece;
        do {
            newPiece = promotionQuestion.handler(promotionPieces);
        } while (newPiece == null);

        killPiece(pawn);
        changePieceOnBoard(null, newPiece);
    }

    /**
     * Permet de mettre une nouvelle pièce à la place d'une autre.
     * @param toChange La pièce à changer.
     * @param newPiece La pièce de remplacement.
     */
    private void changePieceOnBoard(Piece toChange, Piece newPiece) {
        for (int i = 0; i < pieces.length; ++i) {
            if (pieces[i] == toChange) {
                pieces[i] = newPiece;
                return;
            }
        }
    }
}
