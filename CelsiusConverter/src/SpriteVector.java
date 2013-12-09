import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.BitSet;
import java.util.Random;
import java.util.Vector;


@SuppressWarnings("serial")
public class SpriteVector extends Vector{

	protected Background background;

	public SpriteVector(Background back) {
		super(50, 10);
		background = back;
	}

	/**This method is very useful when you want to randomly place multiple sprites on the screen.*/
	public Point getEmptyPosition(Dimension sSize) {
		Rectangle pos = new Rectangle(0, 0, sSize.width, sSize.height);
		Random    rand = new Random(System.currentTimeMillis());
		boolean   empty = false;
		int       numTries = 0;

		// Look for an empty position
		while (!empty && numTries++ < 50) {
			// Get a random position
			pos.x = Math.abs(rand.nextInt() %
					background.getSize().width);
			pos.y = Math.abs(rand.nextInt() %
					background.getSize().height);

			// Iterate through sprites, checking if position is empty
			boolean collision = false;
			for (int i = 0; i < size(); i++) {
				Rectangle testPos = ((Sprite)elementAt(i)).getPosition();
				if (pos.intersects(testPos)) {
					collision = true;
					break;
				}
			}
			empty = !collision;
		}
		return new Point(pos.x, pos.y); 
	} 

	public void update() {
		// Iterate through sprites, updating each
		Sprite    s, sHit;
		Rectangle lastPos;
		for (int i = 0; i < size(); ) {
			// Update the sprite
			s = (Sprite)elementAt(i);
			lastPos = new Rectangle(s.getPosition().x, s.getPosition().y,
					s.getPosition().width, s.getPosition().height);
			BitSet action = s.update();

			// Check for the SA_ADDSPRITE action
			if (action.get(Sprite.SA_ADDSPRITE)) {
				// Add the sprite
				Sprite sToAdd = s.addSprite(action);
				if (sToAdd != null) {
					int iAdd = add(sToAdd);
					if (iAdd >= 0 && iAdd <= i)
						i++;
				}
			}

			// Check for the SA_RESTOREPOS action
			if (action.get(Sprite.SA_RESTOREPOS))
				s.setPosition(lastPos);

			// Check for the SA_KILL action
			if (action.get(Sprite.SA_KILL)) {
				removeElementAt(i);
				continue;
			}

			// Test for collision
			int iHit = testCollision(s);
			if (iHit >= 0)
				if (collision(i, iHit))
					s.setPosition(lastPos);
			i++;
		} 

	} 

	public int add(Sprite s) {//use other data structures to do this more efficiently
		// Use a binary search to find the right location to insert the
		// new sprite (based on z-order)
		int   l = 0, r = size(), i = 0;
		int   z = s.getZOrder(),
				zTest = z + 1;
		while (r > l) {
			i = (l + r) / 2;
			zTest = ((Sprite)elementAt(i)).getZOrder();
			if (z < zTest)
				r = i;
			else
				l = i + 1;
			if (z == zTest)
				break;
		}
		if (z >= zTest)
			i++;

		insertElementAt(s, i);
		return i;
	}

	protected boolean collision(int i, int iHit) {
		// Swap velocities (bounce)
		Sprite s = (Sprite)elementAt(i);
		Sprite sHit = (Sprite)elementAt(iHit);
		Point swap = s.getVelocity();
		s.setVelocity(sHit.getVelocity());
		sHit.setVelocity(swap);
		return true;
	}

	protected int testCollision(Sprite test) {
		// Check for collision with other sprites
		Sprite  s;
		for (int i = 0; i < size(); i++)
		{
			s = (Sprite)elementAt(i);
			if (s == test)  // don't check itself
				continue;
			if (test.testCollision(s))
				return i;
		}
		return -1;
	}

	public void draw(Graphics g) {
		// Draw the background
		background.draw(g);

		// Iterate through sprites, drawing each
		for (int i = 0; i < size(); i++)
			((Sprite)elementAt(i)).draw(g);
	}

	public Sprite isPointInside(Point pt) {
		// Iterate backward through the sprites, testing each
		for (int i = (size() - 1); i >= 0; i--) {
			Sprite s = (Sprite)elementAt(i);
			if (s.isPointInside(pt))
				return s;
		}
		return null;
	}


	public Background getBackground() {
		return background;
	}

	public void setBackground(Background back) {
		background = back;
	}
}
