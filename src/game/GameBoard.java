package game;

import chess.PieceType;
import chess.PlayerColor;
import engine.pieces.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private final int WIDTH = 8;
    private final Piece[] PIECES = new Piece[WIDTH * 4];
    Piece lastPieceMoved;
    private final PromotionQuestion PROMOTION_QUESTION;
    public enum GameState {
        NONE,
        CHECKMATE,
        PAT
    }

    public GameBoard(PromotionQuestion callback) {
        this.PROMOTION_QUESTION = callback;
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
                height = WIDTH - 2;


            for (int n = 0; n < WIDTH; n++) {
                PIECES[i++] = new Pawn(this, color, new Vector(n, height));
            }

            if (color == PlayerColor.WHITE)
                height--;
            else
                height++;
            // Rooks
            PIECES[i++] = new Rook(this, color, new Vector(0, height));
            PIECES[i++] = new Rook(this, color, new Vector(WIDTH - 1, height));

            // Knights
            PIECES[i++] = new Knight(this, color, new Vector(1, height));
            PIECES[i++] = new Knight(this, color, new Vector(WIDTH - 2, height));

            // Bishops
            PIECES[i++] = new Bishop(this, color, new Vector(2, height));
            PIECES[i++] = new Bishop(this, color, new Vector(WIDTH - 3, height));

            //Queen
            PIECES[i++] = new Queen(this, color, new Vector(3, height));

            // King
            PIECES[i++] = new King(this, color, new Vector(4, height));

            color = GameBoard.getOppositeColor(color);
        }
    }

    /**
     * Retourne la pièce se trouver à une position du plateau de jeu
     * @param vector position
     * @return la pièce ou null s'il n'y a pas de pièce à cette position
     */
    public Piece getPieceAt(Vector vector) {
        assert vector != null;

        for (Piece piece : PIECES) {
            if (piece != null && piece.getPosition().equals(vector))
                return piece;
        }
        return null;
    }

    /**
     * Retourne un tableau contenant toutes les pièces en jeu de la couleur
     * souhaitée.
     * @param color couleur des pièces à retourner
     * @return tableau de pièces
     */
    public Piece[] getPiecesWithColor(PlayerColor color){
        assert color != null;

        List<Piece> piecesSameColor = new ArrayList<>();

        for (Piece piece : PIECES) {
            if(piece != null && piece.getColor() == color){
                piecesSameColor.add(piece);
            }
        }

        return piecesSameColor.toArray(new Piece[0]);
    }

    /**
     * Récupère le roi de la couleur précisée.
     * @param color La couleur souhaitée.
     * @return  Référence sur le roi.
     */
    public Piece getKing(PlayerColor color){
        assert color != null;

        for (Piece piece : PIECES) {
            if(piece != null && piece.getColor() == color && piece.getType() == PieceType.KING){
                return piece;
            }
        }
        return null;
    }

    /**
     * Permet de retirer une pièce du jeu.
     * @param piece La pièce à tuer.
     */
    public void killPiece(Piece piece) {
        assert piece != null;

        changePieceOnBoard(piece, null);
        piece.setDead(true);
    }

    /**
     * Permet de récupérer l'état actuel du jeu concernant une certaine couleur.
     * @param color la couleur du joueur
     * @return l'état du jeu (échec et mat, pat ou rien)
     */
    public GameState getGameState(PlayerColor color) {
        assert color != null;

        if (isCheckmate(color))
            return GameState.CHECKMATE;

        if (isPat(color))
            return GameState.PAT;

        return GameState.NONE;
    }

    /**
     * Permet de savoir si le roi d'une couleur est en échec.
     * @param color La couleur du roi.
     * @return Vrai si en échec.
     */
    public boolean isCheck(PlayerColor color){
        assert color != null;

        PlayerColor oppositeColor = GameBoard.getOppositeColor(color);
        Piece[] oppositePieces = getPiecesWithColor(oppositeColor);
        Piece king = getKing(color);

        if (oppositePieces == null) return false;

        for (Piece oppositePiece : oppositePieces) {
            if (oppositePiece != null && oppositePiece.checkMove(king.getPosition())) {
                return !oppositePiece.getPosition().equals(king.getPosition());
            }
        }
        return false;
    }

    /**
     * Permet de savoir si le joueur d'une certaine couleur induit une situation
     * d'égalité (pat).
     * @param color la couleur du joueur en question
     * @return vrai s'il y pat, faux autrement
     */
    private boolean isPat(PlayerColor color) {
        assert color != null;

        boolean canMove = false;
        for (Piece piece : getPiecesWithColor(color)) {
            if (piece != null && piece.getType() != PieceType.KING && piece.canMove())
                canMove = true;
        }
        return  !canMove && kingCannotMove(color) && !isCheck(color);
    }

    /**
     * Permet de vérifier s'il y a échec et mat.
     * @param color La couleur du roi.
     * @return Vrai si échec et mat.
     */
    private boolean isCheckmate(PlayerColor color){
        assert color != null;

        return isCheck(color) && kingCannotMove(color);
    }

    /**
     * Informe sur la possibilité du roi d'une certaine couleur de bouger
     * (également par rapport aux positions qui le mettraient échec).
     * @param color La couleur du roi
     * @return Vrai s'il ne peut pas bouger.
     */
    private boolean kingCannotMove(PlayerColor color){
        assert color != null;

        Piece king = getKing(color);
        assert king != null;

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
                if(!isCheck(king.getColor())){
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
     * @param position La case à vérifier.
     * @return Vrai si la case est sur le plateau.
     */
    public boolean isOnBoard(Vector position) {
        assert position != null;

        final Rectangle board = new Rectangle(0, 0, WIDTH, WIDTH);
        return board.contains(position.getX(), position.getY());
    }

    /**
     * Applique la promtion
     * @param pawn Le pion promu
     * @param position Position sur laquelle la nouvelle pièce devra être placée
     */
    public void promotion(Piece pawn, Vector position) {
        assert pawn != null && position != null;

        Piece[] promotionPieces = {
                new Queen(this, pawn.getColor(), position),
                new Bishop(this, pawn.getColor(), position),
                new Knight(this, pawn.getColor(), position),
                new Rook(this, pawn.getColor(), position)
        };

        Piece newPiece;
        do {
            newPiece = PROMOTION_QUESTION.handler(promotionPieces);
        } while (newPiece == null);

        killPiece(pawn);
        changePieceOnBoard(null, newPiece);
    }

    /**
     * Permet de remplacer une pièce par une autre (seulement dans le tableau
     * de pièce, les positions ne sont pas modifiées).
     * @param toChange La pièce à changer.
     * @param newPiece La pièce de remplacement.
     */
    private void changePieceOnBoard(Piece toChange, Piece newPiece) {
        // On ne fait pas de vérification != null sur les paramètres car on veut
        // pouvoir remplacer une pièce (ou un null) par une référence null
        // (ou une pièce).
        for (int i = 0; i < PIECES.length; ++i) {
            if (PIECES[i] == toChange) {
                PIECES[i] = newPiece;
                return;
            }
        }
    }

    /**
     * Retourne la couleur opposée.
     * @param color Couleur.
     * @return Couleur opposée.
     */
    public static PlayerColor getOppositeColor(PlayerColor color){
        return color == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
    }

    // Getters et setters
    public void setLastPieceMoved(Piece lastPieceMoved) {
        this.lastPieceMoved = lastPieceMoved;
    }

    public Piece getLastPieceMoved() {
        return lastPieceMoved;
    }

    public Piece[] getPieces() {
        return PIECES;
    }

    public int getWidth() {
        return WIDTH;
    }
}
