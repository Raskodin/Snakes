package playingpiece;

import java.awt.Color;

import game.Drawable;
import ui.GridPanel;
/**
 * The nodes of snakes as a new class so that it can be represented as a drawable piece 
 */
public class Nodes extends Piece implements Drawable {
	
	private int type=0;
	/**
     * Creates a new Food
     * @param x, X coordinate of the food (x>0) increases from left to right.
     * @param y, Y coordinate of the food (y>0) increases from up to bottom.
     * calls the super constructor with given parameters
     */
	public Nodes(int x, int y) {
		super(x,y);
	}
	
	/**
     * Getter for the type of the Node 
     * @return int type 1 for head, 0 for body
     */
	public int getType(){
		return this.type;
	}
	/**
     * Setter for the type of the node
     * @param int type , the new type of node
     */
	void setType(int type) {
		this.type=type;
	}
	
	/**
     * @Override draws the Node to the panel and paints with Blue if its head or Yellow if its body
     * @Param GridPanel panel draws the Node to the given panel
     */

	public void draw(GridPanel panel) {
		if(this.type==1) {
			panel.drawSquare(getX(), getY(), Color.BLUE);
		}
		else {
			panel.drawSquare(getX(), getY(), Color.YELLOW);
		}
		
	}

}
