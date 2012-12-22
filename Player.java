import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Player {
    
	public static final int MAX = 1;
	public static final int MIN = -1;
	public static final int NEWBOARD = new Board().hashCode();
	private byte maxDepth;
	private byte playerColor;
	private byte d1;
	private byte d2;
	
	public Player() {
		
		maxDepth = 2;
		playerColor = Board.B;
	}
	
	public Player(byte maxDepth, byte playerColor) {
		
		this.maxDepth = maxDepth;
		this.playerColor = playerColor;
	}
	
	public void roll() {
		
		this.d1 = (byte)((Math.random()*6)+1);
		this.d2 = (byte)((Math.random()*6)+1);
	}
	
	public byte getD1() {
		
		return this.d1;
	}
	
	public byte getD2() {
			
		return this.d2;
	}
	
	public byte getDepth() {
		
		return this.maxDepth;
	}
	
	public Move[] inputMove(Board b) {
		
		if(b.getChildren(d1, d2, playerColor).isEmpty()) {
			if(playerColor==Board.W)
				System.out.println("White rolled "+d1+" and "+d2+" , but it's not very effective..");
			else 
				System.out.println("Black rolled "+d1+" and "+d2+" , but it's not very effective..");
			return null;
		}
			
		
		Move[] moves;
		String fr;
		String t;
		int from = -1;
		int to = -1;
		boolean gotit = false;
		boolean dubs = (d1==d2);
		boolean direction = false;
		
		/* to determine which dice was played */
		boolean pD1 = false;
		boolean pD2 = false;
		
		/* makes new Move array with length depending on
		 * whether or not we have doubles 
		 */
		if(dubs)
			moves = new Move[4];
		else
			moves = new Move[2];
		
		/* creates a temporary board so that we can 
		 * display moves while a turn has not finished
		 */
		Board tempB = new Board(b);
		
		for(int i=0; i<moves.length; i++) { 
			
			InputStreamReader read = new InputStreamReader(System.in);
			BufferedReader in = new BufferedReader(read);
			
			if(playerColor==Board.W)
				System.out.println("White rolled "+d1+" and "+d2+" .");
			else 
				System.out.println("Black rolled "+d1+" and "+d2+" .");
			
			//Position[] tempP = tempB.getPositions();
			
			/* checking if we are in lastrun mode */
			boolean lastrun = tempB.lastrun(playerColor);
			
			while(!gotit) {
				
				try {
					
					System.out.println("Enter where you want to move from :");
					fr = in.readLine();
					from = Integer.parseInt(fr);
					System.out.println("Enter where you want to move to :");
					t = in.readLine();
					to = Integer.parseInt(t);
					
					/* checking if everyone is moving in the right direction */
					direction = (((playerColor==Board.W) && ((to-from)>0)) || ((playerColor==Board.B) && ((to-from)<0))); 
					
					if((Math.abs(from-to) == d1) && direction && (!pD1 || dubs) || lastrun) {
						
						
						/* making the move on the temporary board
						 * for displaying purposes
						 */
						if(tempB.moveIsLegal((byte)from, (byte)to, playerColor, d1, d2)) {
							
							if((!dubs && i==0)||(dubs && i!=3)) {
								tempB.playMove((byte)from, (byte)to, playerColor);
								tempB.print();
							}
							pD1 = true;
							gotit = true;
						} else {
							System.out.println("\nIllegal move, try again0. \n");
						}
						
					} else if((Math.abs(from-to) == d2) && direction && (!pD2 || dubs) || lastrun)  {
					
						/* same as above */
						if(tempB.moveIsLegal((byte)from, (byte)to, playerColor, d1, d2)) {
							
							if((!dubs && i==0)||(dubs && i!=3)) {
								tempB.playMove((byte)from, (byte)to, playerColor);
								tempB.print();
							}
							pD2 = true;
							gotit = true;
						} else {
							System.out.println("\nIllegal move, try again1. \n");
						}	
					}else {
					
						System.out.println("\nIllegal move, try again2. \n");
					}
					
				} catch(Exception e) {
					
					System.out.println("\nTry again3.\n");
					//e.printStackTrace();
				}
			}
			gotit = false;
			moves[i] = new Move((byte)from, (byte)to, playerColor);
			if(tempB.isTerminal()) {
				Move[] tempM = new Move[i+1];
				for(int c=0; c<tempM.length; c++) {
					tempM[c] = moves[c];
				}
				return tempM;
			}
		}
			
		return moves;
	}
	
	public Board MiniMax(Board b, byte d1, byte d2) {
		
		
		
		if(playerColor==Board.B) {
			
			Board max = max(new Board(b), 0, d1 ,d2);
			
			/* if there are no legal moves to be played */
			if(max.hashCode()==NEWBOARD) {
				//System.out.println("\n\nFUUUUU\n\n");
				return b;
			} else {
				return max;
			}
			
		} else { /* if white player is to play */
			
			Board min = min(new Board(b), 0, d1, d2);
			
			/* if there are no legal moves to be played */
			if(min.hashCode()==NEWBOARD) {
				//System.out.println("\n\nFUUUUU\n\n");
				return b;
			} else {
				return min;
			}
		}
	}
	
	public Board min(Board b, int depth, byte d1, byte d2) {
		
		if(b.isTerminal() || depth>=maxDepth) {
			
			return b;
		}
		
		Board minBoard = new Board();
		
		if(depth==0) {
			
			ArrayList<Board> children = b.getChildren(d1, d2, Board.W);
			
			int min = Integer.MAX_VALUE;
			
			for(Board child : children) {
				
				max(child, depth+1, d1, d2);
				
				if(child.getValue() < min) {
					
					min = child.getValue();
					minBoard = new Board(child);
				}
			}
			
			return minBoard;
			
		} else {
			
			ArrayList<Board> children = new ArrayList<Board>();
			ArrayList<Board> tmp;
			
			for(byte i=0; i<21; i++) {
				
				if(i<6) {
					d1 = 1;
					d2 = (byte)(i+1);
				} else if(i<11) {
					d1 = 2;
					d2 = (byte)(i-4);
				} else if(i<15) {
					d1 = 3;
					d2 = (byte)(i-8);
				} else if(i<18) {
					d1 = 4;
					d2 = (byte)(i-11);
				} else if(i<20) {
					d1 = 5;
					d2 = (byte)(i-13);
				} else {
					d1 = 6;
					d1 = 6;
				}
					
				tmp = new ArrayList<Board>(b.getChildren(d1, d2, Board.W));
				
				for(Board child : tmp) {
					/* setting the dice that were played on the 
					 * board for future use */
					child.setd1Pl(d1);
					child.setd2Pl(d2);
					
					children.add(child);
				}
			}
			
			int counter = 0;
			
			int value = 0;
			
			for(Board child : children) {
				
				Board temp = max(child, depth+1, d1, d2);
				
				byte D1 = child.getd1Pl();
				byte D2 = child.getd2Pl();
				
				if(temp!=null) {
					
					counter++;
					
					if(D1==D2) {
						value += temp.evaluate()*2;
					} else {
						value += temp.evaluate();
					}
				} else {
					
					counter++;
					
					if(D1==D2) {
						value += child.getValue()*2;
					} else {
						value += child.getValue();
					}
				}
			}
			if(counter!=0) 
				b.setValue(value/counter);
			else 
				b.setValue(0);
		}
		return null;
	}
	
	public Board max(Board b, int depth, byte d1, byte d2) {
		
		if(b.isTerminal() || depth>=maxDepth) {
			
			return b;
		}	
		
		Board maxBoard = new Board();
		
		if(depth==0) {
			
			ArrayList<Board> children = b.getChildren(d1, d2, Board.B);
			
			int max = Integer.MIN_VALUE;
			
			for(Board child : children) {
				
				min(child, depth+1, d1, d2);
				
				if(child.getValue() > max) {
					
					max = child.getValue();
					maxBoard = new Board(child);
				}
			}
			
			return maxBoard;
			
		} else {
			
			ArrayList<Board> children = new ArrayList<Board>();
			ArrayList<Board> tmp;
			
			for(byte i=1; i<7; i++) {
				
				if(i<6) {
					d1 = 1;
					d2 = (byte)(i+1);
				} else if(i<11) {
					d1 = 2;
					d2 = (byte)(i-4);
				} else if(i<15) {
					d1 = 3;
					d2 = (byte)(i-8);
				} else if(i<18) {
					d1 = 4;
					d2 = (byte)(i-11);
				} else if(i<20) {
					d1 = 5;
					d2 = (byte)(i-13);
				} else {
					d1 = 6;
					d1 = 6;
				}
				
				tmp = new ArrayList<Board>(b.getChildren(d1, d2, Board.B));
				
				for(Board child : tmp) {
					
					child.setd1Pl(d1);
					child.setd2Pl(d2);
					
					children.add(child);
				}
			}

			int counter = 0;
			
			int value = 0;
			
			for(Board child : children) {
				
				Board temp = min(child, depth+1, d1, d2);
				
				byte D1 = child.getd1Pl();
				byte D2 = child.getd2Pl();
				
				if(temp!=null) {
					
					counter++;
					
					if(D1==D2) {
						value += temp.evaluate_modie()*2;
					} else {
						value += temp.evaluate_modie();
					}
				} else {
					
					counter++;
					
					if(D1==D2) {
						value += child.getValue()*2;
					} else {
						value += child.getValue();
					}
				}
			}
			if(counter!=0)
				b.setValue(value/counter);
			else
				b.setValue(0);
		}
		return null;
	}
}
