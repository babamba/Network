package com.bit2016.network.echo;

import java.awt.SecondaryLoop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

import org.omg.Messaging.SyncScopeHelper;

public class Echoclient {
	private static final String SERVER_IP ="192.168.1.13";
	private static final int SERVER_PORT = 5004;
	
	public static void main(String[] args) {
		Socket socket = null;
		
		
		//소켓생성
	
		try {
			socket = new Socket();
//			2.서버연결
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			System.out.println("[클라이언트] 클라이언트가 서버에 연결되었습니다.");
			
//			3.IOstream받아오기
			BufferedReader br = 
					new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			PrintWriter pw =
					new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"));
							
//			4.쓰기
		try{
			while(true){
							
			Scanner scanner = new Scanner(System.in);
			System.out.println(">>");
			String inputData = scanner.nextLine();
		
			if(inputData == null){
				
			}
				}
//			data = new String(buffer, 0, readByteCount, "UTF-8");
//			System.out.println("[클라이언트 >> ]" + data);
		} catch (IOException e) {
			e.printStackTrace();
		}

	

}