import java.io.*;
import java.net.*;

public class ChatServer {

	public static void main(String[] args) {
		boolean bStart = false;
		DataInputStream dis = null;
		Socket s = null;
		ServerSocket so = null;
		
		try{
			so = new ServerSocket(8888);
		}catch(BindException e3) {
			System.out.println("端口被占用!");
			e3.printStackTrace();
		}catch(IOException e4){
			e4.printStackTrace();
		}
		try{
			bStart = true;
			while(bStart) {
				s = so.accept();
				boolean bConnected = false;
System.out.println("a client connected");
                bConnected = true;
                dis = new DataInputStream(s.getInputStream());
                while(bConnected){
	                System.out.println(dis.readUTF());
                }
			}
		}catch(IOException e) {
			//e.printStackTrace();
			System.out.println("clint closed");
		}finally {
            try {
            	if(null != dis) dis.close();
				if(null != s) s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
