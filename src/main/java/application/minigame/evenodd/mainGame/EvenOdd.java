package application.minigame.evenodd.mainGame;

import application.minigame.evenodd.mvc.EOView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class EvenOdd extends Application {

    //variabili per la grandezza della schermata
    public final static int GRID_DIM = 6;
    private static final int SCENE_WIDTH = 600;
    private static final int SCENE_HEIGHT = 480;

    //creo un istanza del controller
    public static final EOView view = new EOView(GRID_DIM);

    //avvio della finestra del programma
    @Override
    public void start(final Stage primaryStage) {

        /**
         * Setto lo stage principale
         */

        view.setStage(primaryStage);

        primaryStage.setTitle("TicTacToe");

        /**
         * Il controllore mi ritorna La griglia coi bottoni
         */
        StackPane root = view.createPane();

        primaryStage.setScene(new Scene(root, SCENE_WIDTH, SCENE_HEIGHT));
       primaryStage.setResizable(false);
        primaryStage.show();


    }

    public static void main(final String[] args) {
        launch(args);
    }


}
