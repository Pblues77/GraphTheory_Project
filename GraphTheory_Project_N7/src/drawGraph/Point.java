package drawGraph;

public class Point {
	private int x;
	private int y;
	private int d;
	private String id;
	private String name;

	

	public Point(int x, int y, int d, String id, String name) {
		super();
		this.x = x;
		this.y = y;
		this.d = d;
		this.id = id;
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setX(int x) {
		this.x = x;
	}
}
