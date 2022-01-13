package game;

import chess.PieceType;
import chess.PlayerColor;
import engine.pieces.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private final int width = 8;
    private final Piece[] pieces = new Piece[width * 4];
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
        List<Piece> piecesSameColor = new ArrayList<>();

        for (Piece piece : pieces) {
            if(piece != null && piece.getColor() == color){
                piecesSameColor.add(piece);
            }
        }

        return piecesSameColor.toArray(new Piece[0]);
    }

    /**
     * Récupère le roi d'une couleur.
     * @param color La couleur souhaitée.
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

//        for (int j = 0; j < 2; j++) {
//
//            if (color == PlayerColor.BLACK)
//                height = width - 2;
//
//
//            for (int n = 0; n < width; n++) {
//                //pieces[i++] = new Pawn(this, color, new Vector(n, height));
//            }
//
//            if (color == PlayerColor.WHITE)
//                height--;
//            else
//                height++;
//            // Rooks
//            //pieces[i++] = new Rook(this, color, new Vector(0, height));
//            //pieces[i++] = new Rook(this, color, new Vector(width - 1, height));
//
//            // Knights
//            //pieces[i++] = new Knight(this, color, new Vector(1, height));
//            //pieces[i++] = new Knight(this, color, new Vector(width - 2, height));
//
//            // Bishops
//            //pieces[i++] = new Bishop(this, color, new Vector(2, height));
//            //pieces[i++] = new Bishop(this, color, new Vector(width - 3, height));
//
//            //Queen
//            //pieces[i++] = new Queen(this, color, new Vector(3, height));
//
//            // King
//            pieces[i++] = new King(this, color, new Vector(4, height));
//
//            color = PlayerColor.getOpposite(color);
//        }
        pieces[i++] = new King(this, PlayerColor.BLACK, new Vector(7, 7));
        pieces[i++] = new King(this, PlayerColor.WHITE, new Vector(4, 0));
        pieces[i++] = new Rook(this, PlayerColor.WHITE, new Vector(0, 7));
        pieces[i++] = new Rook(this, PlayerColor.WHITE, new Vector(7, 0));
        pieces[i++] = new Queen(this, PlayerColor.WHITE, new Vector(6, 0));


    }

    public Piece[] getPieces() {
        return pieces;
    }

    public int getWidth() {
        return width;
    }


    /**
     * Permet de retirer une pièce du jeu.
     * @param piece La pièce à tuer.
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

        PlayerColor oppositeColor = GameBoard.getOppositeColor(color);

        Piece[] oppositePieces = getPiecesWithColor(oppositeColor);

        Piece king = getKing(color);
        for (Piece oppositePiece : oppositePieces) {
            if (oppositePiece.checkMove(king.getPosition())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Permet de vérifier s'il y a échec et mat.
     * @param color La couleur du roi.
     * @return Vrai si échec et mat.
     */
    public boolean isEchecEtMat(PlayerColor color){

        if(!isEchec(color) && getPiecesWithColor(color).length > 1) return false;

        return canKingMove(color);
    }

    private boolean canKingMove(PlayerColor color){
        Piece king = getKing(color);
        final Vector originalPos = king.getPosition();

        final Vector startPos = king.getPosition().add(new Vector(-1, 1));
        Vector posToCheck;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                posToCheck = startPos.add(new Vector(j, -i));

                if((i == 1 && j == 1) || !isOnBoard(posToCheck) || getPieceAt(posToCheck) != null){
                    continue;
                }
                king.setPosition(posToCheck);
                if(!isEchec(king.getColor())){
                    king.setPosition(originalPos);
                    return false;
                }
            }
        }
        king.setPosition(originalPos);
        return true;
    }

    /**
     * permet de savoir si une case se trouve sur le plateau. TODO vérifier que ça fonctionne bien.
     * @param vector La case à vérifier.
     * @return Vrai si la case est sur le plateau.
     */
    private boolean isOnBoard(Vector vector) {
        final Rectangle board = new Rectangle(0, 0, 8, 8);
        return board.contains(vector.getX(), vector.getY());
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

    /**
     * Retourne la couleur opposée.
     * @param color une Couleur.
     * @return Couleur opposée.
     */
    public static PlayerColor getOppositeColor(PlayerColor color){
        return color == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
    }
}
