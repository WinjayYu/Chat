/*使用多线程实现多个客户端连接服务端
 * 
 */

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	

	Socket s = null;
	ServerSocket so = null;
	boolean started = false;
	List<Client> clients = new ArrayList<Client>();
	
	public static void main(String[] args) {
		new ChatServer().start();
	}
	
	public void start() {
		try{
			started = true;
			so = new ServerSocket(8888);			
		}catch(BindException e3) {
			e3.printStackTrace();
		}catch(IOException e4){
			e4.printStackTrace();
		}
		
		while(started){
			try {
				s = so.accept();
				Client c = new Client(s);
				System.out.println("a client connected");
				new Thread(c).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	class Client implements Runnable {
		
		private Socket s = null;
		private DataInputStream dis = null;
		private DataOutputStream dos = null;
		private boolean connected = false;
		
		Client(Socket s) {
			this.s = s;
			try {
				dis = new DataInputStream(s.getInputStream());
				dos = new DataOutputStream(s.getOutputStream());
				connected = true;
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
		
		public void send(String str){
			try {
				dos.writeUTF(str);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void run() {
			try{			
				while(connected) {
					String str = dis.readUTF();
					System.out.println(str);	
	                for(int i=0; i<clients.size(); i++){
	                	Client c = clients.get(i);
	                	c.send(str);
	                }               
				}
			}catch(IOException e) {
				//e.printStackTrace();
				System.out.println("client closed");
			}finally {
	            try {
	            	if(null != dis) dis.close();
					if(null != s) s.close();
					if(null != dos) dos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		

	}
}
