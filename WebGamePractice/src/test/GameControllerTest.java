package test;

import framework.GameController;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameControllerTest extends Canvas{

  private Dimension resolution;
  private Image drawImage;
  private Graphics2D drawGraphics;

  private Color backgroundColor = Color.black;

  private GameController controller;

  private float interpolation;

  public GameControllerTest(Dimension resolution){
	this.resolution = resolution;
	//this.controller = controller;

	setPreferredSize(resolution);
  }
  
  public void start(){
	GameController controller = new GameController(this);
	controller.start();
  }

  public void init(){
	//System.out.println("init");
  }

  public void update(){
	//System.out.println("update");
  }

  public void render(){
	//System.out.println("render");
  }

  public float getInterpolation() {
	return interpolation;
  }

  public void setInterpolation(float interpolation) {
	this.interpolation = interpolation;
  }

  public static void main(String[] args) {
	JFrame frame = new JFrame();
	GameControllerTest game = new GameControllerTest(new Dimension(800,600));
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.add(game);
	frame.pack();
	frame.setVisible(true);
	game.start();
  }
}
