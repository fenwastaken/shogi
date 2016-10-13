package pieces;

import gui.Board;
import gui.Cell;

import java.awt.Color;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class Knight extends Piece{

	String type = "";
	
	ImageIcon HorseT;
	ImageIcon HorseB;
	ImageIcon HorseZT;
	ImageIcon HorseZB;
	ImageIcon HorseA;
	
	public Knight() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Knight(Knight Horse, Boolean isTop, Cell attachedCell){
		this.HorseA = Horse.HorseA;
		this.HorseT = Horse.HorseT;
		this.HorseB = Horse.HorseB;
		this.HorseZT = Horse.HorseZT;
		this.HorseZB = Horse.HorseZB;
		
		this.isTop = isTop;
		this.attachedCell = attachedCell;
		this.setType("Knight");
		
		setSprite();
		
	}

	public ImageIcon getHorseT() {
		return HorseT;
	}


	public void setHorseT(ImageIcon HorseT) {
		this.HorseT = HorseT;
	}


	public ImageIcon getHorseB() {
		return HorseB;
	}


	public void setHorseB(ImageIcon HorseB) {
		this.HorseB = HorseB;
	}


	public ImageIcon getHorseZT() {
		return HorseZT;
	}


	public void setHorseZT(ImageIcon HorseZT) {
		this.HorseZT = HorseZT;
	}


	public ImageIcon getHorseZB() {
		return HorseZB;
	}


	public void setHorseZB(ImageIcon HorseZB) {
		this.HorseZB = HorseZB;
	}


	public ImageIcon getHorseA() {
		return HorseA;
	}


	public void setHorseA(ImageIcon HorseA) {
		this.HorseA = HorseA;
	}
	
	public String getType() {
		return this.type;
	}


	public void setType(String type) {
		this.type = type;
	}

	public void setSprite(){
		
		if(this.isPromoted){
			if(isTop){
				this.setIcon(HorseZT);
			}
			else{
				this.setIcon(HorseZB);
			}
		}
		else{
			if(isTop){
				this.setIcon(HorseT);
			}
			else{
				this.setIcon(HorseB);
			}
		}
		
	}
	
	public void highlight(boolean paintCells){
		
		int[] coords = this.getAttachedCellCoordinates();
		int x = coords[0];
		int y = coords[1];

		if(this.isPromoted){

			this.vCells = new Vector<Cell>();
			if(this.isTop){
				vCells.addElement(Board.cellFinder((x + 1) + "-" + (y - 1))); // top left
				vCells.addElement(Board.cellFinder((x + 1) + "-" + y)); // top
				vCells.addElement(Board.cellFinder((x + 1) + "-" + (y + 1))); // top right
				vCells.addElement(Board.cellFinder(x + "-" + (y - 1))); // left
				vCells.addElement(Board.cellFinder(x + "-" + (y + 1))); // right
				vCells.addElement(Board.cellFinder((x - 1) + "-" + y)); // bottom
			}
			else{
				vCells.addElement(Board.cellFinder((x - 1) + "-" + (y - 1))); // top left
				vCells.addElement(Board.cellFinder((x - 1) + "-" + y)); // top
				vCells.addElement(Board.cellFinder((x - 1) + "-" + (y + 1))); // top right
				vCells.addElement(Board.cellFinder(x + "-" + (y - 1))); // left
				vCells.addElement(Board.cellFinder(x + "-" + (y + 1))); // right
				vCells.addElement(Board.cellFinder((x + 1) + "-" + y)); // bottom
			}
			
			paintCells(paintCells);
			
			
		}
		else{

			this.vCells = new Vector<Cell>();

			if(this.isTop){
				
				this.vCells.add(Board.cellFinder((x + 2) + "-" + (y + 1)));
				this.vCells.add(Board.cellFinder((x + 2) + "-" + (y - 1)));
			}
			else{
				this.vCells.add(Board.cellFinder((x - 2) + "-" + (y - 1)));
				this.vCells.add(Board.cellFinder((x - 2) + "-" + (y + 1)));
			}
			
			paintCells(paintCells);
			
		}

		
	}
}
