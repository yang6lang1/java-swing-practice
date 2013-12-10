package framework;

import interfaces.IGamePanel;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class BlockadiaMain {
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch (Exception e) {
      System.out.println("Could not set the look and feel to nimbus. ");
    }
    
    GameModel model = new GameModel(); 
    IGamePanel panel = new GamePanel(model);
    JFrame blockadia = new JFrame();
    blockadia.setLayout(new BorderLayout());
    
    GameController controller = new GameController(panel, model.getCurrConfig(), model);//TODO
    
    blockadia.pack();
    blockadia.setVisible(true);
    blockadia.setLocationRelativeTo(null);
    blockadia.setResizable(false);
    blockadia.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //TODO: check if the build board is dirty
    controller.start();
  }
}
