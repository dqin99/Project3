import java.awt.Graphics;

class World {
	int height;
	int width;
	Sphere sphere;
	Paddle p1;
	Paddle p2;
	public World(int initWidth, int initHeight) {
		width = initWidth;
		height = initHeight;
		sphere = new Sphere();
		p1 = new Paddle();
		p2 = new Paddle();
	}

	public void drawSphere(Graphics g) {
		sphere.draw(g);
	}
	public void updateSphere(double time) {
		sphere.update(this, time);
	}
	public void drawPaddle(Graphics g) {
		p1.draw(g);
		p2.draw(g);
	}
	public void updatePaddle(double time) {
		p1.update(this, time);
		p2.update(this, time);
	}
}
