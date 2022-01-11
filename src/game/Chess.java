package game;

import chess.PlayerColor;
import engine.pieces.Piece;

public class Chess {
    private final GameBoard board;

    public Chess(GameBoard board){
        this.board = board;
    }

    public boolean isEchec(PlayerColor color){

        PlayerColor oppositeColor = GameBoard.getOppositeColor(color);

        Piece[] oppositePieces = board.getPiecesWithColor(oppositeColor);

        Piece king = board.getKing(color);
        for (int i = 0; i < oppositePieces.length; i++ ) {
            Piece oppositePiece = oppositePieces[i];

            if(oppositePiece.checkMove(king.getPosition())){
                return true;
            }
        }
        return false;
    }
}
