package naturesimulator;

import game.Direction;
import playingpiece.Food;
import playingpiece.Piece;

import java.util.HashMap;
import java.util.List;


/**
 * Class representing the information a piece has about its surroundings.
 * Automatically created and passed by the game to each piece at each timer tick.
 *
 */
public class LocalInformation {

    private HashMap<Direction, Piece> pieces;
    private List<Direction> freeDirections;

    /**
     * Constructs the local information for a piece
     * @param gridWidth width of the grid world
     * @param gridHeight height of the grid world
     * @param creatures mapping of directions to neighbor pieces
     * @param freeDirections list of free directions
     */
    LocalInformation(HashMap<Direction, Piece> pieces, List<Direction> freeDirections) {
        this.pieces = pieces;
        this.freeDirections = freeDirections;
    }


    /**
     * Returns the list of free directions around the current position.
     * The list does not contain directions out of bounds or containing a piece.
     * @return freeDirections list
     */
    public List<Direction> getFreeDirections() {
        return freeDirections;
    }

    /**
     * Utility function to get a randomly selected direction among multiple directions.
     * The selection is uniform random: All directions in the list have an equal chance to be chosen.
     * @param possibleDirections list of possible directions
     * @return direction randomly selected from the list of possible directions
     */
    public static Direction getRandomDirection(List<Direction> possibleDirections) {
        if (possibleDirections.isEmpty()) {
            return null;
        }
        int randomIndex = (int)(Math.random() * possibleDirections.size());
        return possibleDirections.get(randomIndex);
    }
    
    /**
     * Function to get the neighbor food's direction.
     * @return direction of the food.
     */
    public Direction getFoodDirection() {
    	if (pieces.get(Direction.UP) instanceof Food) {
            return Direction.UP;
        }
        if (pieces.get(Direction.DOWN) instanceof Food) {
        	 return Direction.DOWN;
        }
        if (pieces.get(Direction.RIGHT) instanceof Food) {
        	 return Direction.RIGHT;
        }
        if (pieces.get(Direction.LEFT) instanceof Food) {
        	 return Direction.LEFT;
        }
        return null;
    	
    }

}
