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
    public King(GameBoard gameBoard, PlayerColor color, Vector position) {
        super(gameBoard, color, position);

        Movement[] movementRules = {
                new MoveLinear(new Vector(1,0)),
                new MoveLinear(new Vector(0,1)),
                new MoveLinear(new Vector(1,1)),
                new MoveLinear(new Vector(1,-1)),
                new PetitRoque(),
                new GrandRoque()
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
        return false;
    }
}
