package naturesimulator;

import game.Direction;
import game.GridGame;
import playingpiece.Food;
import playingpiece.Piece;
import playingpiece.Snake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Class that implements the game logic for Nature Simulator.
 *
 */
public class NatureSimulator extends GridGame {
	private List<Snake> snakes;
    private List<Piece> pieces;
    private Piece[][] piecesMap;
    private Food food;

    /**
     * Creates a new Nature Simulator game instance with one snake and one food.
     * @param gridWidth number of grid squares along the width
     * @param gridHeight number of grid squares along the height
     * @param gridSquareSize size of a grid square in pixels
     * @param frameRate frame rate (number of timer ticks per second)
     */
    public NatureSimulator(int gridWidth, int gridHeight, int gridSquareSize, int frameRate) {
        super(gridWidth, gridHeight, gridSquareSize, frameRate);
        snakes = new ArrayList<>();
        pieces = new ArrayList<>();
        piecesMap = new Piece[gridWidth][gridHeight];
        addSnake(new Snake());
        addFood(getRandomFood());
        
        
    }
    /**
     * Periodically executed timer tick method.
     * It defines the logic of the game
     */
    @Override
    protected void timerTick() {
    	
        for (Piece piece : pieces) {//cleans the drawables list and update the map for the start of the game
        	removeDrawable(piece);
        	updatePiecesMap(piece.getX(), piece.getY(), piece);
        }
       
        ArrayList<Snake> snakesCopy = new ArrayList<>(snakes);

        for (Snake snake : snakesCopy) {//first chooses action for each snake then executes actions and updates piece list and pieceMap according to these changes
        	
        	Action action = snake.chooseAction(createLocalInformationForPiece(snake.getHead()), food);
        	
        	// Execute action
        	if (action != null) {
        		if (action.getType() == Action.Type.REPRODUCE) {
        			// REPRODUCE
        			snakes.add(new Snake(snake));//adds a new snake which is the current snakes child
        			snake.reproduce();
        			
        
        		} else if (action.getType() == Action.Type.FEED) {
        			// FEED
        			snake.feed(food);
        			pieces.remove(food);
        			pieces.add(snake.getTail());
        			addFood(getRandomFood());
        			
        			ArrayList<Piece> changedpieces=new ArrayList<>(snake.getNodes());
        			changedpieces.add(food);
        			
        			for(Piece changedpiece:changedpieces) {
        				updatePiecesMap(changedpiece.getX(), changedpiece.getY(), changedpiece);
        			}
        			
        		} else if (action.getType() == Action.Type.STAY) {
        			// STAY
        			snake.stay();
        		} else if (action.getType() == Action.Type.MOVE) {
        			int tempx=snake.getTail().getX();
        			int tempy=snake.getTail().getY();
        			
        			snake.moveTo(action.getDirection());
        			
        			ArrayList<Piece> changedpieces=new ArrayList<>(snake.getNodes());
       				
        			updatePiecesMap(tempx, tempy, null);
       				for(Piece changedpiece:changedpieces) {
       				updatePiecesMap(changedpiece.getX(), changedpiece.getY(), changedpiece);
     				}
        		}
        	}
        }
        for (Piece piece : pieces) {//adds the new pieces list to the drawables list of GridGame
           	addDrawable(piece);
        }

    }

    /**
     * Adds a new snake to the game
     * @param Snake snake to be added
     * @return boolean indicating the success of addition
     */
    private boolean addSnake(Snake snake) {
    	if (snake != null) {
    		snakes.add(snake);
    		pieces.addAll(snake.getNodes());
    		return true;
    	}
    	return false;
    }

    /**
     * Adds a new food to the game which means the erase of current food because it replaces with the old one
     * @param Food food to be added
     * @return boolean indicating the success of addition
     */
    private void addFood(Food food) {
    	this.food=food;
    	pieces.add(food);
    	
    }

    /**
     * Creates a LocalInformation object for piece.
     */
    private LocalInformation createLocalInformationForPiece(Piece piece) {
        int x = piece.getX();
        int y = piece.getY();

        HashMap<Direction, Piece> pieces = new HashMap<>();
        pieces.put(Direction.UP, getPieceAtPosition(x, y - 1));
        pieces.put(Direction.DOWN, getPieceAtPosition(x, y + 1));
        pieces.put(Direction.LEFT, getPieceAtPosition(x - 1, y));
        pieces.put(Direction.RIGHT, getPieceAtPosition(x + 1, y));

        ArrayList<Direction> freeDirections = new ArrayList<>();
        if (pieces.get(Direction.UP) == null && isPositionInsideGrid(x, y - 1)) {
            freeDirections.add(Direction.UP);
        }
        if (pieces.get(Direction.DOWN) == null && isPositionInsideGrid(x, y + 1)) {
            freeDirections.add(Direction.DOWN);
        }
        if (pieces.get(Direction.LEFT) == null && isPositionInsideGrid(x - 1, y)) {
            freeDirections.add(Direction.LEFT);
        }
        if (pieces.get(Direction.RIGHT) == null && isPositionInsideGrid(x + 1, y)) {
            freeDirections.add(Direction.RIGHT);
        }

        return new LocalInformation(pieces, freeDirections);
    }

    private boolean isPositionInsideGrid(int x, int y) {
        return (x >= 0 && x < getGridWidth()) && (y >= 0 && y < getGridHeight());
    }

    private void updatePiecesMap(int x, int y, Piece piece) {
        if (isPositionInsideGrid(x, y)) {
            piecesMap[x][y] = piece;
        }
    }

    private Piece getPieceAtPosition(int x, int y) {
        if (!isPositionInsideGrid(x, y)) {
            return null;
        }
        return piecesMap[x][y];
    }

    private boolean isPositionFree(int x, int y) {
        return isPositionInsideGrid(x, y) && getPieceAtPosition(x, y) == null;
    }
    /**
     * Creates a Random food at a empty point on grid
     * @return newly created food.
     */
    private Food getRandomFood() {
		int x=-1;
		int y=-1;
    	while(!isPositionFree(x,y)) {
    		x=(int)(Math.random() * getGridWidth());
    		y=(int)(Math.random() * getGridHeight());
    	}
		return new Food(x,y);
    	
    }

}
