package pieces;

import gui.Board;
import gui.Cell;

import java.awt.Color;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

public class Lancer extends Piece{

	String type = "";

	ImageIcon LancerT;
	ImageIcon LancerB;
	ImageIcon LancerZT;
	ImageIcon LancerZB;
	ImageIcon LancerA;

	public Lancer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lancer(Lancer Lancer, Boolean isTop, Cell attachedCell){
		this.LancerA = Lancer.LancerA;
		this.LancerT = Lancer.LancerT;
		this.LancerB = Lancer.LancerB;
		this.LancerZT = Lancer.LancerZT;
		this.LancerZB = Lancer.LancerZB;

		this.isTop = isTop;
		this.attachedCell = attachedCell;
		this.setType("Lancer");

		setSprite();

	}

	public ImageIcon getLancerT() {
		return LancerT;
	}


	public void setLancerT(ImageIcon LancerT) {
		this.LancerT = LancerT;
	}


	public ImageIcon getLancerB() {
		return LancerB;
	}


	public void setLancerB(ImageIcon LancerB) {
		this.LancerB = LancerB;
	}


	public ImageIcon getLancerZT() {
		return LancerZT;
	}


	public void setLancerZT(ImageIcon LancerZT) {
		this.LancerZT = LancerZT;
	}


	public ImageIcon getLancerZB() {
		return LancerZB;
	}


	public void setLancerZB(ImageIcon LancerZB) {
		this.LancerZB = LancerZB;
	}


	public ImageIcon getLancerA() {
		return LancerA;
	}


	public void setLancerA(ImageIcon LancerA) {
		this.LancerA = LancerA;
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
				this.setIcon(LancerZT);
			}
			else{
				this.setIcon(LancerZB);
			}
		}
		else{
			if(isTop){
				this.setIcon(LancerT);
			}
			else{
				this.setIcon(LancerB);
			}
		}

	}

	public void highlight(boolean paintCells){

		int[] coords = this.getAttachedCellCoordinates();
		int x = coords[0];
		int y = coords[1];
		this.vCells = new Vector<Cell>();

		if(this.isPromoted){	

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

			int varX = x;
			boolean end = false;

			while(!end){

				if(this.isTop){//top
					varX++;
				}
				else{
					varX--;
				}
				Cell cell =  Board.cellFinder((varX) + "-" + y );
				if(cell != null){
					if(Board.isCellOccupied(cell)){//met another piece
						vCells.addElement(cell);
						end = true;
						break;
					}
					else{
						vCells.addElement(cell);
					}
				}
				else{
					end = true;
					break;
				}
			}

			paintCells(paintCells);


		}

	}

}
