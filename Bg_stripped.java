//Αθανάσιος Τσιακούλιας Μανέττας - 3100190, Γιώργος Κυπριανίδης - 3100225

public class Bg_stripped {

	/* this is meant to be used with the
	 * "timeloop" bash script, and is of
	 * no use at all for regular games.
	 */

	public static void main(String[] args)
	{
		Player Bplayer = new Player((byte)2, Board.B);
		Player Wplayer = new Player((byte)2, Board.W);

		Board b = new Board();

		while(!b.isTerminal())
		{
			switch(b.getLastColPlayed())
			{
				case Board.B:

					Wplayer.roll();

					b = new Board(Wplayer.MiniMax(b, Wplayer.getD1(), Wplayer.getD2()));
					b.setLastColPlayed(Board.W);

					break;

				case Board.W:


					Bplayer.roll();

					b = new Board(Bplayer.MiniMax(b, Bplayer.getD1(), Bplayer.getD2()));
					b.setLastColPlayed(Board.B);

					break;
				default:
					break;
			}
		}
		Position[] pos = b.getPositions();
		if(pos[26].getNum()==15)
			System.out.println("White Wins!");
		else if(pos[27].getNum()==15)
			System.out.println("Black Wins!");
	}
}
