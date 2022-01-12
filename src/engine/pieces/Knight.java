package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.rules.Movements.MoveL;
import engine.rules.Movements.Movement;
import game.GameBoard;
import game.Vector;

public class Knight extends Piece {
    public Knight(GameBoard board, PlayerColor color, Vector position) {
        super(board, color, position);

        Movement[] movementRules = {new MoveL(board, this)};
        setRules(movementRules);
    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }

    public String toString() {
        return "Knight";
    }
}
