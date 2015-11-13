import java.awt.*;
import java.awt.event.*;


public class Client {

	public static void main(String[] args) {
		new MyFrame("Chat");

	}

}

class MyFrame extends Frame {
	TextField tf = new TextField();
	TextArea ta = new TextArea();
	
	MyFrame(String s){
		super(s);
		setLocation(300,300);
		setSize(400,400);
		//this.setBackground(Color.BLUE);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		this.add(tf,BorderLayout.SOUTH);
		this.add( ta,BorderLayout.NORTH);
		pack();
		this.setVisible(true);
	}
}

/*class MyWindowMonitor extends WindowAdapter {
	public void windowClosing(WindowEvent e){
		//setVisible(false);
		System.exit(0);
	}
}
*/