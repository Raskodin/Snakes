package playingpiece;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import game.Direction;
import naturesimulator.Action;
import naturesimulator.LocalInformation;
/**
 * The snake, nodes of the snake are gathered and linked via LinkedList
 * This class defines the action of a snake in any situation 
 * 
 */
public class Snake{
	
	LinkedList<Nodes> mysnake=new LinkedList<Nodes>();
	/**
	 * Initialize a snake at default location when called. 
	 * 
	 */
	public Snake() {
		for(int i=0;i<4;i++) {
			mysnake.add(new Nodes(4-i ,1));
		}
		mysnake.getFirst().setType(1);
	}
	/**
	 * Initialize a new snake derived from any old snake passed by parameter. Old snake's tail becomes new snake's head
	 * @param Snake old , the parent snake of newborn snake 
	 * 
	 */
	public Snake(Snake old) {
		for(int i=0;i<4;i++) {
			mysnake.add(old.mysnake.get(7-i));
		}
		mysnake.getFirst().setType(1);
	}
	/**
     * Getter for the head Node of the snake
     * @return Node head 
     */
	public Piece getHead() {
		
		return mysnake.getFirst();
	}
	/**
     * Getter for the tail Node of the snake
     * @return Node tail 
     */
	public Piece getTail() {
		return mysnake.getLast();
	}
	/**
     * Getter for a list of Nodes of the snake
     * @return ArrayList nodes 
     */
	public ArrayList<Piece> getNodes(){
		
		ArrayList<Piece> nodes=new ArrayList<>();
		for(int i=0;i<mysnake.size();i++) {
			nodes.add(mysnake.get(i));
		}
		return nodes;
		
	}
	/**
     * to choose which action will Snake make
     * returns a type of action and direction of that action if necessary
     * @Param LocalInformation headInfo to provide the current snake's environment information
     * @param Food food to move towards to food
     */
	public Action chooseAction(LocalInformation headinfo, Food food) {
		if(mysnake.size()==8) {
			return new Action(Action.Type.REPRODUCE);
		}else if(headinfo.getFoodDirection()!=null) {
			return new Action(Action.Type.FEED);
			
		}else if(headinfo.getFreeDirections().isEmpty()) {
		
			return new Action(Action.Type.STAY);
		}else if(!headinfo.getFreeDirections().isEmpty()) {
			return new Action(Action.Type.MOVE, whereToMove(headinfo, food));
		}
		return null;
	}
	/**
     * Decides which direction to go for a snake to get closer to the food
     * It sometimes chooses a direction which actually increase the distance to the food, that is to provide snakes a random move so that they wont get too close and stuck quickly
     * @Param LocalInformation headInfo to provide the current snake's environment information
     * @param Food food to move towards to food
     */	
	private Direction whereToMove(LocalInformation headinfo, Food food) {
		List<Direction> copylist=headinfo.getFreeDirections();
		List<Direction> finallist=new ArrayList<>();
		for(Direction direction:copylist) {
			if(isGettingCloser(direction, food)) {
				for(int i=0;i<10;i++) {
					finallist.add(direction);
				}
			}else {
				finallist.add(direction);
			}
		}
		
		return LocalInformation.getRandomDirection(finallist);
//		return LocalInformation.getRandomDirection(copylist); (random hareket eder)

		
	}
	/**
     * Checks if the snake gets closer to the food as a result of the move
     * @Param Direction direction which direction to check
     * @param Food food to check if the snake is getting closer
     */	
	private boolean isGettingCloser(Direction direction, Food food) {
		int xDifferance1=Math.abs(getHead().getX()-food.getX());
		int xDifferance2=xDifferance1;
		int yDifferance1=Math.abs(getHead().getY()-food.getY());
		int yDifferance2=yDifferance1;
		
		if(direction==Direction.UP) {
			yDifferance2=Math.abs(getHead().getY()-1-food.getY());
		}else if(direction==Direction.DOWN) {
			yDifferance2=Math.abs(getHead().getY()+1-food.getY());
		}else if(direction==Direction.LEFT) {
			xDifferance2=Math.abs(getHead().getX()-1-food.getX());
		}else if(direction==Direction.RIGHT) {
			xDifferance2=Math.abs(getHead().getX()+1-food.getX());
		}
		return xDifferance2+yDifferance2<xDifferance1+yDifferance1;
		
	}
	/**
     *Makes the snake's head move at given direction and the snake's body follows it
     * @Param Direction direction ,moves the snake to given direction
     */
	public void moveTo(Direction direction) {
		Nodes head=mysnake.getFirst();
		for(int i=mysnake.size()-1;i>0;i--) {
			mysnake.get(i).setX(mysnake.get(i-1).getX());
			mysnake.get(i).setY(mysnake.get(i-1).getY());
		}
		if(direction==Direction.UP) {
			head.setY(head.getY()-1);
		}
		if(direction==Direction.DOWN) {
			head.setY(head.getY()+1);
		}
		if(direction==Direction.RIGHT) {
			head.setX(head.getX()+1);
		}
		if(direction==Direction.LEFT) {
			head.setX(head.getX()-1);
		}
	}
	/**
     *Makes the snake eat the nearby food
     * @Param Food food, provide food's location
     */
	public void feed(Food food) {
		
		mysnake.add(new Nodes(mysnake.getLast().getX(), mysnake.getLast().getY()));
		
		for(int i=mysnake.size()-2;i>0;i--) {
			mysnake.get(i).setX(mysnake.get(i-1).getX());
			mysnake.get(i).setY(mysnake.get(i-1).getY());
		}
		
		mysnake.getFirst().setY(food.getY());
		mysnake.getFirst().setX(food.getX());

	}
	/**
     *Makes the snake stay without doing anything
     * 
     */
	public void stay() {
		
	}
	/**
     *Breaks down the snake's last 4 nodes 
     * 
     */
	public void reproduce() {
		while(mysnake.size()>4) {
			mysnake.removeLast();
		}
		
		
	}

	

	
	
	
	
		

}
