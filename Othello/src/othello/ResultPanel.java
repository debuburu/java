package othello;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class ResultPanel extends JPanel {
	private int blackScore;
	private int whiteScore;
	
	ResultPanel(int blackScore, int whiteScore) {
		this.blackScore = blackScore;
		this.whiteScore = whiteScore;
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		Color winColor = Color.BLUE;
		Color loseColor = Color.RED;
		Color evenColor = Color.ORANGE;
		
		if(blackScore > whiteScore) {
			g2.setColor(winColor);
		} else if(blackScore < whiteScore) {
			g2.setColor(loseColor);
		} else {
			g2.setColor(evenColor);
		}
		g2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		g2.drawString("Black " + blackScore + " : ", 30, 30);
		g2.drawString("White " + whiteScore, 90, 30);
	}
}
