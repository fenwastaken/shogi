package gui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.xml.bind.util.ValidationEventCollector;

import pieces.Piece;

public class Holder extends JLabel{

	String name;
	int counter = 0;
	JLabel labCounter;
	JLabel labSprite;
	ImageIcon sprite;


	public Holder(String name, ImageIcon sprite) {
		super();
		this.name = name;
		this.labCounter = new JLabel();
		this.labCounter.setForeground(Color.WHITE);
		this.labCounter.setHorizontalAlignment(JLabel.CENTER);
		this.labSprite = new JLabel();
		this.sprite = sprite;
		this.addMouseListener(new appMouseListener());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public JLabel getLabCounter() {
		return labCounter;
	}

	public void setLabCounter(JLabel labCounter) {
		this.labCounter = labCounter;
	}

	public int getCounter(){
		return this.counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public JLabel getLabSprite() {
		return labSprite;
	}

	public void setLabSprite(JLabel labSprite) {
		this.labSprite = labSprite;
	}

	public ImageIcon getSprite() {
		return sprite;
	}

	public void setSprite(ImageIcon sprite) {
		this.sprite = sprite;
	}

	//Methods-------------------------------------------

	public void increaseCounter(){
		this.counter++;
		updateLabCounter();
	}

	public void decreaseCounter(){
		this.counter--;
		updateLabCounter();
	}

	public void updateLabCounter(){
		if(this.counter < 1){
			this.labCounter.setText("");
			this.labSprite.setIcon(null);
		}
		else{
			this.labCounter.setText(this.counter + "");
			this.labSprite.setIcon(this.sprite);
		}
	}

	public void addToLpane(int z){
		Board.lpane.add(this, z);
		Board.lpane.add( labSprite, z);
		Board.lpane.add(labCounter, z);
	}

	public void spriteBounder(){
		int xPos = this.getX();
		int yPos = this.getY();

		if(this.getName().substring(2).equals("Knight")){
			labSprite.setBounds(xPos, yPos-10, 70,  82);
		}
		else if((this.getName().substring(2).equals("Gold"))||(this.getName().substring(2).equals("Bishop"))||(this.getName().substring(2).equals("Rook"))){
			labSprite.setBounds(xPos + 5, yPos-10, 70,  82);
		}
		else{
			labSprite.setBounds(xPos+10, yPos-10, 70,  82);
		}

		labCounter.setBounds(xPos+20, yPos+60, 20, 20);
	}

	public Boolean isThisHolderTop(){
		Boolean isTop = false;
		String strIsTop = this.getName().substring(0, 1);
		if(strIsTop.equals("T")){
			isTop = true;
		}
		return isTop;
	}

	public void displayPawnLines(){
		boolean isTop = false;
		if(this.getName().substring(0, 1).equals("T")){
			isTop = true;
		}
		
		Vector<Cell> vCl = new Vector<Cell>();
		
		for(Piece pc : Board.vPieces){
			if(pc.getType().equals("Pawn")){
				if(pc.isTop() == isTop){
					for(Cell cl : Board.vCells){
						int cellY = Integer.parseInt(cl.getName().substring(2, 3));
						int pieceY = Integer.parseInt(pc.getAttachedCell().getName().substring(2, 3));
						if((cellY == pieceY) || (Board.isCellOccupied(cl))){
							cl.setBackground(new Color(100, 0, 0, 100));
							cl.setOpaque(true);
							vCl.add(cl);
						}
					}
				}
			}
		}
		
		for(Cell cl : Board.vCells){
			if(!vCl.contains(cl)){
				cl.setBackground(new Color(0, 0, 100, 100));
				cl.setOpaque(true);
			}
		}
		
		Board.lpane.revalidate();
		Board.lpane.repaint();

	}
	
	public void displayPawnLancerMaxRows(){
		boolean isTop = false;
		if(this.getName().substring(0, 1).equals("T")){
			isTop = true;
		}

		if(!isTop){
			for(Cell cl : Board.vCells){
				int cellY = Integer.parseInt(cl.getName().substring(0, 1));
				if(cellY < 2){
					cl.setBackground(new Color(100, 0, 0, 100));
					cl.setOpaque(true);
				}
			}
		}
		else{
			for(Cell cl : Board.vCells){
				int cellY = Integer.parseInt(cl.getName().substring(0, 1));
				if(cellY > 8){
					cl.setBackground(new Color(100, 0, 0, 100));
					cl.setOpaque(true);
				}
			}
		}
		Board.lpane.revalidate();
		Board.lpane.repaint();

	}
	
	public void displayKnightMaxRows(){
		boolean isTop = false;
		if(this.getName().substring(0, 1).equals("T")){
			isTop = true;
		}

		if(!isTop){
			for(Cell cl : Board.vCells){
				int cellY = Integer.parseInt(cl.getName().substring(0, 1));
				if(cellY < 3){
					cl.setBackground(new Color(100, 0, 0, 100));
					cl.setOpaque(true);
				}
			}
		}
		else{
			for(Cell cl : Board.vCells){
				int cellY = Integer.parseInt(cl.getName().substring(0, 1));
				if(cellY > 7){
					cl.setBackground(new Color(100, 0, 0, 100));
					cl.setOpaque(true);
				}
			}
		}
		Board.lpane.revalidate();
		Board.lpane.repaint();

	}
	
	public void paintAllCellsBlue(){
		for(Cell cl : Board.vCells){
			cl.setBackground(new Color(0, 0, 100, 100));
			cl.setOpaque(true);
		}
		Board.lpane.revalidate();
		Board.lpane.repaint();
	}
	
	public void paintOccupiedCellsRed(){
		for(Cell cl : Board.vCells){
			for(Piece pc : Board.vPieces){
				if(pc.getAttachedCell() == cl){
					cl.setBackground(new Color(100, 0, 0, 100));
					cl.setOpaque(true);
				}
			}
		}
		Board.lpane.revalidate();
		Board.lpane.repaint();
	}

	
	public void deselectHolders(){

		if(Board.selectedHolder != null){
			for(Holder hldr : Board.vHolders){
				Board.selectedHolder.setBorder(null);
			}
			Board.selectedHolder = null;
		}
		
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
			if(e.getButton() == MouseEvent.BUTTON1 && Holder.this.getCounter() > 0 && !Board.gameOver && !Board.promoting && Board.isMyTurn){

				if(isThisHolderTop() == Board.jadeTurn){
					Board.vCells.firstElement().deselectCells();
					if(Board.selectedHolder != null){// holder selected already
						if(Board.selectedHolder != Holder.this){//reselect other holder
							Board.selectedHolder.setBorder(BorderFactory.createLineBorder(null));
							Board.selectedHolder = Holder.this;
							Board.selectedHolder.setBorder(BorderFactory.createLineBorder(Color.green));
						}
						else{//deselect this holder
							deselectHolders();
						}
					}
					else{//select this holder
						Board.selectedHolder = Holder.this;
						Board.selectedHolder.setBorder(BorderFactory.createLineBorder(Color.green));
					}
					
					if(Holder.this == Board.selectedHolder){
						paintAllCellsBlue();
						paintOccupiedCellsRed();
					}
					
					if(Holder.this.getName().substring(2).equals("Pawn") && Holder.this == Board.selectedHolder){	
						displayPawnLines();
					}
					if((Holder.this.getName().substring(2).equals("Pawn") || Holder.this.getName().substring(2).equals("Lancer")) && Holder.this == Board.selectedHolder){
						displayPawnLancerMaxRows();
					}
					if(Holder.this.getName().substring(2).equals("Knight") && Holder.this == Board.selectedHolder){
						displayKnightMaxRows();
					}
				}
				
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

}
