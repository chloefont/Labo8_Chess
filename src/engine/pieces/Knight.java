package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.rules.Movements.MoveL;
import engine.rules.Rule;
import game.GameBoard;
import game.Vector;

public class Knight extends Piece {

    public Knight(GameBoard board, PlayerColor color, Vector position) {
        super(board, color, position);

        Rule[] rules = {new MoveL(board, this)};
        setRules(rules);
    }

    public String toString() {
        return "Knight";
    }

    // Getters et setters
    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
    }
}
