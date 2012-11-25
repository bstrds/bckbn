public class Test {
	
	public static void main(String[] args) {
		
		Player Bplayer = new Player(0, Board.B);
		Player Wplayer = new Player(0, Board.W);
		
		int counter = 0;
		
		Board b = new Board();
		
		b.print();
		
		while(!b.isTerminal()) {
			
			System.out.println();
			counter++;
			
			switch(b.getLastColPlayed()) {
				
				case Board.B:
					
					Move Wmove = new Move();
					System.out.println("White moves");
					
					if(counter==1)
						Wplayer.roll();
					
					/* this means human player */
					if(Wplayer.getDepth()==0) {
						
						Wmove = Wplayer.inputMove(Wplayer.getD1(), Wplayer.getD2());
					} else {
						
					}
					if(b.moveIsLegal(Wmove.getFrom(), Wmove.getTo(), Board.W)) {
						b.playMove(Wmove.getFrom(), Wmove.getTo(), Board.W );
						counter = 0;
					}
					break;
				
				case Board.W:
					
					Move Bmove = new Move();
					System.out.println("Black moves");
					
					if(counter==1)
						Bplayer.roll();
						
					if(Bplayer.getDepth()==0) {
						
						Bmove = Bplayer.inputMove(Bplayer.getD1(), Bplayer.getD2());
					} else {
						
					}
					if(b.moveIsLegal(Bmove.getFrom(), Bmove.getTo(), Board.B)) {
						b.playMove(Bmove.getFrom(), Bmove.getTo(), Board.B );
						counter = 0;
					}
					break;
				default:
					break;
			}
			b.print();
		}
	}
}
