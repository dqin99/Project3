import java.awt.Color;
import java.awt.Graphics;

public class Paddle {
    final int length = 100;
	final int width = 5;
    Pair velocity = new Pair(0, 0);
	private static int paddleNumber = 1;
	Pair position;

	public Paddle() {
		if (paddleNumber == 1) {
			this.position = new Pair(5, 15);
		} else {
			this.position = new Pair(1014, 15);
		}
		paddleNumber++;
	}
	public void draw(Graphics g) {
		g.fillRect((int) position.x, (int) position.y, width, length);
		g.setColor(Color.WHITE);
	}
	public void update(World w, double time) {
		position = position.add(velocity);
		move(w);
	}
	public void move(World w) {
		if(position.y + length >= w.height) {
			velocity.flipY();
		}else if(position.y == 0) {
			velocity.flipY();
		}
	}
}
