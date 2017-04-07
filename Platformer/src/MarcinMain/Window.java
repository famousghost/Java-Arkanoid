package MarcinMain;

import javax.swing.JFrame;

public class Window extends JFrame {
	
	int width;
	int height;

	public Window()
	{
		width = 800;
		height = 600;
		setSize(width,height);
		setTitle("Platformer");
		setLayout(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}
