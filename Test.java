public class Test {
	
	public static void main(String[] args) {
		
		Player Bplayer = new Player(0, Board.B);
		Player Wplayer = new Player(0, Board.W);
		
		/* initializing the Move boards */
		Move Wmove[] = new Move[0];
		Move Bmove[] = new Move[0];
		
		int counter = 0;
		
		Board b = new Board();
		
		b.print();
		
		while(!b.isTerminal()) {
			
			System.out.println();
			counter++;
			
			switch(b.getLastColPlayed()) {
				
				case Board.B:
					
					System.out.println("White moves");
					
					if(counter==1)
						Wplayer.roll();
					
					/* this means human player */
					if(Wplayer.getDepth()==0) {
						
						Wmove = Wplayer.inputMove(Wplayer.getD1(), Wplayer.getD2(), b);
					} else {
						
					}
					for(int i=0; i<Wmove.length; i++) {
						if(b.moveIsLegal(Wmove[i].getFrom(), Wmove[i].getTo(), Board.W)) {
							b.playMove(Wmove[i].getFrom(), Wmove[i].getTo(), Board.W );
							counter = 0;
						}
					}
					break;
				
				case Board.W:
					
					System.out.println("Black moves");
					
					if(counter==1)
						Bplayer.roll();
						
					if(Bplayer.getDepth()==0) {
						
						Bmove = Bplayer.inputMove(Bplayer.getD1(), Bplayer.getD2(), b);
					} else {
						
					}
					for(int i=0; i<Bmove.length; i++) {
						if(b.moveIsLegal(Bmove[i].getFrom(), Bmove[i].getTo(), Board.B)) {
							b.playMove(Bmove[i].getFrom(), Bmove[i].getTo(), Board.B );
							counter = 0;
						}
					}
					break;
				default:
					break;
			}
			b.print();
		}
	}
}
