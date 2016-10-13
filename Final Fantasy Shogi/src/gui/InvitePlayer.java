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

public class InvitePlayer extends JDialog{

	JButton btOK = new JButton("OK");
	JTextField tfName = new JTextField();
	
	public InvitePlayer(){
		this.setSize(200, 120);
		this.setTitle("FFS - invite");
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		initControles();
	}
	
	
	public void initControles(){
		JPanel zoneClient = (JPanel) this.getContentPane();
		zoneClient.setLayout(new BoxLayout(zoneClient, BoxLayout.Y_AXIS));
		JLabel labText = new JLabel("Invite player: ");
		labText.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		
		tfName.setAlignmentX(JTextField.CENTER_ALIGNMENT);
		tfName.setMaximumSize(new Dimension(200, 20));
		
		zoneClient.add(Box.createRigidArea(new Dimension(20, 10)));
		zoneClient.add(labText);
		zoneClient.add(Box.createRigidArea(new Dimension(20, 5)));
		zoneClient.add(tfName);
		zoneClient.add(Box.createRigidArea(new Dimension(20, 5)));
		
		btOK.setAlignmentX(JButton.CENTER_ALIGNMENT);
		zoneClient.add(btOK);
		
		btOK.addActionListener(new appActionListener());
		tfName.addActionListener(new appActionListener());
	}
	
	
	class appActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == btOK && !tfName.getText().isEmpty()){
				
				String name = tfName.getText();
				Board.inviting = name;
				Board.bot.sendMessage(name, "|$> I would like to play with you");
				
				Board.miInvite.setLabel("Cancel invitation (" + name + ")");
				Board.hasInvited = true;
				
				Board.setSelectionFrameVisible(true);
				Board.labPrinter.setText("Waiting for");
				Board.labPiecePrinter.setText(name);
				
				Board.lpane.revalidate();
				Board.lpane.repaint();
				InvitePlayer.this.dispose();
			}
			
			if(e.getSource() == tfName){
				btOK.doClick();
			}
			
		}
		
	}
	
}
