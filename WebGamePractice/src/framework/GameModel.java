package framework;

import game.BuildConfig;
import interfaces.IGamePanel;

import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.common.Vec2;

public class GameModel {

  private static DebugDraw gamePanelRenderer;
  private static IGamePanel gamePanel;
  private BuildConfig config;
  private final Vec2 mouse = new Vec2();

  private boolean[] keys = new boolean[512];
  private boolean[] codedKeys = new boolean[512];
  private float panelWidth;

  public boolean pause = false;

  public GameModel() {
	// TODO: create game stages for cover page, game setting, game play....etc
//	config = new GameConfig();
	pause = false;
  }

  public void initKeyboard(){
	keys = new boolean[512];
	codedKeys = new boolean[512];
  }
  
  public BuildConfig getCurrConfig() {//TODO: change name into getCurrGame
	return this.config;
  }

  public void setCurrConfig(final BuildConfig config) {//TODO: change name into setCurrGame
	this.config = config;
  }

  public Vec2 getMouse() {
	return this.mouse;
  }

  /**
   * Returns the mouse coordinates on screen
   * 
   * @see Config.getWorldMouse()
   * */
  public void setMouse(final Vec2 mouse) {
	this.mouse.set(mouse);
  }

  public float getPanelWidth() {
	return this.panelWidth;
  }

  public void setPanelWidth(final float panelWidth) {
	this.panelWidth = panelWidth;
  }

  /**
   * Gets the array of keys, index corresponding to the char value.
   * 
   * @return
   */
  public boolean[] getKeys(){
	return keys;
  }

  /**
   * Gets the array of coded keys, index corresponding to the coded key value.
   * 
   * @return
   */
  public boolean[] getCodedKeys(){
	return codedKeys;
  }

  public static DebugDraw getGamePanelRenderer() {
	return GameModel.gamePanelRenderer;
  }

  public static void setGamePanelRenderer(final DebugDraw argRenderer) {
	GameModel.gamePanelRenderer = argRenderer;
  }

  public static IGamePanel getGamePanel() {
	return gamePanel;
  }

  public static void setGamePanel(final IGamePanel gamePanel) {
	GameModel.gamePanel = gamePanel;
  }
}
