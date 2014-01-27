package othello;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class Grid extends JPanel {
	private static final long serialVersionUID = 1L;

	enum State {
		None(null),
		White(Color.WHITE),
		Black(Color.BLACK);
		
		private Color color;
		
		State(Color color) {
			this.color = color;
		}
		
		public Color getColor() {
			return color;
		}
	}
	private State state = State.None;
	private int size;
	
	Grid(int size, Color color) {
		super();
		this.size = size;
		setSize(size, size);
	}
	
	public State getState() {
		return state;
	}
	
	public void change(State state) {
		this.state = state;
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		BasicStroke stroke = new BasicStroke(4.0f);
		g2.setStroke(stroke);
		g2.setColor(Color.BLACK);
		g2.draw(new Line2D.Double(0.0d, 0.0d, 0.0d, (double)size));
		g2.draw(new Line2D.Double(0.0d, 0.0d, (double)size, 0.0d));
		g2.draw(new Line2D.Double((double)size, 0.0d, (double)size, (double)size));
		g2.draw(new Line2D.Double(0.0d, (double)size, (double)size, (double)size));
		
		if(state != State.None) {
			g2.setColor(state.getColor());
			g2.fill(new Ellipse2D.Double((double)size / 6, (double)size / 6, (double)size / 3 * 2, (double)size / 3 * 2));
		}
	}
}
