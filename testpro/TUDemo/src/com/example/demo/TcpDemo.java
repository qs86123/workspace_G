package com.example.demo;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class TcpDemo extends JFrame {

	private Socket socket;
	private ServerSocket serverSocket;
	private BufferedReader br;
	PrintWriter pw;
	Container container;
	JTextArea ta = new JTextArea();
	JTextField tf = new JTextField();

	public TcpDemo(String title) {
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
				pw.println(tf.getText());// �����ݷ��ͳ�ȥ��û����䣬��Ϣ��������ȥ
				ta.append("server:" + tf.getText() + "\n");
				ta.setSelectionEnd(ta.getText().length());
				tf.setText("");
			}
		});
	}

	void getserver() throws IOException {
		serverSocket = new ServerSocket(8998);
		System.out.println("create soket finish...");

		System.out.println("wait for connection...");
		socket = serverSocket.accept();
		// ֻ��ҪΪ�䴴��һ���������ͻ����������
		pw = new PrintWriter(socket.getOutputStream(), true);
		// ����һ�����������������տͻ��˵����루IO���У�����
		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		getclientMessage();
	}

	private void getclientMessage() {

		try {
			while (true) {
				ta.append("client:" + br.readLine() + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			br.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		TcpDemo td = new TcpDemo("server");
		td.setSize(500, 500);
		td.setVisible(true);
		td.getserver();
	}

}
