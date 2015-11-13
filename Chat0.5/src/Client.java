/*
 *接收服务端的数据 
 *由于自己写的bug太多，最后copy老师的代码才调试好，惭愧惭愧！
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;


public class Client extends Frame {
	
        Socket s = null;
		TextField tf = new TextField();
		TextArea ta = new TextArea();
		DataOutputStream dos = null;
		DataInputStream dis = null;
		boolean bConnect = false;
		Thread tRecv = new Thread(new Receive());

		public static void main(String[] args) {
			new Client().launchFrame();
        }

		
		public void launchFrame() {			
			setLocation(300,300);
			this.setSize(400,400);
			add(tf,BorderLayout.SOUTH);
			add( ta,BorderLayout.NORTH);
			pack();
			//this.setBackground(Color.BLUE);
			this.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e) {
					disconnct();
					System.exit(0);
				}
				
			});	
			tf.addActionListener(new TFListener());
			this.setVisible(true);
			connect();		
			tRecv.start();
		}
		
		public void disconnct(){
			try {
				dos.close();
				dis.close();
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
				dis = new DataInputStream(s.getInputStream());
				bConnect = true;
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
				//ta.setText(str);
				tf.setText("");
				
				try {
					dos.writeUTF(str);
					dos.flush();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		        
				
			}
			 
		}

		private class Receive implements Runnable {

			public void run() {
				try {
					while(bConnect) {
						String str = dis.readUTF();
						ta.setText(ta.getText() + str + '\n');
					}
				}catch (SocketException e) {
					System.out.println("退出了，bye!");
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}





