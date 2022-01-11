package engine.rules.Movements.specials;

import chess.PieceType;
import engine.pieces.Piece;
import engine.rules.Movements.Movement;
import game.GameBoard;
import game.Vector;

public class GrandRoque extends Movement {
    @Override
    public boolean check(GameBoard board, Piece piece, Vector to) {
        // Si le roi et la tour n'as pas bougé

        // Si aucune pièce se trouve entre le roi et la tour

        // modifier la place de la tour

        return false;

    }

    @Override
    public void apply() {

    }
}
