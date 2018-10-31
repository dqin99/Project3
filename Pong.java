import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Pong extends JPanel implements KeyListener {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	public static final int FPS = 60;
	World world;

	class Runner implements Runnable {
		public void run() {
			while (true) {
				world.updateSpheres(1.0 / (double) FPS);
				repaint();
				try {
					Thread.sleep(1000 / FPS);
				} catch (InterruptedException e) {
				}
			}
		}
	}

	public void keyPressed(KeyEvent e) {
		char c = e.getKeyChar();
		System.out.println("You pressed down: " + c);
		if (c == 'w') {
			for (int i = 0; i < world.numSpheres; i++) {
				Sphere s = world.spheres[i];
				Pair acceleration = s.acceleration;
				if (acceleration.y == 0) {
					acceleration.y = acceleration.x;
					acceleration.x = 0;
				}
				if (acceleration.y > 0) {
					acceleration.flipY();
				}
			}
		}
		if (c == 's') {
			for (int i = 0; i < world.numSpheres; i++) {
				Sphere s = world.spheres[i];
				Pair acceleration = s.acceleration;
				if (acceleration.y == 0) {
					acceleration.y = acceleration.x;
					acceleration.x = 0;
				}
				if (acceleration.y < 0) {
					acceleration.flipY();
				}
			}
		}
		if (c == 'a') {
			for (int i = 0; i < world.numSpheres; i++) {
				Sphere s = world.spheres[i];
				Pair acceleration = s.acceleration;
				if (acceleration.x == 0) {
					acceleration.x = acceleration.y;
					acceleration.y = 0;
				}
				if (acceleration.x > 0) {
					acceleration.flipX();
				}
			}
		}
		if (c == 'd') {
			for (int i = 0; i < world.numSpheres; i++) {
				Sphere s = world.spheres[i];
				Pair acceleration = s.acceleration;
				if (acceleration.x == 0) {
					acceleration.x = acceleration.y;
					acceleration.y = 0;
				}
				if (acceleration.x < 0) {
					acceleration.flipX();
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		char c = e.getKeyChar();
		System.out.println("\tYou let go of: " + c);

	}

	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		System.out.println("You typed: " + c);
	}

	public void addNotify() {
		super.addNotify();
		requestFocus();
	}

	public Pong() {
		world = new World(WIDTH, HEIGHT, 1);
		addKeyListener(this);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		Thread mainThread = new Thread(new Runner());
		mainThread.start();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Pong");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Pong mainInstance = new Pong();
		frame.setContentPane(mainInstance);
		frame.pack();
		frame.setVisible(true);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		world.drawSpheres(g);

	}

}
