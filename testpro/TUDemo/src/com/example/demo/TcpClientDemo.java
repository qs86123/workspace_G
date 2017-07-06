package com.example.demo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class TcpClientDemo extends JFrame {

	Socket socket;
	Container container;
	JTextArea ta = new JTextArea();
	JTextField tf = new JTextField();
	PrintWriter pw;

	public TcpClientDemo(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container = this.getContentPane();
		final JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setBorder(new BevelBorder(BevelBorder.RAISED));
		getContentPane().add(jScrollPane, BorderLayout.CENTER);
		jScrollPane.setViewportView(ta);
		container.add(tf, "South");
		tf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pw.println(tf.getText());//�����ݷ��ͳ�ȥ��û����䣬��Ϣ��������ȥ
				ta.append("client:"+tf.getText() + "\n");
				//ta.setSelectionEnd(ta.getText().length());
				tf.setText("");
			}
		});
	}

	private void connect(){
		ta.append("try to connect,please wait\n");
		InetAddress ipAddress;
		try {
			ipAddress = InetAddress.getLocalHost();
			String ip = ipAddress.getHostAddress();
			socket = new Socket(ip, 8998);
			pw = new PrintWriter(socket.getOutputStream(), true); 
			ta.append("connection finish\n");
		} catch (UnknownHostException e) {
			System.out.println("error");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("error");
			e.printStackTrace();
		}
		
	}

	private void getme() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		while (true) {
			ta.append("server:" + br.readLine() + "\n");

		}
	}

	public static void main(String[] args) throws IOException {
		final TcpClientDemo tcd = new TcpClientDemo("client");
		tcd.setSize(200, 200);
		tcd.setVisible(true);
		tcd.connect();
		tcd.getme();
	}

}
