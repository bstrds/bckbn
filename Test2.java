import java.util.ArrayList;

public class Test2 {
	
	public static void main(String[] args) {
		
		Board b = new Board();
		
		b.print();
		
		int d1 = (int)(Math.random()*6) + 1;
		int d2 = (int)(Math.random()*6) + 1;
		
		//d1 = d2 = 6;
		System.out.println("\n\nWhite rolled "+d1+" and "+d2+"\n\n");
		
		ArrayList<Board> children = b.getChildren(d1, d2, 1);
		
		for (Board child : children) {
			
			child.print();
			System.out.println("Child Evaluation = "+child.evaluate());
			
		}
	}
}