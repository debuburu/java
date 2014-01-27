package othello;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Board extends JFrame {
	private static final long serialVersionUID = 1L;
	private final int GRID_NUM = 8;
	private final int GRID_SIZE = 30;
	private Grid[][] gridArray = new Grid[GRID_NUM][GRID_NUM];

	public Board(String title) {
		super(title);

		//サイズ変更禁止
		setResizable(false);
		//サイズ指定
		this.setBounds(10, 10, GRID_SIZE * GRID_NUM, GRID_SIZE * (GRID_NUM + 1));
		//背景色指定
		this.getContentPane().setBackground(Color.GREEN);

		//各マスを追加
		setLayout(new GridLayout(GRID_NUM, GRID_NUM));
		for (int i = 0; i < GRID_NUM; i++) {
			for (int j = 0; j < GRID_NUM; j++) {
				gridArray[j][i] = new Grid(GRID_SIZE, Color.GREEN);
				add(gridArray[j][i]);
			}
		}
		
		//初期配置
		gridArray[GRID_NUM / 2 - 1][GRID_NUM / 2 - 1].change(Grid.State.Black);
		gridArray[GRID_NUM / 2][GRID_NUM / 2].change(Grid.State.Black);
		gridArray[GRID_NUM / 2][GRID_NUM / 2 - 1].change(Grid.State.White);
		gridArray[GRID_NUM / 2 - 1][GRID_NUM / 2].change(Grid.State.White);
		
		//マスクリック時のイベント
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				clicked(e.getX(), e.getY());
			}
		});

		//終了時イベント
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				dispose();
				System.exit(0);
			}
		});
	}
	
	public void clicked(int x, int y) {
		System.out.println("clicked." + x + ", " + y);
		gridArray[x / GRID_SIZE][(y - GRID_SIZE) / GRID_SIZE].change(Grid.State.White);
	}

	public static void main(String[] args) {
		Board board = new Board("オセロワールド");
		board.setVisible(true);
	}

}