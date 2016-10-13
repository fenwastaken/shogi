package pieces;

import gui.Board;
import gui.Cell;

import java.awt.Color;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class King extends Piece{

	String type = "";

	ImageIcon kingA;
	ImageIcon kingT;
	ImageIcon kingB;

	public King() {
		super();
		// TODO Auto-generated constructor stub
	}

	public King(King king, Boolean isTop, Cell attachedCell){
		
		this.kingT = king.kingA;
		this.kingT = king.kingT;
		this.kingB = king.kingB;

		this.isTop = isTop;
		this.attachedCell = attachedCell;
		this.setType("King");
		
		setSprite();

	}
	
	public ImageIcon getKingA() {
		return kingA;
	}

	public void setKingA(ImageIcon kingA) {
		this.kingA = kingA;
	}

	public ImageIcon getKingT() {
		return kingT;
	}


	public void setkingT(ImageIcon kingT) {
		this.kingT = kingT;
	}


	public ImageIcon getkingB() {
		return kingB;
	}


	public void setkingB(ImageIcon kingB) {
		this.kingB = kingB;
	}

	public String getType() {
		return this.type;
	}


	public void setType(String type) {
		this.type = type;
	}

	public void setSprite(){

		if(isTop){
			this.setIcon(kingT);
		}
		else{
			this.setIcon(kingB);
		}
	}

	public void highlight(boolean paintCells){
		
		int[] coords = this.getAttachedCellCoordinates();
		int x = coords[0];
		int y = coords[1];
		
		this.vCells = new Vector<Cell>();
		

				vCells.addElement(Board.cellFinder((x - 1) + "-" + (y - 1))); // top left
				vCells.addElement(Board.cellFinder((x - 1) + "-" + y)); // top
				vCells.addElement(Board.cellFinder((x - 1) + "-" + (y + 1))); // top right
				vCells.addElement(Board.cellFinder(x + "-" + (y - 1))); // left
				vCells.addElement(Board.cellFinder(x + "-" + (y + 1))); // right
				vCells.addElement(Board.cellFinder((x + 1) + "-" + y)); // bottom
				vCells.addElement(Board.cellFinder((x + 1) + "-" + (y - 1))); // bottom left
				vCells.addElement(Board.cellFinder((x + 1) + "-" + (y + 1))); // bottom right
		
				paintCells(paintCells);
		
	}
	
}


