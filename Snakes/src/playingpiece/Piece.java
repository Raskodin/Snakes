package playingpiece;

import game.Drawable;
import ui.GridPanel;
/**
 * Class that implements the Food which extends Piece and implements Drawable
 * 
 */
public class Piece implements Drawable{
	private int x;
	private int y;
	/**
     * Creates a new Piece
     * @param x, X coordinate of the piece (x>0) increases from left to right.
     * @param y, Y coordinate of the piece (y>0) increases from up to bottom.
     */
	public Piece(int x, int y) {
		this.x=x;
		this.y=y;
	}

	/**
     * Getter for the X coordinate of the creature
     * @return int x
     */
	public int getX() {
		return this.x;
	}
	/**
     * Setter for the X coordinate of the creature
     * @param int x , the new value of x
     */
	void setX(int x) {
		this.x=x;
	}
	/**
     * Getter for the Y coordinate of the creature
     * @return int y
     */
	public int getY() {
		return this.y;
	}
	/**
     * Setter for the Y coordinate of the creature
     * @param int y , the new value of y
     */
	void setY(int y) {
		this.y=y;
	}


	/**
     * to draw creature on the panel, will be overridden in subclasses
     * @Override
     */
	public void draw(GridPanel panel) {
		
		
	}
}
