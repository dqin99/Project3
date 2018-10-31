import java.awt.Color;
import java.awt.Graphics;

public class Paddle {
	private static final int length = 10;
	private static final int width = 5;
	private static final int velocityY = 10;
	Pair position;


	public Paddle() {
		this.position = new Pair(15, 15);
	}
	
	public void draw(Graphics g) {
		Color c = g.getColor();

		g.setColor(c);
		g.fillRect((int) position.x, (int) position.y, length, width);
		g.setColor(c);
	}
}
