/*
 *服务端向客户端发送数据 
 *由于自己写的bug太多，最后copy老师的代码才调试好，惭愧惭愧！
 */

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {

	//Socket s = null;
	ServerSocket so = null;
	boolean started = false;
	List<Client> clients = new ArrayList<Client>();
	
	public static void main(String[] args) {
		new ChatServer().start();
	}
	
	public void start() {
		try{			
			so = new ServerSocket(8888);
			started = true;
		}catch(BindException e3) {
			//e3.printStackTrace();
			System.exit(0);
		}catch(IOException e4){
			e4.printStackTrace();
		}
		
		try {
		    while(started){
		    	Socket s = so.accept();
		       // s = so.accept();
				Client c = new Client(s);
				System.out.println("a client connected");
				new Thread(c).start();
				clients.add(c);
			}
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}finally {
			try {
				so.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	class Client implements Runnable {
		
		private Socket s;
		private DataInputStream dis = null;
		private DataOutputStream dos = null;
		private boolean connected = false;
		
		public Client(Socket s) {
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
			}catch(EOFException e) {
				System.out.println("client closed");
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
	            try {
	            	if(dis != null) dis.close();
					if(dos != null) dos.close();
					if(s != null) s.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		

	}
}
