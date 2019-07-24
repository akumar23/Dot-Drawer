package stacklab;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

// everything in this class before action preferred were written by the lab instructor
// but through my comments i hope to show that i still understand what is happening

public class Painter extends JFrame implements ActionListener {

	// initializes the buttons on the board and the canvas to draw on

	private Painter				outerThis;
	private JButton				undoBtn;
	private JButton				redoBtn;
	private JButton				quitBtn;
	private ColorChooser		colorChooser;
	private Canvas				canvas;

	// stack for the dots that are put on the canvas

	private Stack<Circle> 		history;

	// stack for the dots that get undone

	private Stack<Circle> 		undoHistory;
	
	
	private class ColorChooser extends JPanel {
		JSlider[]		sliders;
		SamplerPanel	sampler;
		
		ColorChooser() {
			setLayout(new BorderLayout());
			sampler = new SamplerPanel();
			
			JPanel west = new JPanel(new GridLayout(3, 1));
			sliders = new JSlider[3];

			// creates 3 panels for the color chooser

			for (int i=0; i<3; i++) {
				JPanel p = new JPanel();
				p.add(new JLabel("" + "RGB".charAt(i)));
				sliders[i] = new JSlider(0, 255, (i==0) ? 255 : 0);
				sliders[i].addChangeListener(sampler);
				p.add(sliders[i]);
				west.add(p);
			}
			add(west, BorderLayout.WEST);
			add(sampler, BorderLayout.CENTER);
		}

		// changes the color of the brush based on the values on the rgb knob

		Color getColor() {
			int r = sliders[0].getValue();
			int g = sliders[1].getValue();
			int b = sliders[2].getValue();
			return new Color(r, g, b);
		}
		
		private class SamplerPanel extends JPanel implements ChangeListener {
			public Dimension getPreferredSize()
			{
				return new Dimension(50, 50);
			}
			
			public void stateChanged(ChangeEvent e) 
			{
				repaint();
			}

			// paints on the canvas

			public void paintComponent(Graphics g) {
				g.setColor(getColor());
				g.fillRect(0, 0, getWidth(), getHeight());
			}
		}
	}
	
	
	private class Canvas extends JPanel implements MouseListener {
		Canvas()
		{
			addMouseListener(this);
		}
		
		public Dimension getPreferredSize()
		{
			return new Dimension(500, 500);
		}

		public void mouseClicked(MouseEvent e) {
			Circle circle = new Circle(e.getPoint(), 50, colorChooser.getColor());
			history.push(circle);
			repaint();
			undoBtn.setEnabled(true);
		}

		public void mousePressed(MouseEvent e) { }
		public void mouseReleased(MouseEvent e) { }
		public void mouseEntered(MouseEvent e) { }
		public void mouseExited(MouseEvent e) { }
		
		public void paintComponent(Graphics g) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, getWidth(), getHeight());
			for (Circle circle: history)
				circle.paint(g);
		}
	}  // End of inner class Canvas
	
	
	Painter() {
		outerThis = this;
		
		setResizable(false);
		
		history = new Stack<>();
		undoHistory = new Stack<>();

		// sets up the components of the panel
		
		JPanel pan = new JPanel();
		colorChooser = new ColorChooser();
		pan.add(colorChooser);
		undoBtn = new JButton("Undo");
		undoBtn.addActionListener(this);
		pan.add(undoBtn);
		redoBtn = new JButton("Redo");
		redoBtn.addActionListener(this);
		pan.add(redoBtn);
		quitBtn = new JButton("Quit");
		quitBtn.addActionListener(this);
		pan.add(quitBtn);
		add(pan, BorderLayout.NORTH);
		canvas = new Canvas();
		add(canvas, BorderLayout.CENTER);
		pack();
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == quitBtn)
			System.exit(0);
		else if (e.getSource() == undoBtn)
			undo();
		else if (e.getSource() == redoBtn)
			redo();
	}
	
	
	void undo()
	{
		System.out.println("Undo not implemented");
	}

	
	void redo()
	{
		System.out.println("Redo not implemented");
	}
	
	
	Stack<Circle> getHistory()
	{
		return history;
	}

	
	
	Stack<Circle> getUndoHistory()
	{
		return undoHistory;
	}
	
	
	void setUndoButtonEnabled(boolean b)
	{
		undoBtn.setEnabled(b);
	}
	
	
	void setRedoButtonEnabled(boolean b)
	{
		redoBtn.setEnabled(b);
	}
	
	
	public static void main(String[] args)
	{
		new Painter().setVisible(true);
	}
	
}
