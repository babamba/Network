package com.bit2016.network.echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class EchoServer {
	private static final int PORT = 5004;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;

		try {
			// 1.서버소켓 생성.
			serverSocket = new ServerSocket();

			InetAddress inetAddress = InetAddress.getLocalHost(); // 지역호스트
																	// IP주소반환
			String hostAddress = inetAddress.getHostAddress(); // 호스트 IP주소 반환

			// 2. 바인딩 (소켓끼리 소켓주소(IP + HOST) 바인딩)
			serverSocket.bind(new InetSocketAddress(hostAddress, PORT));
			System.out.println("[서버] 연결대기중");

			// 3.accept(클라이언트 연결요청까지 대기)
			
			Socket socket = serverSocket.accept(); // 클라이언트 연결수락

			InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress(); // 연결된시스템에 대한 주소(IP+PORT)를 반환한다.
			InetAddress inetRemoteAddress = inetSocketAddress.getAddress(); // socket어드레스																	
		
			String remoteHostAddress = inetRemoteAddress.getHostAddress(); // host어드레스
																			 
			int remoteHostPort = inetSocketAddress.getPort();
			System.out.println("[서버] 연결됨 " + "클라이언트 from [" + remoteHostAddress + ":" + remoteHostPort + "]");

			try {
				// InputStream inputStream = socket.getInputStream();
				// OutputStream outputStream = socket.getOutputStream();
				// IO스트림
				BufferedReader bufread = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				PrintWriter printwrite = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"),
						true);

				while (true) {
					// 데이터읽기
					// String data = scan.nextLine();
					String data = bufread.readLine(); // 블락

					// 쓰기
					if (data == null) {
						System.out.println("[서버] 클라이언트 연결종료");
						break;
					}
					System.out.println("[서버] 데이터 수신 " + data);
					printwrite.println(data);

				}

			} catch (SocketException ex) {
				System.out.println("[서버] 클라이언트 접속종료");
			} finally {
				try {
					// 5.자원정리(소켓닫기)
					socket.close();

				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (serverSocket != null && serverSocket.isClosed() == false) {

				try {
					serverSocket.close();
					System.out.println("[서버] 서버가 종료됨");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}