package game;

import chess.*;
import engine.pieces.Piece;

/**
 * Cette class fait le lien entre l'interface et la logique.
 */
public class Controller implements ChessController {
    private final GameBoard gameBoard;
    private PlayerColor tourJoueur = PlayerColor.WHITE;
    private ChessView view;
    private final int WIDTH = 8;


    public Controller() {
        gameBoard = new GameBoard(this::promotionQuestion);
    }

    @Override
    public void start(ChessView view) {
        this.view = view;
        initDisplay();

        view.startView();
    }

    @Override
    public boolean move(int fromX, int fromY, int toX, int toY) {

        Piece piece = gameBoard.getPieceAt(new Vector(fromX, fromY));

        switch (gameBoard.getGameState(tourJoueur)) {
            case PAT -> {
                view.displayMessage("Vous êtes en situation d'égalité.");
                return false;
            }
            case CHECK_MATE -> {
                view.displayMessage("ECHEC ET MAT !!!");
                return false;
            }
        }

        if (piece == null){
            view.displayMessage("Aucune pièce choisi !");
            return false;
        }

        if (piece.getColor() != tourJoueur){
            view.displayMessage("Ce n'est pas votre tour !");
            return false;
        }


        if (!piece.checkMove(new Vector(toX, toY))) {
            view.displayMessage("Vous ne pouvez pas déplacer votre pièce ici");
            return false;
        }

        Piece other = null;
        if (!piece.isDead())
            other = gameBoard.getPieceAt(new Vector(toX, toY));

        // déplace la pièce si tout est validé
        piece.move(new Vector(toX, toY));

        if (gameBoard.isEchec(tourJoueur)) {
            view.displayMessage("Vous mettez votre roi en danger !");
            piece.move(piece.getLastPosition());
            return false;
        }



        if (other != null) {
            gameBoard.killPiece(other);
        }

        removeAllPiecesFromBoard();
        showPiecesOnBoard();

        tourJoueur = GameBoard.getOppositeColor(tourJoueur);


        return true;
    }

    @Override
    public void newGame() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < WIDTH; j++)
                view.removePiece(i, j);
        }
        tourJoueur = PlayerColor.WHITE;
        initDisplay();
    }

    /**
     * Initialise le GameBoard et affiche les pièces sur l'interface.
     */
    private void initDisplay() {
        gameBoard.init();
        showPiecesOnBoard();
    }

    //TODO rendre abstraite ?
    /**
     * Questionne l'utilisateur sur la pièce qu'il veut récupérer à la place de
     * son pion promu.
     * @param promotionPieces la liste de pièce que t'utilisateur peut choisir
     * @return la pièce choisie par l'utilisateur
     */
    protected Piece promotionQuestion(Piece[] promotionPieces) {
        return view.askUser("Promotion", "Votre pion est promi. " +
                "Choisissez une nouvelle pièce.", promotionPieces);
    }

    /**
     * Affiche les pièces du GameBoard sur l'interface.
     */
    protected void showPiecesOnBoard(){
        for (Piece p: gameBoard.getPIECES()) {
            if(p == null) continue;
            view.putPiece(p.getType(), p.getColor(), p.getPosition().getX(),
                    p.getPosition().getY());
        }
    }

    /**
     * Retire toutes les pièces de l'interface
     */
    protected void removeAllPiecesFromBoard(){
        for(int i = 0; i < gameBoard.getWIDTH(); i++){
            for(int j = 0; j < gameBoard.getWIDTH(); j++){
                view.removePiece(i,j);
            }
        }
    }
}
