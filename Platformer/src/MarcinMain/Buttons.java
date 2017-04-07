package MarcinMain;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;


public class Buttons extends Window implements ActionListener {
	
	JButton resume;
	JButton start;
	JButton credits;
	JButton exit;
	

	
	public Buttons(int width,int height)
	{
		start = new JButton("Start");
		start.setBounds(350,200,width,height);
		credits = new JButton("Credits");
		credits.setBounds(350,300,width,height);
		exit = new JButton("Exit");
		exit.setBounds(350,400,width,height);
		resume = new JButton("Continue");
		resume.setBounds(350,100, width, height);
		start.addActionListener(this);
		resume.addActionListener(this);
		credits.addActionListener(this);
		exit.addActionListener(this);
		add(start);
		add(credits);
		add(exit);
		add(resume);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent event) {
		Object other = event.getSource();
		if(other == resume)
		{
			System.out.println("Continue");
		}
		else if(other == start)
		{
			System.out.println("Start Game");
		}
		else if(other == credits)
		{
			System.out.println("Credits");
		}
		else if(other == exit)
		{
			dispose();
		}
	}
	
}
