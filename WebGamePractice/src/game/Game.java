package game;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Game {
	
	// Whether to enable VSync in hardware.
	public static final boolean VSYNC = true;
	
	// Width and height of our window
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	// Whether to use fullscreen mode
	public static final boolean FULLSCREEN = false;
	
	// Whether our game loop is running
	protected boolean running = false;
	
	public static void main(String[] args) throws LWJGLException {
		new Game().start();
	}
	
	// Start our game
	public void start() throws LWJGLException {
		// Set up our display 
		Display.setTitle("Display example"); //title of our window
		Display.setResizable(true); //whether our window is resizable
		Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT)); //resolution of our display
		Display.setVSyncEnabled(VSYNC); //whether hardware VSync is enabled
		Display.setFullscreen(FULLSCREEN); //whether fullscreen is enabled

		//create and show our display
		Display.create();
		
		// Create our OpenGL context and initialize any resources
		create();
		
		// Call this before running to set up our initial size
		resize();

		running = true;
		
		// While we're still running and the user hasn't closed the window... 
		while (running && !Display.isCloseRequested()) {
			// If the game was resized, we need to update our projection
			if (Display.wasResized())
				resize();
			
			// Render the game
			render();
			
			// Flip the buffers and sync to 60 FPS
			Display.update();
			Display.sync(60);
		}
		
		// Dispose any resources and destroy our window
		dispose();
		Display.destroy();
	}
	
	// Exit our game loop and close the window
	public void exit() {
		running = false;
	}
	
	// Called to setup our game and context
	protected void create() {
		// 2D games generally won't require depth testing 
	  GL11.glDisable(GL11.GL_DEPTH_TEST);
		
		// Enable blending
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		// Set clear to transparent black
		GL11.glClearColor(0f, 0f, 0f, 0f);
				
		// ... initialize resources here ...
	}
	
	// Called to render our game
	protected void render() {
		// Clear the screen
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		// ... render our game here ...
	}
	
	// Called to resize our game
	protected void resize() {
	  GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		// ... update our projection matrices here ...
	}
	
	// Called to destroy our game upon exiting
	protected void dispose() {
		// ... dispose of any textures, etc ...
	}
}