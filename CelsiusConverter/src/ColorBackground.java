import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

public class ColorBackground extends Background {
  protected Color color;

  public ColorBackground(Component comp, Color c) {
    super(comp);
    color = c;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color c) {
    color = c;
  }

  public void draw(Graphics g) {
    // Fill with color
    g.setColor(color);
    g.fillRect(0, 0, size.width, size.height);
    g.setColor(Color.black);
  }
}