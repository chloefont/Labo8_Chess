package game;

import chess.PieceType;
import chess.PlayerColor;
import engine.pieces.*;

public class GameBoard {
    private final int width = 8;
    private boolean gameFinished = false;
    private Piece[] pieces = new Piece[width * 4];

    Piece getPiece (Vector vector) {
        for (Piece piece : pieces) {
            if  (piece.getPosition().equals(vector))
                return piece;
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
            pieces[i++] = new Knight(this, color, new Vector(1, height));
            pieces[i++] = new Knight(this, color, new Vector(width - 2, height));

            // Bishops
            pieces[i++] = new Bishop(this, color, new Vector(2, height));
            pieces[i++] = new Bishop(this, color, new Vector(width - 3, height));

            // King
            pieces[i++] = new King(this, color, new Vector(3, height));

            //Queen
            pieces[i++] = new Queen(this, color, new Vector(4, height));

            color = getOppositeColor(color);
        }

    }

    public Piece[] getPieces() {
        return pieces;
    }
}
