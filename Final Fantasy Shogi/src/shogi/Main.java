package shogi;

import gui.Board;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//this uses esper.net as a webservice because I'm a lazy shit and I don't care that it isn't secured
		
	    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
	        public void run() {
	            if(Board.connected && Board.matchup != null && Board.bot != null){
	            	Board.bot.sendMessage(Board.matchup, "|$> Halas I disconnected");
	            }
	        }
	    }, "Shutdown-thread"));
		
		Board board = new Board();
		board.setVisible(true);
		
	}

}
