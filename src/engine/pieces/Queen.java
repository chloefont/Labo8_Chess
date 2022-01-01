package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import game.GameBoard;
import game.Vector;

public class Queen extends Piece {
    public Queen(GameBoard gameBoard, PlayerColor color, Vector position) {
        super(gameBoard, color, position);
    }

    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }
}
