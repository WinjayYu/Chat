/*
 * 显示文本区（ta）接收编辑文本区（tf）的数据
 */

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
		tf.addActionListener(new TFListener());
		this.setVisible(true);
	}
	
	private class TFListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String s = tf.getText().trim();
			ta.setText(s);
			tf.setText("");
			
		}
		 
	}
}


