import java.util.ArrayList;

public class Test2 {
	
	public static void main(String[] args) {
		
		Board b = new Board();
		
		b.print();
		
		ArrayList<Board> children = b.getChildren(1, 4, 1);
		
		for (Board child : children) {
			
			child.print();
		}
	}
}