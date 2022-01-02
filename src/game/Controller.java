package game;

import chess.*;
import engine.pieces.Piece;

public class Controller implements ChessController {
    private final GameBoard gameBoard;
    private PlayerColor tourJoueur = PlayerColor.WHITE;
    private ChessView view;
    private int width = 8;

    public Controller() {
        gameBoard = new GameBoard();
    }

    @Override
    public void start(ChessView view) {
        this.view = view;
        initDisplay();

        view.startView();
    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {
        tourJoueur = GameBoard.getOppositeColor(tourJoueur);

        Piece piece = gameBoard.getPiece(new Vector(fromX, fromY));

        if (piece == null)
            return false;

        Vector oldPosition = piece.getPosition();
        if (piece.move(new Vector(toX, toY))) {
            view.removePiece(oldPosition.getX(), oldPosition.getY());
            view.putPiece(piece.getType(), piece.getColor(), piece.getPosition().getX(), piece.getPosition().getY());
            return true;
        } else {
            view.displayMessage("Vous ne pouvez pas déplacer votre pièce ici");
            return false;
        }
    }

    @Override
    public void newGame() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++)
                view.removePiece(i, j);
        }

        initDisplay();
    }

    private void initDisplay() {
        gameBoard.init();
        for (Piece piece : gameBoard.getPieces()) {
            if (piece != null)
                view.putPiece(piece.getType(), piece.getColor(), piece.getPosition().getX(), piece.getPosition().getY());
        }
    }
}
