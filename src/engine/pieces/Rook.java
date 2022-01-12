package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.rules.Movements.MoveLinear;
import engine.rules.Movements.Movement;
import game.GameBoard;
import game.Vector;

public class Rook extends Piece implements LinearMovement, HasMoved {
    int maxMove = getBoard().getWidth();
    private boolean hasMoved = false;

    public Rook(GameBoard board, PlayerColor color, Vector position) {
        super(board, color, position);

        Movement[] movementRules = {
                new MoveLinear(new Vector(1, 0), board, this),
                new MoveLinear(new Vector(0, 1), board, this),
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
        return PieceType.ROOK;
    }

    @Override
    public int getMaxMove() {
        return maxMove;
    }

    public String toString() {
        return "Rook";
    }

    @Override
    public boolean hasMoved() {
        return hasMoved;
    }
}
