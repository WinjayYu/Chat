import java.awt.*;
import java.awt.event.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {

	public static void main(String[] args) {
		new MyFrame("Chat");

	}

}

class MyFrame extends Frame {
	Socket s = null;
	TextField tf = new TextField();
	TextArea ta = new TextArea();
	DataOutputStream dos = null;

	
	MyFrame(String s){
		super(s);
		setLocation(300,300);
		setSize(400,400);
		//this.setBackground(Color.BLUE);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				disconnct();
				System.exit(0);
			}
			
		});
		this.add(tf,BorderLayout.SOUTH);
		this.add( ta,BorderLayout.NORTH);
		pack();
		tf.addActionListener(new TFListener());
		this.setVisible(true);
		connect();
	}
	
	public void disconnct(){
		try {
			dos.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void connect() {
		try {
			s = new Socket("127.0.0.1",8888);
			dos = new DataOutputStream(s.getOutputStream());
System.out.println("connect!");		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private class TFListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String str = tf.getText().trim();
			ta.setText(str);
			tf.setText("");
			
			try {
				dos.writeUTF(str);
				dos.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	        
			
		}
		 
	}
}


