import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

class Sphere {
	Pair position;
	Pair velocity;
	Pair acceleration;
	double radius;
	double dampening;
	Color color;

	public Sphere() {
		Random rand = new Random();
		position = new Pair(500.0, 500.0);
		velocity = new Pair((double) (rand.nextInt(1000) - 500), (double) (rand.nextInt(1000) - 500));
		acceleration = new Pair(0.0, 200.0);
		radius = 10;
		dampening = 1.3;
		color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
	}

	public void update(World w, double time) {
		position = position.add(velocity.times(time));
		velocity = velocity.add(acceleration.times(time));
		bounce(w);
	}

	public void setPosition(Pair p) {
		position = p;
	}

	public void setVelocity(Pair v) {
		velocity = v;
	}

	public void setAcceleration(Pair a) {
		acceleration = a;
	}

	public void draw(Graphics g) {
		Color c = g.getColor();

		g.setColor(color);
		g.drawOval((int) (position.x - radius), (int) (position.y - radius), (int) (2 * radius), (int) (2 * radius));
		g.setColor(c);
	}

	private void bounce(World w) {
		Boolean bounced = false;
		if (position.x - radius < 0) {
			velocity.flipX();
			position.x = radius;
			bounced = true;
		} else if (position.x + radius > w.width) {
			velocity.flipX();
			position.x = w.width - radius;
			bounced = true;
		}
		if (position.y - radius < 0) {
			velocity.flipY();
			position.y = radius;
			bounced = true;
		} else if (position.y + radius > w.height) {
			velocity.flipY();
			position.y = w.height - radius;
			bounced = true;
		}
		if (bounced) {
			velocity = velocity.divide(dampening);
		}
	}
}
