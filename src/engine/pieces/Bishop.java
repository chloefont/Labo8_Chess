package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.rules.Movements.MoveLinear;
import engine.rules.Rule;
import game.GameBoard;
import game.Vector;

public class Bishop extends Piece {

    public Bishop(GameBoard board, PlayerColor color, Vector position) {
        super(board, color, position);

        Rule[] rules = {
                new MoveLinear(new Vector(1, 1), board, this),
                new MoveLinear(new Vector(-1, 1), board, this),
        };
        setRules(rules);
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }

    public String toString() {
        return "Bishop";
    }
}
