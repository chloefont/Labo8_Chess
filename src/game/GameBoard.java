package game;

import chess.PieceType;
import chess.PlayerColor;
import engine.pieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBoard {
    private final int width = 8;
    private boolean gameFinished = false;
    private Piece[] pieces = new Piece[width * 4];
    Controller controller;
    Piece lastPieceToMove;

    //TODO peut-être faire ton truc de callback à la place pour la fonction onDeath
    public GameBoard(Controller controller) {
        this.controller = controller;
    }

//    public GameBoard(GameBoard gameBoard){
//        gameFinished = gameBoard.gameFinished;
//        pieces = (Piece[]) Arrays.stream(gameBoard.pieces).map(piece -> piece.copy()).toArray();
//        controller = gameBoard.controller;
//    }

    public Piece getPiece (Vector vector) {
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
            if(piece != null && piece.getColor() == color && piece instanceof King){
                return piece;
            }
        }
        return null;
    }

    //TODO faudra peut-être la changer de place
    static public PlayerColor getOppositeColor(PlayerColor color) {
        if (color == PlayerColor.WHITE)
            return PlayerColor.BLACK;
        return PlayerColor.WHITE;
    }

    void init() {
        PlayerColor color = PlayerColor.WHITE;
        int i = 0;
        int height = 1;

        for (int j = 0; j < 2; j++) {

            if (color == PlayerColor.BLACK)
                height = width - 2;


            for (int n = 0; n < width; n++) {
                //pieces[i++] = new Pawn(this, color, new Vector(n, height));
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

            color = getOppositeColor(color);
        }

    }

    public Piece[] getPieces() {
        return pieces;
    }

    public int getWidth() {
        return width;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public void onDeath(Piece piece) {
        if (piece.getType() == PieceType.KING)
            gameFinished = true;

        controller.deathPiece(piece.getPosition());
        for (int i = 0; i < pieces.length; ++i) {
            //TODO comment on peut supprimer la pièce autrement
            if (piece == pieces[i]) {
                pieces[i] = null;
                piece.setOnBoard(false);
            }
        }
    }

    public void promotion(Piece pawn, Vector newPos) {
        Piece[] promotionPieces = {
                new Queen(this, pawn.getColor(), newPos),
                new Bishop(this, pawn.getColor(), newPos),
                new Knight(this, pawn.getColor(), newPos),
                new Rook(this, pawn.getColor(), newPos)
        };

        Piece newPiece;
        do {
            newPiece = controller.promotionQuestion(promotionPieces);
        } while (newPiece == null);

        onDeath(pawn);

        for (int i = 0; i < pieces.length; ++i) {
            if (pieces[i] == null) {
                pieces[i] = newPiece;
                return;
            }
        }
    }
}
