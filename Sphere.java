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
	static int player1 = 0;
	static int player2 = 0;

	public Sphere() {
		Random rand = new Random();
		position = new Pair(512, 384);
		velocity = new Pair(100, 100);
		acceleration = new Pair(10, 10);
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
		g.drawString("Player one: " + Integer.toString(player1), 300, 50);
		g.drawString("Player two: " + Integer.toString(player2), 650, 50);
		
	}

	private void bounce(World w) {
		Boolean bounced = false;
		if(position.x - radius <= w.p1.position.x + w.p1.width) {
			if(position.y >= w.p1.position.y && position.y <= w.p1.position.y + w.p1.length) {
				velocity.flipX();
				acceleration.flipX();
				bounced = true;
			}
		}
		if(position.x + radius >= w.p2.position.x + w.p2.width) {
			if(position.y >= w.p2.position.y && position.y <= w.p2.position.y + w.p2.length) {
				velocity.flipX();
				acceleration.flipX();
				bounced = true;
			}
		}
		if (position.x - radius < 0) {
			velocity.flipX();
			acceleration.flipX();
			position.x = radius;
			position = reset();
			player2++;
			bounced = true;
			
		} else if (position.x + radius > w.width) {
			velocity.flipX();
			acceleration.flipX();
			position.x = w.width - radius;
			position = reset();
			player1++;
			bounced = true;
		}
		if (position.y - radius < 0) {
			velocity.flipY();
			acceleration.flipY();
			position.y = radius;
			bounced = true;
		} else if (position.y + radius > w.height) {
			velocity.flipY();
			acceleration.flipY();
			position.y = w.height - radius;
			bounced = true;
		}
		if (bounced) {
			velocity = velocity.divide(dampening);
		}
	}

	public Pair reset() {
		position.x = 512;
		position.y = 384;
		return position;
	}

}
