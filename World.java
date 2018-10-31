import java.awt.Graphics;

class World {
	int height;
	int width;

	int numSpheres;
	Sphere spheres[];
	
	public World(int initWidth, int initHeight, int initNumSpheres) {
		width = initWidth;
		height = initHeight;

		numSpheres = initNumSpheres;
		spheres = new Sphere[numSpheres];

		for (int i = 0; i < numSpheres; i++) {
			spheres[i] = new Sphere();
		}

	}
	public void drawPaddles(Graphics g) {
		
	}

	public void drawSpheres(Graphics g) {
		for (int i = 0; i < numSpheres; i++) {
			spheres[i].draw(g);
		}
	}

	public void updateSpheres(double time) {
		for (int i = 0; i < numSpheres; i++)
			spheres[i].update(this, time);
	}

}
