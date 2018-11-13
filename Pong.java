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
				world.updateSphere(1.0 / (double) FPS);
				world.updatePaddle(1.0 / (double) FPS);
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
		if (c == 'r') {
			if (world.p1.velocity.y == 0) {
				world.p1.velocity.y = 5;
			}
			if (world.p1.velocity.y > 0) {
				world.p1.velocity.flipY();
			}
		}
		if (c == 'v') {
			if (world.p1.velocity.y == 0) {
				world.p1.velocity.y = 5;
			}
			if (world.p1.velocity.y < 0) {
				world.p1.velocity.flipY();
			}
		}
		if (c == 'f') {
			world.p1.velocity.y = 0;
		}
		if (c == 'u') {
			if (world.p2.velocity.y == 0) {
				world.p2.velocity.y = 5;
			}
			if (world.p2.velocity.y > 0) {
				world.p2.velocity.flipY();
			}
		}
		if (c == 'n') {
			if (world.p2.velocity.y == 0) {
				world.p2.velocity.y = 5;
			}
			if (world.p2.velocity.y < 0) {
				world.p2.velocity.flipY();
			}
		}
		if (c == 'j') {
			world.p2.velocity.y = 0;
		}

	}

	public void keyReleased(KeyEvent e) {
		char c = e.getKeyChar();
		System.out.println("\tYou let go of: " + c);
		if (c == 'w') {
			world.p1.velocity.y = 0;
		}
		if (c == 's') {
			world.p1.velocity.y = 0;
		}
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
		world = new World(WIDTH, HEIGHT);
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
		g.setColor(Color.WHITE);
		g.fillRect(WIDTH / 2, 0, 4, HEIGHT);
		world.drawSphere(g);
		world.drawPaddle(g);
	}

}
