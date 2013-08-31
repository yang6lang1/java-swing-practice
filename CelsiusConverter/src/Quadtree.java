import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Quadtree {

	private final int MAX_OBJECTS = 10; // how many objects a node can hold
										// before it splits
	private final int MAX_LEVELS = 5; // the deepest level subnode

	private final int level; // current node level
	private final List<Rectangle> objects;
	private final Rectangle bounds; // the 2D space that the node occupies
	private final Quadtree[] nodes; // four subnodes

	public Quadtree(int pLevel, Rectangle pBounds) {
		level = pLevel;
		objects = new ArrayList<Rectangle>();
		bounds = pBounds;
		nodes = new Quadtree[4];
	}

	/**
	 * The clear method clears the quadtree by recursively clearing all objects
	 * from all nodes.
	 * */
	public void clear() {
		objects.clear();

		for (int i = 0; i < nodes.length; i++) {
			if (nodes[i] != null) {
				nodes[i].clear();
				nodes[i] = null;
			}
		}
	}

	/**
	 * The split method splits the node into four subnodes by dividing the node
	 * into four equal parts and initializing the four subnodes with the new
	 * bounds.
	 * */
	public void split() {
		int subWidth = (int) (bounds.getWidth() / 2);
		int subHeight = (int) (bounds.getHeight() / 2);
		int x = (int) (bounds.getX());
		int y = (int) (bounds.getY());

		nodes[0] = new Quadtree(level + 1, new Rectangle(x + subWidth, y,
				subWidth, subHeight));
		nodes[1] = new Quadtree(level + 1, new Rectangle(x, y, subWidth,
				subHeight));
		nodes[2] = new Quadtree(level + 1, new Rectangle(x, y + subHeight,
				subWidth, subHeight));
		nodes[3] = new Quadtree(level + 1, new Rectangle(x + subWidth, y
				+ subHeight, subWidth, subHeight));
	}

	/**
	 * Determine which node the object belongs to. -1 means object cannot
	 * completely fit within a child node and is part of the parent node
	 */
	public int getIndex(Rectangle pRect) {
		int index = -1;
		double verticalMidPoint = bounds.getX() + (bounds.getWidth() / 2);
		double horizontalMidPoint = bounds.getY() + (bounds.getHeight() / 2);

		// Object can completely fit within the top quadrants
		boolean topQuadrant = (pRect.getY() < horizontalMidPoint && pRect
				.getY() + pRect.getHeight() < horizontalMidPoint);
		// Object can completely fit within the bottom quadrants
		boolean bottomQuadrant = (pRect.getY() > horizontalMidPoint);

		// Object can completely fit within the left quadrants
		if (pRect.getX() < verticalMidPoint
				&& pRect.getX() + pRect.getWidth() < verticalMidPoint) {
			if (topQuadrant) {
				index = 1;
			} else if (bottomQuadrant) {
				index = 2;
			}
		}
		// Object can completely fit within the right quadrants
		else if (pRect.getX() > verticalMidPoint) {
			if (topQuadrant) {
				index = 0;
			} else if (bottomQuadrant) {
				index = 3;
			}
		}

		return index;
	}

	/**
	 * Insert the object into the quadtree. If the node exceeds the capacity, it
	 * will split and add all objects to their corresponding nodes.
	 */
	public void insert(Rectangle pRect) {
		/*
		 * The insert method is where everything comes together. The method
		 * first determines whether the node has any child nodes and tries to
		 * add the object there. If there are no child nodes or the object
		 * doesn’t fit in a child node, it adds the object to the parent node.
		 * 
		 * Once the object is added, it determines whether the node needs to
		 * split by checking if the current number of objects exceeds the max
		 * allowed objects. Splitting will cause the node to insert any object
		 * that can fit in a child node to be added to the child node; otherwise
		 * the object will stay in the parent node.
		 */
		if (nodes[0] != null) {
			int index = getIndex(pRect);

			if (index != -1) {
				nodes[index].insert(pRect);

				return;
			}
		}

		objects.add(pRect);

		if (objects.size() > MAX_OBJECTS && level < MAX_LEVELS) {
			if (nodes[0] == null) {
				split();
			}

			int i = 0;
			while (i < objects.size()) {
				int index = getIndex(objects.get(i));
				if (index != -1) {
					nodes[index].insert(objects.remove(i));

				} else {
					i++;
				}
			}
		}
	}

	/**
	 * Return all objects that could collide with the given object
	 */
	public List retrieve(List returnObjects, Rectangle pRect) {
		int index = getIndex(pRect);
		if (index != -1 && nodes[0] != null) {
			nodes[index].retrieve(returnObjects, pRect);
		}

		returnObjects.addAll(objects);

		return returnObjects;
	}
}
