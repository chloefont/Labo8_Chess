package engine.rules.Movements;

import engine.pieces.LinearMovement;
import engine.pieces.Piece;
import game.GameBoard;
import game.Vector;

public class MoveLinear extends Movement {
    private final Vector direction;

    // pour ne pas tout casser;
    MoveLinear(){
        direction = new Vector(0,0);
    }

    MoveLinear(Vector direction){
        this.direction = direction;
    }


    @Override
    public boolean check(GameBoard board, Piece piece, Vector to) {
        // On check que le destination se trouve bien dans la bonne direction et qu'aucun pion ne se trouve entre la piece et la destination.
        return direction.colinear(to.sub(piece.getPosition())) && super.checkNoPieceBetween(board, piece, to);
    }
}
