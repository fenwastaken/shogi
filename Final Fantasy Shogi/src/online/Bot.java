package online;

import gui.Board;
import gui.InputName;
import gui.InviteNotification;

import java.io.IOException;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.PircBot;

public class Bot extends PircBot {

	public String botName = "shogitest";
	String irc = "irc.esper.net";
	public String channel = "#shogiroom";
	String name = null;
	InputName IN = null;
	Board board = null;
	
	
	public Bot(String name, InputName IN, Board board){
		
		this.botName = "sp0-" + name;
		//this.setVerbose(true);
		
		this.setName(botName);
		this.setLogin("shogiEnthusiast");
		this.setVersion("It's a  duel to the death.");
		this.setAutoNickChange(true);
		
		this.IN = IN;
		this.board = board;
		
		try {
			this.connect(irc);
			System.out.println("Connected to esper.");
			this.joinChannel(channel);
		} catch (IOException | IrcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Couldn't connect to esper");
			dcing();
		}
		
	}
	
	@Override
	protected void onConnect() {
		// TODO Auto-generated method stub
		super.onConnect();
		Board.bot = this;
		Board.connected = true;
		
		//displaying connected
		name = this.getNick();
		Board.setSelectionFrameVisible(true);
		Board.labPrinter.setText("Connected!");
		Board.labPiecePrinter.setText("(as " + name.substring(name.indexOf("sp") + 4) + ")");
		
		//enabling invite menu
		Board.miInvite.setEnabled(true);
		
		//closing the inputName GUI
		IN.dispose();

		
	}
	
	@Override
	protected void onDisconnect() {
		// TODO Auto-generated method stub
		super.onDisconnect();
		dcing();
		
	}
	
	@Override
	protected void onPrivateMessage(String sender, String login,
			String hostname, String message) {
		// TODO Auto-generated method stub
		super.onPrivateMessage(sender, login, hostname, message);
		
		if(sender.equals(Board.matchup)){
			Board.addMessage(sender.substring(4) + ": " + message);
		}
		
		if(message.equals("|$> I would like to play with you") && Board.isInvited == false && Board.hasInvited == false){
			InviteNotification INO = new InviteNotification(sender, board);
			INO.setVisible(true);
		}
		
		if(message.equals("|$> I refuse your challenge") && Board.hasInvited && Board.inviting.equals(sender)){
			Board.invitedRefused(sender);
		}
		
		if(message.equals("|$> I accept your challenge") && sender.toLowerCase().substring(4).equals(Board.inviting.toLowerCase().substring(4))){
			
			//game start! as the active King player
			Board.resetBoard(board);
			//here tf
			Board.isJade = false;
			Board.matchup = sender;
			Board.isMyTurn = true;
			Board.gameOver = false;
			Board.miInvite.setEnabled(false);
			
			Board.setChatVisible(true);
			Board.setSelectionFrameVisible(true);
			Board.labPrinter.setText("You're King");
			Board.labPiecePrinter.setText(sender + " is Jade");
			Board.vCells.firstElement().paintPieces();
			
			//room stuff
			Board.bot.changeNick("sp1-" + Board.bot.botName.substring(4));
			Board.matchup = "sp1-" + Board.matchup.substring(4);
			
		}
		
		if(message.equals("|$> Halas I disconnected") && sender.toLowerCase().equals(Board.matchup.toLowerCase())){

			System.out.println("DC from Halas");
			Board.setSelectionFrameVisible(true);
			Board.labPrinter.setText(Board.matchup.substring(4));
			Board.labPiecePrinter.setText("disconnected");
			Board.lpane.revalidate();
			Board.lpane.repaint();
			
			Board.miInvite.setLabel("Invite player");
			Board.miInvite.setEnabled(true);

			Board.isMyTurn = true;
			Board.gameOver = true;
			Board.matchup = null;
			Board.isInvited = false;
			Board.hasInvited = false;
			Board.inviting = null;
		}
		
		if(message.startsWith("|$> move") && sender.toLowerCase().equals(Board.matchup.toLowerCase()) && !Board.isMyTurn){
			String origin = message.substring(message.indexOf(";") + 1, message.indexOf(":"));
			String destination = message.substring(message.indexOf(":") + 1, message.indexOf("%"));
			String promote = message.substring(message.indexOf("%") + 1 );
			System.out.println("origin("+origin+") destination("+destination+")");
			Board.autoPieceMover(origin, destination, promote);
		}

		if(message.startsWith("|$> take") && sender.toLowerCase().equals(Board.matchup.toLowerCase()) && !Board.isMyTurn){
			String origin = message.substring(message.indexOf(";") + 1, message.indexOf(":"));
			String destination = message.substring(message.indexOf(":") + 1, message.indexOf("%"));
			String promote = message.substring(message.indexOf("%") + 1 );
			System.out.println("origin("+origin+") destination("+destination+")");
			Board.autoPieceTaker(origin, destination, promote);
		}
		
		if(message.startsWith("|$> drop") && sender.toLowerCase().equals(Board.matchup.toLowerCase()) && !Board.isMyTurn){
			String destination = message.substring(message.indexOf(";") + 1, message.indexOf(":"));
			String type = message.substring(message.indexOf(":") + 1, message.indexOf("%"));
			boolean isTop = Boolean.parseBoolean(message.substring(message.indexOf("%") + 1 ));
			System.out.println("destination("+destination+") type("+type+")");
			Board.autoPieceDropper(destination, type, isTop);
		}
		
	}
	
	public void dcing(){
		//reset menu
		Board.miInvite.setLabel("Invite player");
		Board.miInvite.setEnabled(false);
		Board.miConnect.setLabel("Connect");
		//reset variables
		Board.inviting = null;
		Board.isMyTurn = true;
		Board.gameOver = true;
		Board.matchup = null;
		Board.isInvited = false;
		Board.hasInvited = false;
		
		Board.setSelectionFrameVisible(true);
		Board.labPrinter.setText("Disconnected.");
		Board.labPiecePrinter.setText("");
		Board.lpane.revalidate();
		Board.lpane.repaint();
		
		Board.connected = false;
		Board.bot = null;
	}
	
}
