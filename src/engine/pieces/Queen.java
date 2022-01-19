package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.rules.Movements.MoveLinear;
import engine.rules.Rule;
import game.GameBoard;
import game.Vector;

public class Queen extends Piece {

    public Queen(GameBoard board, PlayerColor color, Vector position) {
        super(board, color, position);

        final Rule[] rules = {
                new MoveLinear(new Vector(1, 0), board, this),
                new MoveLinear(new Vector(0, 1), board, this),
                new MoveLinear(new Vector(1, 1), board, this),
                new MoveLinear(new Vector(1, -1), board, this),
        };
        setRules(rules);
    }

    @Override
    public String toString() {
        return "Queen";
    }

    // Getters et setters
    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }

}
