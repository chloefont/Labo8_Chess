import chess.ChessController;
import chess.ChessView;
import chess.views.console.ConsoleView;
import chess.views.gui.GUIView;
import game.*;

public class Main {
    public static void main(String[] args) {
        ChessController controller = new Controller();

        ChessView view = new GUIView(controller);
        //ChessView view = new ConsoleView(controller);

        controller.start(view);
    }
}
