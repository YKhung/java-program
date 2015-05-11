package client;
import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class SimpleChatClient {
	JTextArea incoming;
	JTextField outgoing;
	BufferedReader reader;
	PrintWriter writer;
	Socket sock;
	static String clientName;

	public static void main(String[] args) {
		SimpleChatClient client = new SimpleChatClient();
		clientName = JOptionPane.showInputDialog("Please input the  name :");
		client.go();
	}

	public void go() {
		// build GUI
		JFrame frame = new JFrame(clientName + "'");
		JPanel mainPanel = new JPanel();
		incoming = new JTextArea(15,20);  
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		incoming.setEditable(false);

		JScrollPane qScroller = new JScrollPane(incoming);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		outgoing = new JTextField(20);

		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new SendButtonListener());

		mainPanel.add(qScroller);
		mainPanel.add(outgoing);
		mainPanel.add(sendButton);

		setUpNetworking();
		Thread readerThread = new Thread(new IncomingReader());
		readerThread.start();

		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(400,500);
		frame.setVisible(true);

	} // close go

	private void setUpNetworking() { 
		try {
			sock = new Socket("127.0.0.1", 5000);
			InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
			reader = new BufferedReader(streamReader);

			writer = new PrintWriter(sock.getOutputStream());

			System.out.println("networking established");
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	} // close setUpNetworking  

	public class SendButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			try {
				writer.println(clientName+" says:\n"+outgoing.getText());
				writer.flush();
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			outgoing.setText("");
			outgoing.requestFocus();
		}
	}  // close SendButtonListener inner class

	public class IncomingReader implements Runnable {
		public void run() {
			String message = null;            
			try {
				while ((message = reader.readLine()) != null) {                       
					System.out.println("read " + message);
					incoming.append(message + "\n");
				} // close while
			} catch(Exception ex) {ex.printStackTrace();}
		} // close run
	} // close inner class    
}