package game;

import org.jbox2d.dynamics.World;

import framework.GameModel;

/**
 * This represents a certain fragment of the game. 
 * */
public abstract class BuildConfig {

  private GameModel model;
  
  private float interpolation;
 
  public void init(){
	//TODO:
  }
  
  public abstract void init(World world);
  
  public abstract void initConfig();//TODO: change the name to initGame()
  
  public abstract void update();
  
  public abstract void step();
  
  public abstract void render();

  public float getInterpolation() {
	return interpolation;
  }

  public void setInterpolation(float interpolation) {
	this.interpolation = interpolation;
  }
}
