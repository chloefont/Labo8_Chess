package game;

import chess.*;
import engine.pieces.Piece;

import java.beans.PropertyEditorManager;

public class Controller implements ChessController {
    private final GameBoard gameBoard;
    private PlayerColor tourJoueur = PlayerColor.WHITE;
    private ChessView view;
    private int width = 8;

    public Controller() {
        gameBoard = new GameBoard(this);
    }

    @Override
    public void start(ChessView view) {
        this.view = view;
        initDisplay();

        view.startView();
    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {

        Piece piece = gameBoard.getPiece(new Vector(fromX, fromY));

        if (piece == null){
            view.displayMessage("Aucune pièce choisi !");
            return false;
        }

        if(piece.getColor() != tourJoueur){
            view.displayMessage("Ce n'est pas votre tour !");
            return false;
        }

        if (!piece.checkMove(new Vector(toX, toY))) {
            view.displayMessage("Vous ne pouvez pas déplacer votre pièce ici");
            return false;
        }

        Piece other = null;
        if (piece.isOnBoard())
            other = gameBoard.getPiece(new Vector(toX, toY));

        // déplace la pièce si tout est validé
        piece.move(new Vector(toX, toY));

        // Check si echec
        if(gameBoard.isEchec(tourJoueur)){
            view.displayMessage("Vous mettez votre roi en danger !");
            piece.move(piece.getLastPosition());
            return false;
        }

        if (other != null) {
            gameBoard.onDeath(other);
        }

        updateView();

        tourJoueur = GameBoard.getOppositeColor(tourJoueur);

        return true;
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

    protected void deathPiece(Vector at) {
        if (at != null)
            view.removePiece(at.getX(), at.getY());
    }

    protected Piece promotionQuestion(Piece[] promotionPieces) {

        return view.askUser("Promotion", "Your pawn is promoted. Please choose a new piece.", promotionPieces);
    }

    protected void updateView(){
        for(int i = 0; i < gameBoard.getWidth(); i++){
            for(int j = 0; j < gameBoard.getWidth(); j++){
                view.removePiece(i,j);
            }
        }
        for (Piece p: gameBoard.getPieces()) {
            if(p == null) continue;
            view.putPiece(p.getType(), p.getColor(), p.getPosition().getX(), p.getPosition().getY());
        }
    }
}
