package playingpiece;

import java.awt.Color;

import game.Drawable;
import ui.GridPanel;

/**
 * Class that implements the Food which extends Piece and implements Drawable
 * 
 */
public class Food extends Piece implements Drawable {

	/**
     * Creates a new Food
     * @param x, X coordinate of the food (x>0) increases from left to right.
     * @param y, Y coordinate of the food (y>0) increases from up to bottom.
     * calls the super constructor with given parameters
     */
	public Food(int x, int y) {
		super(x, y);
	}
	/**
     * @Override draws the Food to the panel and paints with red
     * @Param GridPanel panel draws the Food to the given panel
     */

	@Override
	public void draw(GridPanel panel) {
		panel.drawSquare(getX(), getY(), Color.RED);
	}

}
