package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.rules.Movements.MoveLinear;
import engine.rules.Movements.Movement;
import engine.rules.Movements.specials.GrandRoque;
import engine.rules.Movements.specials.PetitRoque;
import game.GameBoard;
import game.Vector;

public class King extends Piece implements LinearMovement, HasMoved {
    int maxMove = 1;
    private boolean hasMoved = false;

    public King(GameBoard board, PlayerColor color, Vector position) {
        super(board, color, position);

        Movement[] movementRules = {
                new MoveLinear(new Vector(1,0), board, this),
                new MoveLinear(new Vector(0,1), board, this),
                new MoveLinear(new Vector(1,1), board, this),
                new MoveLinear(new Vector(1,-1), board, this),
                new PetitRoque(board, this),
                new GrandRoque(board, this)
        };
        setRules(movementRules);
    }

    @Override
    public void move(Vector to){
        super.move(to);
        hasMoved = true;
    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }

    @Override
    public int getMaxMove() {
        return maxMove;
    }

    public String toString() {
        return "King";
    }

    @Override
    public boolean hasMoved() {
        return hasMoved;
    }
}
