package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

import online.Bot;
import pieces.Bishop;
import pieces.Gold;
import pieces.Jking;
import pieces.King;
import pieces.Knight;
import pieces.Lancer;
import pieces.Pawn;
import pieces.Piece;
import pieces.Rook;
import pieces.Silver;

public class Board extends JFrame{

	public static String version = " - Version 2.3";

	public static JLayeredPane lpane = new JLayeredPane();
	public static JLabel labPrinter = new JLabel();
	public static JLabel labPiecePrinter = new JLabel();
	public static JLabel labFrame = new JLabel();
	public static JLabel labPromoteFrame = new JLabel();
	public static JLabel labPromoteFrameText = new JLabel("Promote?");
	public static JLabel labPromoteFrameYes = new JLabel("Yes");
	public static JLabel labPromoteFrameNo = new JLabel("No");
	public static JTextArea taChat = new JTextArea();
	public static JTextField tfChat = new JTextField();
	public static JScrollPane spanChat = new JScrollPane(taChat);

	public static Piece selectedPiece = null;
	public static Cell selectedCell = null;
	public static Holder selectedHolder = null;
	public static Vector<Cell> vCells = new Vector<Cell>();
	public static Vector<Piece> vPieces = new Vector<Piece>();
	public static Vector<Holder> vHolders = new Vector<Holder>();
	public static boolean gameOver = true;
	public static boolean jadeTurn = false;
	public static boolean promoting = false;
	public static boolean isMyTurn = true; //define turn order in online matches

	//online
	public static Piece lastPlayed = null;
	public static String matchup = null;
	public static boolean isJade = false; //check the player
	public static boolean connected = false;
	public static boolean isInvited = false;
	public static boolean hasInvited = false;
	public static Bot bot = null;
	public static String inviting = null;
	public static String xml = "";

	//debug
	boolean visible = true;
	public static boolean paintBg = true;

	//dimensions---------------------------------------
	int boardXsize = 1070;
	int boardYsize = 770;

	int frameXmargin = 6;
	int frameYmargin = 28;

	int menuYmargin = 20;

	Dimension dTotalSize = new Dimension(boardXsize + frameXmargin, boardYsize + frameYmargin + menuYmargin);
	Dimension dSprite = new Dimension(60, 80);

	//creating sprites------------------------------------------------------------------------------------
	ImageIcon spriteKingA = new ImageIcon (this.getClass().getResource("/sprites/kingA.gif"));
	ImageIcon spriteKingT = new ImageIcon (this.getClass().getResource("/sprites/kingT.gif"));
	ImageIcon spriteKingB = new ImageIcon (this.getClass().getResource("/sprites/kingB.gif"));

	ImageIcon spriteJkingA = new ImageIcon (this.getClass().getResource("/sprites/jkingA.gif"));
	ImageIcon spriteJkingT = new ImageIcon (this.getClass().getResource("/sprites/jkingTest.gif"));
	ImageIcon spriteJKingB = new ImageIcon (this.getClass().getResource("/sprites/jkingB.gif"));

	ImageIcon spriteGoldA = new ImageIcon (this.getClass().getResource("/sprites/goldA.gif"));
	ImageIcon spriteGoldT = new ImageIcon (this.getClass().getResource("/sprites/goldTest.gif"));
	ImageIcon spriteGoldB = new ImageIcon (this.getClass().getResource("/sprites/goldB.gif"));

	ImageIcon spritePawnA = new ImageIcon (this.getClass().getResource("/sprites/pawnA.gif"));
	ImageIcon spritePawnT = new ImageIcon (this.getClass().getResource("/sprites/pawnT.gif"));
	ImageIcon spritePawnB = new ImageIcon (this.getClass().getResource("/sprites/pawnB.gif"));
	ImageIcon spritePawnZT = new ImageIcon (this.getClass().getResource("/sprites/pawnZT.gif"));
	ImageIcon spritePawnZB = new ImageIcon (this.getClass().getResource("/sprites/pawnZB.gif"));

	ImageIcon spriteLancerA = new ImageIcon (this.getClass().getResource("/sprites/lancerA.gif"));
	ImageIcon spriteLancerT = new ImageIcon (this.getClass().getResource("/sprites/lancerT.gif"));
	ImageIcon spriteLancerB = new ImageIcon (this.getClass().getResource("/sprites/lancerB.gif"));
	ImageIcon spriteLancerZT = new ImageIcon (this.getClass().getResource("/sprites/lancerZT.gif"));
	ImageIcon spriteLancerZB = new ImageIcon (this.getClass().getResource("/sprites/lancerZB.gif"));

	ImageIcon spriteHorseA = new ImageIcon (this.getClass().getResource("/sprites/horseA.gif"));
	ImageIcon spriteHorseT = new ImageIcon (this.getClass().getResource("/sprites/horseT.gif"));
	ImageIcon spriteHorseB = new ImageIcon (this.getClass().getResource("/sprites/horseB.gif"));
	ImageIcon spriteHorseZT = new ImageIcon (this.getClass().getResource("/sprites/horseZT.gif"));
	ImageIcon spriteHorseZB = new ImageIcon (this.getClass().getResource("/sprites/horseZB.gif"));

	ImageIcon spriteSilverA = new ImageIcon (this.getClass().getResource("/sprites/silverA.gif"));
	ImageIcon spriteSilverT = new ImageIcon (this.getClass().getResource("/sprites/silverT.gif"));
	ImageIcon spriteSilverB = new ImageIcon (this.getClass().getResource("/sprites/silverB.gif"));
	ImageIcon spriteSilverZT = new ImageIcon (this.getClass().getResource("/sprites/silverZT.gif"));
	ImageIcon spriteSilverZB = new ImageIcon (this.getClass().getResource("/sprites/silverZB.gif"));

	ImageIcon spriteRookA = new ImageIcon (this.getClass().getResource("/sprites/rookA.gif"));
	ImageIcon spriteRookT = new ImageIcon (this.getClass().getResource("/sprites/rookT.gif"));
	ImageIcon spriteRookB = new ImageIcon (this.getClass().getResource("/sprites/rookB.gif"));
	ImageIcon spriteRookZT = new ImageIcon (this.getClass().getResource("/sprites/rookZT.gif"));
	ImageIcon spriteRookZB = new ImageIcon (this.getClass().getResource("/sprites/rookZB.gif"));

	ImageIcon spriteKnightA = new ImageIcon (this.getClass().getResource("/sprites/knightA.gif"));
	ImageIcon spriteKnightT = new ImageIcon (this.getClass().getResource("/sprites/knightT.gif"));
	ImageIcon spriteKnightB = new ImageIcon (this.getClass().getResource("/sprites/knightB.gif"));
	ImageIcon spriteKnightZT = new ImageIcon (this.getClass().getResource("/sprites/knightZT.gif"));
	ImageIcon spriteKnightZB = new ImageIcon (this.getClass().getResource("/sprites/knightZB.gif"));

	ImageIcon board = new ImageIcon (this.getClass().getResource("/sprites/newBoard3.png"));
	ImageIcon frameTop = new ImageIcon (this.getClass().getResource("/sprites/frame2.png"));
	ImageIcon framePromote = new ImageIcon (this.getClass().getResource("/sprites/frame2.png"));
	ImageIcon about = new ImageIcon (this.getClass().getResource("/sprites/about.png"));

	JLabel bg = new JLabel(board);
	JLabel labAbout = new JLabel(about);

	//templates
	public static King kingTemplate = new King();
	public static Jking jkingTemplate = new Jking();
	public static Pawn pawnTemplate = new Pawn();
	public static Gold goldTemplate = new Gold();
	public static Silver silverTemplate = new Silver();
	public static Knight horseTemplate = new Knight();
	public static Lancer lancerTemplate = new Lancer();
	public static Bishop rookTemplate = new Bishop();
	public static Rook knightTemplate = new Rook();

	//menus
	public static MenuItem miNewLocalGame = new MenuItem("New game");
	public static MenuItem miConnect = new MenuItem("Connect");
	public static MenuItem miInvite = new MenuItem("Invite player");
	public static MenuItem miAbout = new MenuItem("About");
	MenuItem miResetBoard = new MenuItem("Reset board");

	public Board(){
		this.setTitle("Final Fantasy Shogi" + version);
		this.setSize(dTotalSize);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		initControles();

	}

	public void initControles(){

		JPanel zoneClient = (JPanel) this.getContentPane();


		lpane.setPreferredSize(new Dimension(boardXsize, boardYsize));
		zoneClient.add(lpane);


		initBoard();

		lpane.addMouseListener(new appMouseListener());


		//Menu ----------------------------------------------------
		MenuBar menuBar = new MenuBar();
		this.setMenuBar(menuBar);

		Menu meGame = new Menu("Game");
		menuBar.add(meGame);
		//meGame.setEnabled(false); disable game

		Menu meLocal = new Menu("Local");
		meGame.add(meLocal);

		meLocal.add(miNewLocalGame);

		Menu meOnline = new Menu("Online");
		meGame.add(meOnline);


		meOnline.add(miConnect);


		meOnline.add(miInvite);

		miInvite.setEnabled(false);

		Menu meSettings = new Menu("Settings");
		menuBar.add(meSettings);

		meSettings.add(miResetBoard);

		Menu meAbout = new Menu("About");
		menuBar.add(meAbout);


		meAbout.add(miAbout);

		//menu listeners
		miNewLocalGame.addActionListener(new menuActionListener());
		miConnect.addActionListener(new menuActionListener());
		miResetBoard.addActionListener(new menuActionListener());
		miInvite.addActionListener(new menuActionListener());
		miAbout.addActionListener(new menuActionListener());

		tfChat.addActionListener(new menuActionListener());


		//printing
		initPrinters();

		//cells---------------------------------------------
		initCells();

		//pieces init--------------------------------
		initPieces();

		//holders
		initHolders();

		setSelectionFrameVisible(true);
		labPrinter.setText(" Start a new");
		labPiecePrinter.setText("     game!");

		labPromoteFrameYes.addMouseListener(new appMouseListenerPromote());
		labPromoteFrameNo.addMouseListener(new appMouseListenerPromote());
		labPromoteFrame.addMouseListener(new appMouseListenerPromote());
		labFrame.addMouseListener(new appMouseListenerPromote());


	}

	public void initPrinters(){
		labPrinter.setFont(new Font("Serif", Font.PLAIN, 35));
		labPrinter.setBounds(845, 17, 240, 120);
		//printer.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		lpane.add(labPrinter, 0);
		//piecePrinter
		labPiecePrinter.setFont(new Font("Serif", Font.PLAIN, 25));
		labPiecePrinter.setBounds(880, 42, 240, 120);
		labPiecePrinter.setText("");
		lpane.add(labPiecePrinter, 0);

		labFrame.setBounds(829, 17, 240, 160);
		labFrame.setIcon(frameTop);
		lpane.add(labFrame, 2);

		labPromoteFrame.setBounds(829, 217, 240, 160);
		labPromoteFrame.setIcon(frameTop);
		lpane.add(labPromoteFrame, 2);

		labPromoteFrameText.setFont(new Font("Serif", Font.PLAIN, 35));
		labPromoteFrameText.setBounds(870, 207, 240, 120);
		lpane.add(labPromoteFrameText, 2);

		labPromoteFrameYes.setFont(new Font("Serif", Font.PLAIN, 25));
		labPromoteFrameYes.setBounds(890, 297, 40, 40);
		lpane.add(labPromoteFrameYes, 2);

		labPromoteFrameNo.setFont(new Font("Serif", Font.PLAIN, 25));
		labPromoteFrameNo.setBounds(950, 297, 40, 40);
		lpane.add(labPromoteFrameNo, 2);


		taChat.setEditable(false);
		taChat.setLineWrap(true);
		taChat.setWrapStyleWord(true);
		spanChat.setBounds(15, 380, 230, 330);
		spanChat.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spanChat.setBorder(BorderFactory.createRaisedBevelBorder());
		lpane.add(spanChat, 2);

		((DefaultCaret) taChat.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		tfChat.setBounds(15, 380 + 330 + 6, 230, 30);
		tfChat.setBorder(BorderFactory.createRaisedBevelBorder());
		lpane.add(tfChat, 2);

		setChatVisible(false);

		setSelectionFrameVisible(false);
		setPromoteFrameVisible(false, false);

	}

	public static void resetBoard(Board board){

		if(Board.bot != null){
			Board.bot.changeNick("sp0-" + Board.bot.getNick().substring(4));
		}
		
		Board.miInvite.setEnabled(true);
		Board.miInvite.setLabel("Invite player");

		isInvited = false;
		hasInvited = false;
		isMyTurn = true;
		jadeTurn = false;
		gameOver = true;
		promoting = false;

		lpane.removeAll();
		vCells.removeAllElements();
		vPieces.removeAllElements();
		vHolders.removeAllElements();

		board.initBoard();
		board.initCells();
		board.initHolders();
		board.initPrinters();
		board.initPieces();
		lpane.setComponentZOrder(board.bg, board.lastZ()); //puts the board last again

		setSelectionFrameVisible(true);
		labPrinter.setText(" Start a new");
		labPiecePrinter.setText("     game!");

		setPromoteFrameVisible(false, false);

		if(Board.connected && Board.bot != null && Board.matchup != null){
			Board.setChatVisible(true);
		}

		lpane.revalidate();
		lpane.repaint();
	}




	public void newGame(){
		gameOver = false;
		Board.vCells.firstElement().paintPieces();
		setSelectionFrameVisible(true);
		labPiecePrinter.setText("");
		labPrinter.setText(" King starts!");
	}

	public void initBoard(){
		bg.setBounds(0, 0, boardXsize, boardYsize);
		lpane.add(bg, lastZ());
	}

	public void initHolders(){
		for(int j = 1; j < 3; j++){
			String side = "";
			int xPos = 0;
			int yPos = 0;

			int x = 0;
			int y = 0;

			int height = 80;
			int width = 60;
			int border = 25;

			if(j < 2){
				side = "T-";
				xPos = width + border;
				yPos = border;
			}
			else{
				side = "B-";
				xPos = border + width * 14;
				yPos = border + height * 5;
			}
			for(int i = 1 ; i < 9 ; i++){
				String name = null;
				ImageIcon sprite = null;

				switch(i){
				case 1:
					name = side + "Silver";
					sprite = spriteSilverA;
					x = xPos;
					y = yPos;
					break;
				case 2:
					name = side + "Bishop";
					sprite = spriteRookA;
					x = xPos;
					y = yPos + height;
					break;
				case 3:
					name = side + "Knight";
					sprite = spriteHorseA;
					x = xPos;
					y = yPos + height * 2;
					break;
				case 4:
					name = side + "Pawn";
					sprite = spritePawnA;
					x = xPos;
					y = yPos + height * 3;
					break;
				case 5:
					name = side + "Gold";
					sprite = spriteGoldA;
					x = xPos + width;
					y = yPos;
					break;
				case 6:
					name = side + "Rook";
					sprite = spriteKnightA;
					x = xPos + width;;
					y = yPos + height;
					break;
				case 7:
					if(j<2){
						name = side + "King";
						sprite = spriteJkingA;
						x = xPos + width;
						y = yPos + height * 2;
					}
					else{
						name = side + "Jade";
						sprite = spriteKingA;
						x = xPos + width;
						y = yPos + height * 2;
					}
					break;
				case 8:
					name = side + "Lancer";
					sprite = spriteLancerA;
					x = xPos + width;
					y = yPos + height * 3;
					break;
				}

				Holder holder = new Holder(name, sprite);
				holder.setBounds(x, y, 60, 80);
				holder.spriteBounder();
				vHolders.add(holder);
				holder.addToLpane(1);
				lpane.revalidate();
				lpane.repaint();
			}
		}
	}

	public void initCells(){
		int xStart = 265;
		int yStart = 25;

		for(int i = 1 ; i < 10 ; i++){
			for(int j = 1; j < 10; j++){
				Cell cell = new Cell(i, j);
				cell.setBounds(xStart, yStart, 60, 80);
				int aboveBoardZ = lpane.getComponentZOrder(bg) - 1;
				lpane.add(cell, aboveBoardZ);
				vCells.add(cell);
				xStart = xStart + 60;
			}

			yStart = yStart + 80;
			xStart = 265;

		}
		lpane.repaint();
		lpane.revalidate();
	}

	public void initPieces(){
		initPawns();
		initKings();
		initGold();
		initSilver();
		initHorse();
		initLancer();
		initRook();
		initKnight();
	}

	public void initKnight(){

		knightTemplate.setKnightA(spriteKnightA);
		knightTemplate.setKnightT(spriteKnightT);
		knightTemplate.setKnightB(spriteKnightB);
		knightTemplate.setKnightZT(spriteKnightZT);
		knightTemplate.setKnightZB(spriteKnightZB);

		Rook Knight1 = new Rook(knightTemplate, true, cellFinder("2-2"));
		Rook Knight2 = new Rook(knightTemplate, false, cellFinder("8-8"));

		pieceMaker(Knight1);
		pieceMaker(Knight2);

	}

	public void initRook(){

		rookTemplate.setRookA(spriteRookA);
		rookTemplate.setRookT(spriteRookT);
		rookTemplate.setRookB(spriteRookB);
		rookTemplate.setRookZT(spriteRookZT);
		rookTemplate.setRookZB(spriteRookZB);

		Bishop Rook1 = new Bishop(rookTemplate, true, cellFinder("2-8"));
		Bishop Rook2 = new Bishop(rookTemplate, false, cellFinder("8-2"));

		pieceMaker(Rook1);
		pieceMaker(Rook2);

	}

	public void initLancer(){
		lancerTemplate.setLancerA(spriteLancerA);
		lancerTemplate.setLancerT(spriteLancerT);
		lancerTemplate.setLancerB(spriteLancerB);
		lancerTemplate.setLancerZT(spriteLancerZT);
		lancerTemplate.setLancerZB(spriteLancerZB);

		Lancer Lancer1 = new Lancer(lancerTemplate, true, cellFinder("1-1"));
		Lancer Lancer2 = new Lancer(lancerTemplate, true, cellFinder("1-9"));
		Lancer Lancer3 = new Lancer(lancerTemplate, false, cellFinder("9-1"));
		Lancer Lancer4 = new Lancer(lancerTemplate, false, cellFinder("9-9"));

		pieceMaker(Lancer1);
		pieceMaker(Lancer2);
		pieceMaker(Lancer3);
		pieceMaker(Lancer4);
	}

	public void initHorse(){
		horseTemplate.setHorseA(spriteHorseA);
		horseTemplate.setHorseT(spriteHorseT);
		horseTemplate.setHorseB(spriteHorseB);
		horseTemplate.setHorseZT(spriteHorseZT);
		horseTemplate.setHorseZB(spriteHorseZB);

		Knight Horse1 = new Knight(horseTemplate, true, cellFinder("1-2"));
		Knight Horse2 = new Knight(horseTemplate, true, cellFinder("1-8"));
		Knight Horse3 = new Knight(horseTemplate, false, cellFinder("9-2"));
		Knight Horse4 = new Knight(horseTemplate, false, cellFinder("9-8"));

		pieceMaker(Horse1);
		pieceMaker(Horse2);
		pieceMaker(Horse3);
		pieceMaker(Horse4);
	}

	public void initSilver(){
		silverTemplate.setsilverA(spriteSilverA);
		silverTemplate.setsilverT(spriteSilverT);
		silverTemplate.setsilverB(spriteSilverB);
		silverTemplate.setsilverZT(spriteSilverZT);
		silverTemplate.setsilverZB(spriteSilverZB);

		Silver silver1 = new Silver(silverTemplate, true, cellFinder("1-3"));
		Silver silver2 = new Silver(silverTemplate, true, cellFinder("1-7"));
		Silver silver3 = new Silver(silverTemplate, false, cellFinder("9-3"));
		Silver silver4 = new Silver(silverTemplate, false, cellFinder("9-7"));

		pieceMaker(silver1);
		pieceMaker(silver2);
		pieceMaker(silver3);
		pieceMaker(silver4);

	}

	public void initGold(){
		goldTemplate.setgoldA(spriteGoldA);
		goldTemplate.setgoldT(spriteGoldT);
		goldTemplate.setgoldB(spriteGoldB);

		Gold gold1 = new Gold(goldTemplate, true, cellFinder("1-4"));
		Gold gold2 = new Gold(goldTemplate, true, cellFinder("1-6"));
		Gold gold3 = new Gold(goldTemplate, false, cellFinder("9-4"));
		Gold gold4 = new Gold(goldTemplate, false, cellFinder("9-6"));

		gold1.setPromoted(true);
		gold2.setPromoted(true);
		gold3.setPromoted(true);
		gold4.setPromoted(true);

		pieceMaker(gold1);
		pieceMaker(gold2);
		pieceMaker(gold3);
		pieceMaker(gold4);
	}

	public void initPawns(){
		//pawn
		pawnTemplate.setPawnA(spritePawnA);
		pawnTemplate.setPawnB(spritePawnB);
		pawnTemplate.setPawnT(spritePawnT);
		pawnTemplate.setPawnZB(spritePawnZB);
		pawnTemplate.setPawnZT(spritePawnZT);

		//top
		for(int i = 1 ; i < 10 ; i++){
			Cell cell = cellFinder("3-" + i);
			pieces.Pawn pawn = new pieces.Pawn(pawnTemplate, true, cell);
			pieceMaker(pawn);
		}


		//bot
		for(int i = 1 ; i < 10 ; i++){
			Cell cell = cellFinder("7-" + i);
			Pawn pawn = new Pawn(pawnTemplate, false, cell);
			pieceMaker(pawn);
		}
	}

	public void initKings(){
		kingTemplate.setkingT(spriteKingT);
		kingTemplate.setkingB(spriteKingB);

		jkingTemplate.setjkingT(spriteJkingT);
		jkingTemplate.setjkingB(spriteJKingB);

		King king = new King(kingTemplate, false, cellFinder("9-5"));
		king.setPromoted(true);
		pieceMaker(king);
		Jking jKing = new Jking(jkingTemplate, true, cellFinder("1-5"));
		jKing.setPromoted(true);
		pieceMaker(jKing);




	}

	public static void pieceMaker(Piece piece){
		vPieces.add(piece);
		pieceBounder(piece);
		lpane.add(piece, 1);
	}

	public static void pieceBounder(Piece piece){

		int xPos = piece.getAttachedCell().getX();
		int yPos = piece.getAttachedCell().getY();

		if(piece.getType().equals("Knight")){
			if(piece.isTop()){
				piece.setBounds(xPos + 2, yPos-20, 70,  82);
			}
			else{
				piece.setBounds(xPos - 5, yPos-20, 70,  82);
			}
		}
		else if(piece.getType().equals("Lancer")){
			piece.setBounds(xPos+5, yPos-20, 70,  82);
		}
		else{
			piece.setBounds(xPos+10, yPos-20, 70,  82);
		}
	}

	public static Holder holderFinder(boolean isTop, String type){
		Holder holder = null;
		String side;
		if(isTop){
			side = "B-";
		}
		else{
			side = "T-";
		}
		for(Holder hldr : vHolders){
			if(hldr.getName().equals(side+type)){
				holder = hldr;
			}
		}
		return holder;
	}

	public static Boolean isCellOccupied(Cell cell){
		Boolean occupied = false;
		for(Piece pc : vPieces){
			if(pc.getAttachedCell().getName().equals(cell.getName())){
				occupied = true;
				break;
			}
		}
		return occupied;
	}

	public static Cell cellFinder(String name){
		Cell ret = null;
		for(Cell cell : vCells){
			if(cell.name.equals(name)){
				ret = cell;
				//System.out.println("Found cell " + name);
				break;
			}
		}
		return ret;
	}

	public static Piece pieceFinder(Cell cell){
		Piece ret = null;
		Cell cell2 = cellFinder(cell.name);
		for(Piece piece : vPieces){
			if(piece.getAttachedCell() != null){
				if(piece.getAttachedCell().equals(cell2)){
					ret = piece;
					break;
				}
			}
		}

		return ret;
	}


	public int lastZ(){
		return lpane.getComponentCount() - 1;
	}

	public static void setPromoteFrameVisible(boolean isVisible, boolean isNoVisible){
		labPromoteFrame.setVisible(isVisible);
		labPromoteFrameText.setVisible(isVisible);
		labPromoteFrameYes.setVisible(isVisible);
		labPromoteFrameNo.setVisible(isNoVisible);
	}

	public static void setSelectionFrameVisible(boolean isVisible){
		labFrame.setVisible(isVisible);
		labPrinter.setVisible(isVisible);
		labPiecePrinter.setVisible(isVisible);
	}

	public static void setChatVisible(boolean isVisible){
		spanChat.setVisible(isVisible);
		tfChat.setVisible(isVisible);
	}

	public void bgDeleter(MouseEvent e){
		if(e.getButton() == MouseEvent.BUTTON3){
			if(visible){
				lpane.remove(bg);

				for(Cell cell : vCells){
					cell.setBorder(BorderFactory.createLineBorder(Color.black));
				}

				lpane.repaint();
				lpane.revalidate();
				visible = false;
			}
			else{
				lpane.add(bg, lastZ()+1);

				for(Cell cell : vCells){
					cell.setBorder(null);
				}

				selectedCell = null;
				labPrinter.setText("");

				lpane.repaint();
				lpane.revalidate();
				visible = true;

			}
		}
	}

	public static void promotePiece(Piece piece, boolean yes){
		if(yes){
			piece.setPromoted(true);
			piece.setSprite();
			System.out.println("Promoted " + piece.getType());
		}
		setPromoteFrameVisible(false, false);
		gameOver = false;
	}

	class appMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

			if(e.getButton() == MouseEvent.BUTTON1 && !gameOver && !promoting){
				Board.vHolders.firstElement().deselectHolders();
				System.out.println("location: " + e.getX() + ", " + e.getY());
				Board.vCells.firstElement().paintPieces();
			}

			bgDeleter(e);

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub


		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	class appMouseListenerPromote implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == labPromoteFrameYes && e.getButton() == MouseEvent.BUTTON1){
				Board.xml = Board.xml + "%Y";
				//here
				System.out.println("Yes!");
				promotePiece(Board.selectedPiece, true);
				Board.promoting = false;
				Board.xmlSender();
				Board.vCells.firstElement().turnChanger();
			}

			if(e.getSource() == labPromoteFrameNo && e.getButton() == MouseEvent.BUTTON1){
				Board.xml = Board.xml + "%N";
				System.out.println("No!");
				promotePiece(Board.selectedPiece, false);
				Board.promoting = false;
				Board.xmlSender();
				Board.vCells.firstElement().turnChanger();
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	//online related methods
	public void connect(){

		if(!connected){
			resetBoard(Board.this);
			InputName IN = new InputName(this);
			IN.setVisible(true);
		}
		else{
			resetBoard(Board.this);
			if(Board.bot != null){
				if(Board.matchup != null){
					Board.bot.sendMessage(Board.matchup, "|$> Halas I disconnected"); //notify matchup
				}
				Board.bot.disconnect();
				Board.setChatVisible(false);
			}
		}
	}

	public void invite(){

			if(!hasInvited && !isInvited){
				Inviter invit = new Inviter(this, Board.bot);
				invit.setVisible(true);
			}
			else if(hasInvited && !isInvited){
				Board.hasInvited = false;
				Board.miInvite.setLabel("Invite player");
				Board.setSelectionFrameVisible(true);
				Board.labPrinter.setText("Start");
				Board.labPiecePrinter.setText("a new game!");
			}
			else{
				//if inviting when invited, should refuse first
			}
	}

	public static void invitedRefused(String name){
		Board.hasInvited = false;
		Board.inviting = null;
		Board.miInvite.setLabel("Invite player");
		Board.setSelectionFrameVisible(true);
		Board.labPrinter.setText(name);
		Board.labPiecePrinter.setText("Declined");
	}

	public static void addMessage(String message){
		if(!message.contains("|$>")){
			Board.taChat.append(message + "\n");
		}
	}

	public static void autoPieceMover(String cellOrigin, String cellDestination, String promote){
		Piece pc = pieceFinder(cellFinder(cellOrigin));
		pc.setAttachedCell(cellFinder(cellDestination));
		if(promote.equals("Y")){
			pc.setPromoted(true);
			pc.setSprite();
		}
		pieceBounder(pc);
		Board.lastPlayed = pc;
		vCells.firstElement().turnChanger();
		vCells.firstElement().checkWin();
	}

	public static void autoPieceTaker(String cellOrigin, String cellDestination, String promote){
		Piece pc = pieceFinder(cellFinder(cellDestination));

		Board.holderFinder(pc.isTop(), pc.getType()).increaseCounter();

		Board.lpane.remove(pc);
		Board.vPieces.remove(pc);
		pc.setAttachedCell(null);
		pc = null;
		autoPieceMover(cellOrigin, cellDestination, promote);
	}

	public static void autoPieceDropper(String destination, String type, boolean isTop){
		switch(type){
		case "King" :
			Jking jade = new Jking(Board.jkingTemplate, isTop, Board.cellFinder(destination));
			jade.setPromoted(true);
			Board.pieceMaker(jade);
			break;
		case "Jade" :
			King king = new King(Board.kingTemplate, isTop, Board.cellFinder(destination));
			king.setPromoted(true);
			Board.pieceMaker(king);
			break;
		case "Gold" :
			Gold gold = new Gold(Board.goldTemplate, isTop, Board.cellFinder(destination));
			gold.setPromoted(true);
			Board.pieceMaker(gold);
			break;
		case "Silver" :
			Silver silver = new Silver(Board.silverTemplate, isTop, Board.cellFinder(destination));
			Board.pieceMaker(silver);
			break;
		case "Knight" :
			Knight knight = new Knight(Board.horseTemplate, isTop, Board.cellFinder(destination));
			Board.pieceMaker(knight);
			break;
		case "Lancer" :
			Lancer lancer = new Lancer(Board.lancerTemplate, isTop, Board.cellFinder(destination));
			Board.pieceMaker(lancer);
			break;
		case "Pawn" :
			Pawn pawn = new Pawn(Board.pawnTemplate, isTop, Board.cellFinder(destination));
			Board.pieceMaker(pawn);
			break;
		case "Rook" :
			Rook rook = new Rook(Board.knightTemplate, isTop, Board.cellFinder(destination));
			Board.pieceMaker(rook);
			break;
		case "Bishop" :
			Bishop bishop = new Bishop(Board.rookTemplate, isTop, Board.cellFinder(destination));
			Board.pieceMaker(bishop);
			break;
		}
		Board.lastPlayed = Board.vPieces.lastElement();
		Holder hldr = Board.holderFinder(!isTop, type);
		System.out.println("HOLDER: " + hldr.getName());
		hldr.decreaseCounter();
		vCells.firstElement().turnChanger();
	}

	public static void xmlSender(){
		if(Board.connected && Board.matchup != null && Board.bot != null){
			System.out.println("to " + Board.matchup + "-----------------------" + Board.xml);
			Board.bot.sendMessage(Board.matchup, Board.xml);
			Board.xml = "";
		}
	}

	public static void kingChecker(boolean isKing){
		Piece pc = null;
		String varKing = "";
		String location = "";

		if(isKing){
			varKing = "King";
		}
		else{
			varKing = "Jade";
		}

		for(Cell cl : Board.vCells){
			if(Board.isCellOccupied(cl)){
				pc = Board.pieceFinder(cl);
				if(pc.getType().equals(varKing)){
					location = cl.getName();
				}
			}
		}

		boolean isCheck = false;
		for(Piece pc2 : Board.vPieces){
			if(pc2.isTop() == isKing){
				pc2.highlight(false);
				for(Cell cl2 : pc2.getvCells()){
					if(cl2 != null){
						if(cl2.getName().equals(location)){
							isCheck = true;
							pc2.getAttachedCell().setBorder(BorderFactory.createLineBorder(Color.red));
						}
					}
				}
			}
		}

		if(isCheck){
			Board.cellFinder(location).setBackground(new Color(160, 0, 0, 180));
			Board.cellFinder(location).setOpaque(true);
			Board.lpane.revalidate();
			Board.lpane.repaint();
		}

	}

	class menuActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == tfChat){
				Board.addMessage(Board.bot.getNick().substring(4) + ": " +tfChat.getText());
				if(Board.bot != null && Board.matchup!= null && !Board.tfChat.getText().isEmpty()){
					Board.bot.sendMessage(Board.matchup, tfChat.getText());
				}
				tfChat.setText("");
			}

			if(e.getSource() == miNewLocalGame){
				System.out.println("New game");
				if(Board.bot != null){
					Board.bot.disconnect();
				}
				resetBoard(Board.this);
				newGame();
			}

			if(e.getSource() == miConnect){
				connect();
			}

			if(e.getSource() == miResetBoard){
				Board.resetBoard(Board.this);
			}

			if(e.getSource() == miInvite){
				invite();
			}

			if(e.getSource() == miAbout){
				AboutGUI ab = new AboutGUI(about, spriteHorseT, spriteHorseZT);
				ab.setVisible(true);
			}
		}

	}
}
