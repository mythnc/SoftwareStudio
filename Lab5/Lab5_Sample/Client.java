import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame{	
	public static void main(String[] args)
	{
		new Client();
	}
	
	private JTextField jtf= new JTextField();
	private JTextArea jta= new JTextArea();
	private DataInputStream fromServer;
	private DataOutputStream toServer;

	
	public Client(){
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		p.add(new JLabel("Enter radius"), BorderLayout.WEST);
		p.add(jtf, BorderLayout.CENTER);
		jtf.setHorizontalAlignment(JTextField.RIGHT);
		
		setLayout(new BorderLayout());
		add(p, BorderLayout.NORTH);
		add(new JScrollPane(jta), BorderLayout.CENTER);
		jtf.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				double radius = Double.parseDouble(jtf.getText().trim());
				try {
					toServer.writeDouble(radius);
					toServer.flush();
					double area = fromServer.readDouble();
					jta.append("Radius is " + radius + "\n");
					jta.append("Area recived from the server is " + area + "\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.err.println(e);
				}
			}});
		setTitle("Client");
		setSize(500,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		try{			
			Socket socket = new Socket("127.0.0.1", 8000);
			fromServer = new DataInputStream(socket.getInputStream());
			toServer = new DataOutputStream(socket.getOutputStream());
		}
		catch(IOException ex){
			System.err.println(ex);
		}
	}
}
