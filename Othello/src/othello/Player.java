package othello;

public class Player {
	private int gridCount;

	Player() {
		init();
	}
	
	public void init() {
		gridCount = 2;
	}
	
	public void addCount() {
		gridCount++;
		System.out.println("add count=" + gridCount);
	}
	
	public void decreaseCount() {
		gridCount--;
		System.out.println("decrease count=" + gridCount);
	}
	
	public int getCount() {
		return gridCount;
	}
	
}
