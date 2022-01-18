package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.rules.Movements.MoveLinear;
import engine.rules.Movements.GrandRoque;
import engine.rules.Movements.PetitRoque;
import engine.rules.Rule;
import game.GameBoard;
import game.Vector;

public class King extends Piece implements LimitedMovement, HasMoved {
    private final int MAX_MOVE = 1;
    private boolean hasMoved = false;

    public King(GameBoard board, PlayerColor color, Vector position) {
        super(board, color, position);

        Rule[] rules = {
                new MoveLinear(new Vector(1,0), board, this),
                new MoveLinear(new Vector(0,1), board, this),
                new MoveLinear(new Vector(1,1), board, this),
                new MoveLinear(new Vector(1,-1), board, this),
                new PetitRoque(board, this),
                new GrandRoque(board, this)
        };
        setRules(rules);
    }

    @Override
    public void move(Vector to){
        super.move(to);
        hasMoved = true;
    }

    public String toString() {
        return "King";
    }

    // Getters et setters
    @Override
    public PieceType getType() {
        return PieceType.KING;
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
