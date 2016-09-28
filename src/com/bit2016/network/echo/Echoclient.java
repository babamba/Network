package com.bit2016.network.echo;

import java.awt.SecondaryLoop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

import org.omg.Messaging.SyncScopeHelper;

public class Echoclient { // 
	private static final String SERVER_IP = "192.168.1.13";
	private static final int SERVER_PORT = 5004;

	public static void main(String[] args) {
		Socket socket = null;
		Scanner scan = null;
		// 소켓생성

		try {
			socket = new Socket();
			// 2.서버연결
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			System.out.println("[클라이언트] 클라이언트가 서버에 연결되었습니다.");

			// 3.IOstream받아오기
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8")); //서버에서 온거 읽기 
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true); //쓰고 서버로 전송

			// 4.쓰기

			while (true) {
				//입력받기
				Scanner scanner = new Scanner(System.in);
				System.out.print(">>");
				String inputData = scanner.nextLine();

				if (inputData.equals("exit")) {
					break;
				}
				//송신
				pw.println(inputData); 
				
				//수신
				String data = br.readLine(); 
				if (data == null) {	
					break;
				}
				System.out.println("=>" + data);
				
			}
		} catch (SocketException ex) { 
			System.out.println("예외하지 않은 종료");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (socket != null & socket.isClosed() == false) {
					socket.close();
				}
			}
				catch (ConnectException e) {
					System.out.println("연결되지 않음");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				System.out.println("[클라이언트] 클라이언트 접속종료");
			}
		}
		
	}
}