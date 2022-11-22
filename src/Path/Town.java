package Path;

public class Town {
	int name;
	int c;
	int v;
	public Town(int name, int c, int v) {
		this.name = name;
		this.c = c;
		this.v = v;
	}	
	public String toString() {
		return this.name + "[" + c + ":" + v + "]";
	}
}
