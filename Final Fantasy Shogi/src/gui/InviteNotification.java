package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import online.Bot;

public class InviteNotification extends JDialog{

	JButton btOK = new JButton("Accept");
	JButton btNO = new JButton("Decline");
	String inviter = null;
	Board board = null;
	
	public InviteNotification(String inviter, Board board){
		this.setSize(250, 115);
		this.setTitle("FFS - Challenge!");
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setResizable(true);
		this.inviter = inviter;
		this.board = board;
		
		initControles();
	}
	
	
	public void initControles(){
		JPanel zoneClient = (JPanel) this.getContentPane();
		zoneClient.setLayout(new BoxLayout(zoneClient, BoxLayout.Y_AXIS));
		JLabel labText = new JLabel("You've been invited by " + inviter.substring(4));
		labText.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		JPanel panButtons = new JPanel();
		panButtons.setPreferredSize(new Dimension(150, 35));
		panButtons.add(btOK);
		panButtons.add(btNO);
		zoneClient.add(Box.createRigidArea(new Dimension(20, 10)));
		zoneClient.add(labText);
		zoneClient.add(Box.createRigidArea(new Dimension(20, 5)));
		zoneClient.add(panButtons);
		zoneClient.add(Box.createRigidArea(new Dimension(20, 5)));
		
		btOK.setAlignmentX(JButton.CENTER_ALIGNMENT);
		
		btOK.addActionListener(new appActionListener());
		btNO.addActionListener(new appActionListener());
	}
	
	
	class appActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == btOK){
				//makes you the passive Jade player
				
				Board.resetBoard(InviteNotification.this.board);
				Board.setChatVisible(true);
				
				Board.miInvite.setEnabled(false);
				Board.isInvited = true;
				
				Board.bot.sendMessage(inviter, "|$> I accept your challenge");
				Board.matchup = inviter;
				Board.isJade = true;
				Board.gameOver = false;
				Board.isMyTurn = false;
				
				Board.setSelectionFrameVisible(true);
				Board.labPrinter.setText("You're Jade");
				Board.labPiecePrinter.setText(inviter + " is King");
				
				Board.vCells.firstElement().paintPieces();
				
				//room stuff
				Board.bot.changeNick("sp1-" + Board.bot.botName.substring(4));
				Board.matchup = "sp1-" + Board.matchup.substring(4);
				
				InviteNotification.this.dispose();
			}
			
			if(e.getSource() == btNO){
				Board.bot.sendMessage(inviter, "|$> I refuse your challenge");
				InviteNotification.this.dispose();
			}
		}
		
	}
	
}
