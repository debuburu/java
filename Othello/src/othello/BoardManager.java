package othello;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import othello.Grid.State;

public class BoardManager implements BoardListener {
	private Board board;
	private Color currentColor;

	BoardManager() {
		// 先行は黒
		currentColor = Color.BLACK;
	}

	enum Direction {
		Up {
			public Point move(Point p) {
				return new Point(p.x, p.y - 1);
			}
		},
		Down {
			public Point move(Point p) {
				return new Point(p.x, p.y + 1);
			}
		},
		Left {
			public Point move(Point p) {
				return new Point(p.x - 1, p.y);
			}
		},
		Right {
			public Point move(Point p) {
				return new Point(p.x + 1, p.y);
			}
		},
		UpLeft {
			public Point move(Point p) {
				return new Point(p.x - 1, p.y - 1);
			}
		},
		DownLeft {
			public Point move(Point p) {
				return new Point(p.x - 1, p.y + 1);
			}
		},
		DownRight {
			public Point move(Point p) {
				return new Point(p.x + 1, p.y + 1);
			}
		},
		UpRight {
			public Point move(Point p) {
				return new Point(p.x + 1, p.y - 1);
			}
		};

		abstract public Point move(Point p);
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	@Override
	public void click(Point p) {
		List<Point> reverseGrids = new ArrayList<Point>();
		if (board.getGrid(p) != Grid.State.None) {
			// TODO not empty cell
			return;
		}

		// ひっくり返す石を判定する
		reverseGrids.addAll(check(p, Direction.Up, currentColor));
		reverseGrids.addAll(check(p, Direction.Down, currentColor));
		reverseGrids.addAll(check(p, Direction.Left, currentColor));
		reverseGrids.addAll(check(p, Direction.Right, currentColor));
		reverseGrids.addAll(check(p, Direction.UpLeft, currentColor));
		reverseGrids.addAll(check(p, Direction.DownLeft, currentColor));
		reverseGrids.addAll(check(p, Direction.DownRight, currentColor));
		reverseGrids.addAll(check(p, Direction.UpRight, currentColor));

		// 1つでもひっくり返るなら、クリックしたところにまず石を置く
		if (reverseGrids.isEmpty() == false) {
			board.put(p, currentColor);
			// currentColor = new Color(currentColor.getRed() ^ 255, currentColor.getGreen() ^ 255,
			// currentColor.getBlue() ^ 255);
			// ひっくり返す
			for (Point point : reverseGrids) {
				board.reverse(point);
			}

			// 手番交代
			if (currentColor == Color.BLACK) {
				currentColor = Color.WHITE;
			} else {
				currentColor = Color.BLACK;
			}
		}
		
		//TODO 置ける場所が残っているかどうか判定
		if(judgeEnd()) {
			board.endGame();
		}
	}

	private List<Point> check(Point p, Direction direct, Color color) {
		List<Point> reverseGrids = new ArrayList<Point>();
		Point point = p;
		boolean validReverse = false; // 最終的に反転するかどうかのフラグ

		while (true) {
			try {
				point = move(point, direct);
				// 違う色の石が置いてあればカウントを増やす
				if (color != board.getGrid(point).getColor() && validReverse == false) {
					reverseGrids.add(point);
				} else if (color == board.getGrid(point).getColor()) {
					validReverse = true;
				} else {
					break;
				}
			} catch (IndexOutOfBoundsException ex) {
				break;
			}
		}

		// 反転が有効でない場合、リストクリア
		if (validReverse == false) {
			reverseGrids.clear();
		}
		return reverseGrids;
	}

	private Point move(Point p, Direction direct) {
		Point newP = direct.move(p);
		// 範囲外に動いたか、石が置かれていない場合探索終了
		if (newP.x < 0 || newP.y < 0 || board.getGrid(newP) == Grid.State.None) {
			throw new IndexOutOfBoundsException();
		} else {
			return newP;
		}
	}
	
	private boolean judgeEnd() {
		int gridEdgeNum = board.getGridNum();
		
		for(int i = 0;i < gridEdgeNum;i++) {
			for(int j = 0;j < gridEdgeNum;j++) {
				//空白があればまだ終わっていない
				//TODO この場所が置けるかどうかをチェックする
				if(board.getGrid(new Point(i, j)) == State.None) {
					return false;
				}
			}
		}
		
		return true;
	}

	public static void main(String[] args) {
		BoardManager manager = new BoardManager();

		Board board = new Board("オセロワールド", manager);
		manager.setBoard(board);
		board.setVisible(true);
	}

}
