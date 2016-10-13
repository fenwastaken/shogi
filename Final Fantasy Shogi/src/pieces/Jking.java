package pieces;

import gui.Board;
import gui.Cell;

import java.awt.Color;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class Jking extends Piece{

	String type = "";

	ImageIcon jkingA;
	ImageIcon jkingT;
	ImageIcon jkingB;

	public Jking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Jking(Jking jking, Boolean isTop, Cell attachedCell){
		this.jkingA = jking.jkingA;
		this.jkingT = jking.jkingT;
		this.jkingB = jking.jkingB;

		this.isTop = isTop;
		this.attachedCell = attachedCell;
		this.setType("Jade");
		
		setSprite();

	}

	
	public ImageIcon getJkingA() {
		return jkingA;
	}

	public void setJkingA(ImageIcon jkingA) {
		this.jkingA = jkingA;
	}

	public ImageIcon getjkingT() {
		return jkingT;
	}


	public void setjkingT(ImageIcon jkingT) {
		this.jkingT = jkingT;
	}


	public ImageIcon getjkingB() {
		return jkingB;
	}


	public void setjkingB(ImageIcon jkingB) {
		this.jkingB = jkingB;
	}

	public String getType() {
		return this.type;
	}


	public void setType(String type) {
		this.type = type;
	}

	public void setSprite(){

		if(isTop){
			this.setIcon(jkingT);
		}
		else{
			this.setIcon(jkingB);
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


