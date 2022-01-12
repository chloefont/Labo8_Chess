package game;

import engine.pieces.Piece;

public interface PromotionQuestion {
    Piece handler(Piece[] piece);
}
