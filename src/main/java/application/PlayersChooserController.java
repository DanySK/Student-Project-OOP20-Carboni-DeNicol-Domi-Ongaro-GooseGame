package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Player;
import view.GamesViewType;
import view.SceneStarter;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class PlayersChooserController implements Initializable {
    @FXML
    private Button startButton;
    @FXML
    private TextField name1, name2, name3, name4;
    @FXML
    private CheckBox active1, active2, active3, active4;
    @FXML
    private Label errorLabel;

    final private Map<CheckBox, TextField> playersSelectionMap = new HashMap<>();
    final private List<Player> playersList = new ArrayList<>();

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        initializeMap();
        initializeEventHandlers();
    }

    /**
     * Initializes players selection map
     */
    private void initializeMap(){
        playersSelectionMap.put(active1, name1);
        playersSelectionMap.put(active2, name2);
        playersSelectionMap.put(active3, name3);
        playersSelectionMap.put(active4, name4);
    }

    /**
     * Initializes event handlers (mouse click on start)
     */
    private void initializeEventHandlers() {
        startButton.setOnMouseClicked(e -> {
            //number of players enabled
            final int numEnabled = playersSelectionMap.keySet().stream().mapToInt(cb -> cb.isSelected() ? 1 : 0).sum();

            //number of unique names (only of the enabled player)
            int numNames = (int) playersSelectionMap.entrySet().stream().filter(en -> en.getKey().isSelected() &&
                    !en.getValue().getText().trim().isEmpty()).map(en -> en.getValue().getText())
                    .distinct().count();

            if (numEnabled < 2){
                errorLabel.setText("YOU MUST ENTER AT LEAST 2 PLAYERS");
            } else if (numEnabled == numNames) {
                playersSelectionMap.forEach( (cb, n) -> {
                    if(cb.isSelected()) {
                        playersList.add(new Player(n.getText()));
                    }
                });
                
                final Stage newStage = new Stage();
                final Stage s = (Stage) errorLabel.getParent().getScene().getWindow();
                s.close();
                newStage.initModality(Modality.APPLICATION_MODAL);
                newStage.setMinHeight(600);
                newStage.setMinWidth(800);
                final SceneStarter gameScene = new SceneStarter(GamesViewType.GAME);
                try {
                	gameScene.start(newStage);
                } catch (Exception ex){
                	System.out.println(ex);
                    System.out.println("Errore nell'apertura");
                }
            }
            else{
                errorLabel.setText("EVERY PLAYER MUST HAVE AN UNIQUE NAME");
            }
        });
    }
}
