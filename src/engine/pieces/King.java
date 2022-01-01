package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import game.GameBoard;
import game.Vector;

public class King extends Piece {
    public King(GameBoard gameBoard, PlayerColor color, Vector position) {
        super(gameBoard, color, position);
    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }
}
