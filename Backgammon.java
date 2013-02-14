//Αθανάσιος Τσιακούλιας Μανέττας - 3100190, Γιώργος Κυπριανίδης - 3100225

public class Backgammon {
	
	/* to clear the screen */
	final static String ESC = "\033[";
	
	public static void main(String[] args) {
		
		if(args.length<2) {
			System.out.println("Usage: java Backgammon [black player depth] [white player depth]\n" +
					"Ex:    java Backgammon 2 3\n\n" +
					"A depth with value '0' can be given for humans to play.\n" +
					"Recommended depth values are 1-3.\nAnything bigger than that will resul in a very slow game :)\n\n" +
					"Supplying both depths is now mandatory (O`_´O)\n");
			return;
		}

		Player Bplayer = null;
		Player Wplayer = null;
	
		try {
			byte b_depth = Byte.parseByte(args[0]);
			byte w_depth = Byte.parseByte(args[1]);
			if(b_depth<0 || w_depth<0) {
				System.out.println("Player depth values *have* to be positive integers :P");
				return;
			}
			Bplayer = new Player(b_depth, Board.B);
			Wplayer = new Player(w_depth, Board.W);
		} catch(Exception e) {
			System.out.println("Player depth values *have* to be positive integers :P");
			return;
		}
	
		/* initializing the Move boards 
		 * for human players*/
		Move Wmove[] = new Move[0];
		Move Bmove[] = new Move[0];
		
		int counter = 0;
		int movecounter = 0;
		
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
						
						Wmove = Wplayer.inputMove(b);
						
						if(Wmove != null) {
							for(int i=0; i<Wmove.length; i++) {
								if(b.moveIsLegal(Wmove[i].getFrom(), Wmove[i].getTo(), Board.W, Wplayer.getD1(), Wplayer.getD2())) {
									b.playMove(Wmove[i].getFrom(), Wmove[i].getTo(), Board.W );
									counter = 0;
								}
							}
						} else {
							b.setLastColPlayed(Board.W);
							counter = 0;
						}
					} else {
						
						System.out.println("White rolled "+Wplayer.getD1()+" and "+Wplayer.getD2()+" .");
						
						b = new Board(Wplayer.MiniMax(b, Wplayer.getD1(), Wplayer.getD2()));
						
						b.setLastColPlayed(Board.W);

						counter = 0;
						
						movecounter++;
					}
					
					break;
				
				case Board.W:
					
					System.out.println("Black moves");
					
					if(counter==1)
						Bplayer.roll();
						
					if(Bplayer.getDepth()==0) {
						
						Bmove = Bplayer.inputMove(b);
						
						if(Bmove!=null) {	
							for(int i=0; i<Bmove.length; i++) {
								if(b.moveIsLegal(Bmove[i].getFrom(), Bmove[i].getTo(), Board.B, Bplayer.getD1(), Bplayer.getD2())) {
									b.playMove(Bmove[i].getFrom(), Bmove[i].getTo(), Board.B );
									counter = 0;
								}
							}
						} else {
							b.setLastColPlayed(Board.B);
							counter = 0;
						}
					} else {
						System.out.println("Black rolled "+Bplayer.getD1()+" and "+Bplayer.getD2()+" .");
						
						b = new Board(Bplayer.MiniMax(b, Bplayer.getD1(), Bplayer.getD2()));
						
						b.setLastColPlayed(Board.B);
						
						counter = 0;
						
						movecounter++;
					}
					
					break;
				default:
					break;
			}
			
			//System.out.print(ESC + "2J"); 
			b.print();
			
			/* uncomment this if you want the hashcode to be 
			 * printed with each board 
			 */
			//System.out.println("Board Hashcode : "+b.hashCode());
			
			System.out.println("Board value = "+b.getValue());

		}
		Position[] pos = b.getPositions();
		if(pos[26].getNum()==15)
			System.out.println("White Wins!");
		else if(pos[27].getNum()==15)
			System.out.println("Black Wins!");
		
		System.out.println("Moves played : "+movecounter);
	}
}
