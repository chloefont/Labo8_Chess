package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.rules.Movements.MoveL;
import engine.rules.Movements.Movement;
import game.GameBoard;
import game.Vector;

public class Knight extends Piece {
    public Knight(GameBoard gameBoard, PlayerColor color, Vector position) {
        super(gameBoard, color, position);

        Movement[] movementRules = {new MoveL()};
        setRules(movementRules);
    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }
}
