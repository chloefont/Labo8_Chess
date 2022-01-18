package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.rules.Movements.MoveLinear;
import engine.rules.Rule;
import game.GameBoard;
import game.Vector;

public class Rook extends Piece implements LimitedMovement, HasMoved {
    private boolean hasMoved = false;
    private final int MAX_MOVE = getBoard().getWidth();

    public Rook(GameBoard board, PlayerColor color, Vector position) {
        super(board, color, position);

        Rule[] rules = {
                new MoveLinear(new Vector(1, 0), board, this),
                new MoveLinear(new Vector(0, 1), board, this),
        };
        setRules(rules);
    }

    @Override
    public void move(Vector to){
        super.move(to);
        hasMoved = true;
    }

    public String toString() {
        return "Rook";
    }

    // Getters et setters
    @Override
    public PieceType getType() {
        return PieceType.ROOK;
    }

    @Override
    public int getMaxMove() {
        return MAX_MOVE;
    }

    @Override
    public boolean hasMoved() {
        return hasMoved;
    }
}
