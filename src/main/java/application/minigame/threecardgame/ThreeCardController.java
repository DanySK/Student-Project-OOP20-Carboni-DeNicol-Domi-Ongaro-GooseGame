package application.minigame.threecardgame;

import controller.minigame.MinigameController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ThreeCardController implements MinigameController {

    private static final int PROGRESS_IN_GAME = 5;
    private final ThreeCardView view;
    private Choice playerChoice, computerChoice;

    private int numPlayerWin = 0;
    private int numComputerWin = 0;
    private int numTurns = 0;

    public ThreeCardController() {
        view = new ThreeCardView();
        view.setSxButton(new SxClickHandler());
        view.setCenterButton(new CenterClickHandler());
        view.setDxButton(new DxClickHandler());
        computerChoice = Choice.getRandomChoice();
        view.show();
    }

    @Override
    public int getResult() {
       if (checkTurns()) {
           return PROGRESS_IN_GAME;
       }
       return 0;
    }

    /**
     * An inner class for the event catching in the minigame view
     */
    public class SxClickHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            playerChoice = Choice.SX_POS;

            if (getWin(playerChoice, computerChoice)) {
                setWin();
            }
        }
    }

    /**
     * An inner class for the event catching in the minigame view
     */
    public class CenterClickHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (numTurns < 3) {
                playerChoice = Choice.CENTER_POS;

                if (getWin(playerChoice, computerChoice)) {
                    setWin();
                }
            }
        }
    }

    /**
     * An inner class for the event catching in the minigame view
     */
    public class DxClickHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (numTurns < 3) {
                playerChoice = Choice.DX_POS;

                if (getWin(playerChoice, computerChoice)) {
                    setWin();
                }
            }
        }
    }

    /**
     * @param playerChoice
     * @param computerChoice
     * @return true if playerChoice is equal of computerChoice
     */
    private boolean getWin(Choice playerChoice, Choice computerChoice) {
        return playerChoice.equals(computerChoice);
    }

    /**
     * @return true if is over the minigame
     */
    private boolean checkTurns() {
        return numTurns == 3 || numPlayerWin == 2 || numComputerWin == 2;
    }

    private void setWin() {
        numTurns++;

        if (getWin(playerChoice, computerChoice)) {
            numPlayerWin++;
            view.setPlayerScoreLabel(numPlayerWin);
        } else {
            numComputerWin++;
            view.setComputerScoreLabel(numComputerWin);
        }
        view.setImages(computerChoice);
    }

}
