import java.applet.Applet;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.util.Random;


@SuppressWarnings("serial")
public class Atom extends Applet implements Runnable{
	/**offscreen buffer & background image*/
	private Image         offImage, back;
	/**atom images*/
	private Image[]       atom = new Image[6];
	/**graphics context for the offscreen buffer image*/
	private Graphics      offGrfx;
	private Thread        animate;
	/**track the various images as they are being loaded*/
	private MediaTracker  tracker;
	/**holds the sprite vector for the applet*/
	private SpriteVector  sv;
	private int           delay = 83; // 12 fps
	//private int           delay = 17; // 60 fps
	private Random        rand = new
			Random(System.currentTimeMillis());

	public void init() {
		// Load and track the images
		tracker = new MediaTracker(this);
		back = getImage(getClass().getResource("/images/background.jpg"));//.getScaledInstance(30, 30, 0);
		tracker.addImage(back, 0);
		atom[0] = getImage(getClass().getResource("/images/black.jpg")).getScaledInstance(30, 30, 0);
		tracker.addImage(atom[0], 1);
		atom[1] = getImage(getClass().getResource("/images/green.png")).getScaledInstance(30, 30, 0);
		tracker.addImage(atom[1], 1);
		atom[2] = getImage(getClass().getResource("/images/blue.png")).getScaledInstance(30, 30, 0);
		tracker.addImage(atom[2], 1);
		atom[3] = getImage(getClass().getResource("/images/yellow.png")).getScaledInstance(30, 30, 0);
		tracker.addImage(atom[3], 1);
		atom[4] = getImage(getClass().getResource("/images/purple.png")).getScaledInstance(30, 30, 0);
		tracker.addImage(atom[4], 1);
		atom[5] = getImage(getClass().getResource("/images/red.png")).getScaledInstance(30, 30, 0);
		tracker.addImage(atom[5], 1);
	}

	public void start() {
		if (animate == null) {
			animate = new Thread(this);
			animate.start();
		}
	}

	public void stop() {
		if (animate != null) {
			animate.stop();
			animate = null;
		}
	}

	public void run() {
		try {
			tracker.waitForID(0);
			tracker.waitForID(1);
		}
		catch (InterruptedException e) {
			return;
		}

		// Create and add the sprites
		sv = new SpriteVector(new ImageBackground(this, back));
		for (int i = 0; i < 12; i++) {
			Point pos = sv.getEmptyPosition(new Dimension(
					atom[0].getWidth(this), atom[0].getHeight(this)));
			sv.add(createAtom(pos, i % 6));
		}

		// Update everything
		long t = System.currentTimeMillis();
		while (Thread.currentThread() == animate) {
			sv.update();
			repaint();
			try {
				t += delay;
				Thread.sleep(Math.max(0, t - System.currentTimeMillis()));
			}
			catch (InterruptedException e) {
				break;
			}
		}
	}

	public void update(Graphics g) {
		// Create the offscreen graphics context
		if (offGrfx == null) {
			offImage = createImage(size().width, size().height);
			offGrfx = offImage.getGraphics();
		}

		// Draw the sprites
		sv.draw(offGrfx);

		// Draw the image onto the screen
		g.drawImage(offImage, 0, 0, null);
	}

	public void paint(Graphics g) {
		if ((tracker.statusID(0, true) & MediaTracker.ERRORED) != 0) {
			// Draw the error rectangle
			g.setColor(Color.red);
			g.fillRect(0, 0, size().width, size().height);
			return;
		}
		if ((tracker.statusID(0, true) & MediaTracker.COMPLETE) != 0) {
			// Draw the offscreen image
			g.drawImage(offImage, 0, 0, null);
		}
		else {
			// Draw the title message (while the images load)
			Font        f1 = new Font("TimesRoman", Font.BOLD, 28),
					f2 = new Font("Helvetica", Font.PLAIN, 16);
			FontMetrics fm1 = g.getFontMetrics(f1),
					fm2 = g.getFontMetrics(f2);
			String      s1 = new String("Atoms"),
					s2 = new String("Loading images...");
			g.setFont(f1);
			g.drawString(s1, (size().width - fm1.stringWidth(s1)) / 2,
					((size().height - fm1.getHeight()) / 2) + fm1.getAscent());
			g.setFont(f2);
			g.drawString(s2, (size().width - fm2.stringWidth(s2)) / 2,
					size().height - fm2.getHeight() - fm2.getAscent());
		}
	}
	private Sprite createAtom(Point pos, int i) {
		//return new Sprite(this, atom[i], pos, new Point(rand.nextInt()
		//		% 5, rand.nextInt() % 5), 0, Sprite.BA_BOUncE);
		return new Sprite(this, atom, 0,rand.nextInt() % 4 , 5,  sv.getEmptyPosition(new Dimension(100,100)),
				 new Point(0,0),0, Sprite.BA_BOUncE);
//		Component comp, Image[] img, int f, int fInc, int fDelay,
//		Point pos, Point vel, int z_order, int bounds_action
	}
}
