import java.awt.*;

public class Texture {
	public static void draw(Graphics g2d, int screenX, int screenY, int width, int height, Color color) {
		g2d.setColor(color);
		g2d.fillRect(screenX, screenY, width, height);
	}
}
