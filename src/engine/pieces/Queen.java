package engine.pieces;

import chess.PieceType;
import chess.PlayerColor;
import engine.rules.Movements.MoveLinear;
import engine.rules.Movements.Movement;
import game.GameBoard;
import game.Vector;

public class Queen extends Piece implements LinearMovement {
    int maxMove = getGameBoard().getWidth();

    public Queen(GameBoard gameBoard, PlayerColor color, Vector position) {
        super(gameBoard, color, position);

        // TODO il faut faire ça avec toutes les pièces
        // ici je fais comme dans l'exemple que je t'avais donné. La reine peut bouger comme ça: / \ | --
        final Movement[] movementRules = {
                new MoveLinear(new Vector(1,0)),
                new MoveLinear(new Vector(0,1)),
                new MoveLinear(new Vector(1,1)),
                new MoveLinear(new Vector(1,-1)),
        };
        setMovementRules(movementRules);
    }

    @Override
    public PieceType getType() {
        return PieceType.QUEEN;
    }

    @Override
    public int getMaxMove() {
        return maxMove;
    }
}
