 import java.applet.Applet;
 import java.awt.Color;
 import java.awt.Image;
 import java.awt.Graphics;
 import java.awt.MediaTracker;

 public class ImageBlaster extends Applet implements Runnable {
      MediaTracker tracker;
      Image bg;
      Image anim[] = new Image[5];
      int index;
      Thread animator;

      // Get the images for the background (id == 0)
      // and the animation frames (id == 1)
      // and add them to the MediaTracker
      public void init() {
          tracker = new MediaTracker(this);
          bg = getImage(getClass().getResource("/images/background.jpg"));
          tracker.addImage(bg, 0);
          for (int i = 0; i < 5; i++) {
        	  System.out.println(getClass().getResource("/images/background.jpg").toString());
              anim[i] = getImage(getClass().getResource("/images/background.jpg"));
              tracker.addImage(anim[i], 1);
          }
      }

      // Start the animation thread.
      public void start() {
          animator = new Thread(this);
          animator.start();
      }

      // Stop the animation thread.
      public void stop() {
          animator = null;
      }

      // Run the animation thread.
      // First wait for the background image to fully load
      // and paint.  Then wait for all of the animation
      // frames to finish loading. Finally, loop and
      // increment the animation frame index.
      public void run() {
          try {
              tracker.waitForID(0);
              tracker.waitForID(1);
          } catch (InterruptedException e) {
              return;
          }
          Thread me = Thread.currentThread();
          while (animator == me) {
              try {
                  Thread.sleep(100);
              } catch (InterruptedException e) {
                  break;
              }
              synchronized (this) {
                  index++;
                  if (index >= anim.length) {
                      index = 0;
                  }
              }
              repaint();
          }
      }

      // The background image fills the frame so we
      // don't need to clear the applet on repaints.
      // Just call the paint method.
      public void update(Graphics g) {
          paint(g);
      }

      // Paint a large red rectangle if there are any errors
      // loading the images.  Otherwise always paint the
      // background so that it appears incrementally as it
      // is loading.  Finally, only paint the current animation
      // frame if all of the frames (id == 1) are done loading,
      // so that we don't get partial animations.
      public void paint(Graphics g) {
          if ((tracker.statusAll(false) & MediaTracker.ERRORED) != 0) {
              g.setColor(Color.red);
              g.fillRect(0, 0, size().width, size().height);
              return;
          }
          g.drawImage(bg, 0, 0, this);
          if (tracker.statusID(1, false) == MediaTracker.COMPLETE) {
              g.drawImage(anim[index], 10, 10, this);
          }
      }
 }
 