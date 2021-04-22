package model.board;

import java.util.List;

import model.box.Box;
import model.player.PlayerImpl;

public interface Board {

    /**
     * Create the board of game.
     * 
     * @return list of boxes that equals to the game board
     */
    List<Box> generateBoard();

    /**
     * Get type of box where the player is above now.
     * 
     * @param player
     * @return type of the box
     */
    Box getBox(PlayerImpl player);
}
