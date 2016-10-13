package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import online.Bot;

import org.jibble.pircbot.User;

public class Inviter extends JDialog {

	JPanel panList = new JPanel();
	JScrollPane spanList = new JScrollPane(panList);
	
	JButton btRefresh = new JButton("Refresh");
	JButton btInvite = new JButton("Invite");
	
	Vector<PanName> vecPan = new Vector<PanName>();
	
	Bot bot = null;
	JPanel selected = null;
	String  currentNick = null;
	
	
	public Inviter(Board board, Bot bot){
		
		this.bot = bot;
		this.setSize(400, 600);
		this.setResizable(false);
		this.setTitle("Invite a friend");
		this.setLocationRelativeTo(board);
		this.setModal(true);
		initControles();
		
		
	}
	
	public void initControles(){
		JPanel zoneClient = (JPanel) this.getContentPane();
		zoneClient.setLayout(new BoxLayout(zoneClient, BoxLayout.Y_AXIS));
		JPanel panButtons = new JPanel();		
		panButtons.setPreferredSize(new Dimension(400, 40));
		panButtons.setMaximumSize(new Dimension(400, 40));
		panButtons.add(btInvite);
		panButtons.add(btRefresh);
		zoneClient.add(panButtons);
		zoneClient.add(spanList);
		panList.setLayout(new BoxLayout(panList, BoxLayout.Y_AXIS));
		
		btInvite.setAlignmentX(CENTER_ALIGNMENT);
		btRefresh.setAlignmentX(CENTER_ALIGNMENT);
		
		btInvite.addActionListener(new appActionListener());
		btRefresh.addActionListener(new appActionListener());
		btInvite.setEnabled(false);
		
		createList();
		
	}
	
	public void createList(){
		User[] listNicks = bot.getUsers(Board.bot.channel);
		Vector<String> vecUsers = new Vector<String>();
		for(int i = 0 ; i < listNicks.length ; i++){
			String nick = listNicks[i].getNick();
			if(!nick.equals(Board.bot.getNick())){
				if(nick.indexOf("sp0-") > -1 || nick.indexOf("sp1-") > -1){
					System.out.println(nick + " is good");
					vecUsers.add(nick);
				}
				else{
					System.out.println(nick + " isn't good");
				}
			}
		}
		
		panList.removeAll();;
		
		for(String nick : vecUsers){
			PanName pan = new PanName();
			JLabel labName = new JLabel(nick.substring(4));
			pan.setName(nick);
			pan.setLayout(new FlowLayout(FlowLayout.LEFT));
			pan.setBorder(BorderFactory.createEtchedBorder());
			pan.setPreferredSize(new Dimension(390, 40));
			pan.setMaximumSize(new Dimension(390, 40));
			
			int number = Integer.parseInt(nick.substring(2, 3));
			
			if(number == 0){
				labName.setForeground(new Color(0, 100, 0));
				labName.setText(labName.getText() + " - available");
			}
			else{
				labName.setForeground(new Color(100, 0, 0));
				labName.setText(labName.getText() + " - in game");
			}
			pan.add(labName);
			pan.addMouseListener(new appMouseListener());
			vecPan.add(pan);
			panList.add(pan);
		}
		
		spanList.revalidate();
		spanList.repaint();
	}
	
	class PanName extends JPanel{
		String name;
		
		public PanName(){
			super();
		}
		
		public void setName(String name){
			this.name = name;
		}
		
		public String getName(){
			return this.name;
		}
	}
	
	class appActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == btRefresh){
				createList();
			}
			
			if(e.getSource() == btInvite){
				String name = selected.getName();
				Board.inviting = name;
				Board.bot.sendMessage(name, "|$> I would like to play with you");
				
				Board.miInvite.setLabel("Cancel invitation (" + name + ")");
				Board.hasInvited = true;
				
				Board.setSelectionFrameVisible(true);
				Board.labPrinter.setText("Waiting for");
				Board.labPiecePrinter.setText(name.substring(4));
				
				Board.lpane.revalidate();
				Board.lpane.repaint();
				Inviter.this.dispose();
			}
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
			
			if((JPanel) e.getSource() != selected){
				for(JPanel pan : vecPan){
					pan.setBorder(BorderFactory.createEtchedBorder());
				}
				
				selected = (JPanel) e.getSource();
				selected.setBorder(BorderFactory.createLineBorder(Color.red));
				btInvite.setEnabled(true);
			}
			else{
				selected.setBorder(BorderFactory.createEtchedBorder());
				selected = null;
				btInvite.setEnabled(false);
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
