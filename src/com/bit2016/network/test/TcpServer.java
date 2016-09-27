package com.bit2016.network.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import javax.sql.rowset.serial.SerialArray;

public class TcpServer {
	private static final int PORT = 5003; 

	public static void main(String[] args) {
		
		// 대표 포트를 지니는 ServerSocket 객체 선언
		ServerSocket serverSocket = null;
		
/*		byte[] getAddress() : IP주소를 byte배열로 반환한다.
		static InetAddress[] getAllByName(String host) : 도메인명에 지정된 모든 호스트의 IP주소를 배열에 담아 반환한다.
		하나의 도메인명에 여러 IP주소가 맵핑될 수도 있고 또 그 반대의 경우도 가능하기 때문에 전자의 경우 getAllByName()을 통해 모든 IP주소를 얻을 수 있다.
		static InetAddress getByAddress(byte[] addr) : byte배열을 통해 IP주소를 얻는다.
		static InetAddress getByName(String host) : 도메인명을 통해 IP주소를 얻는다.
		String getCanonicalHostName() : FQDN(fully qualified domain name)을 반환한다.
		String getHostAddress() : 호스트의 IP주소를 반환한다.
		String getHostName() : 호스트의 이름을 반환한다.
		static InetAddress getLocalHost() : 지역호스트의 IP주소를 반환한다.
		boolean isMulticastAddress() : IP주소가 멀티캐스트 주소인지 알려준다.
		boolean isLoopbackAddress() : IP주소가 loopback주소인지 알려준다.
*/


		try {
			// 1. 서버소켓 생성
			serverSocket = new ServerSocket();

			// 2. binding(소켓에 소켓주소(IP + Port)을 바인딩(붙인다)한다.
			InetAddress inetAddress = InetAddress.getLocalHost();
			String hostAddress = inetAddress.getHostAddress();

			serverSocket.bind(new InetSocketAddress(hostAddress, PORT));
			System.out.println("[server] binding " + hostAddress + ":" + PORT);

			// 3. accept(클라이언트로 부터 연결요청을 기다린다.)
			Socket socket = serverSocket.accept(); // block(정지)
			InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();

			InetAddress inetRemoteAddress = inetSocketAddress.getAddress();
			String remoteHostAddress = inetRemoteAddress.getHostAddress();
			int remoteHostPort = inetSocketAddress.getPort();

			System.out.println("[server] connected by client[" + remoteHostAddress + ":" + remoteHostPort + "]");

			try {
				// 4. 데이터 통신 (Io클래스 추상화 후 파일스트림과 똑같게)
				InputStream inputStream = socket.getInputStream();
				OutputStream outputStream = socket.getOutputStream();

			while(true){
					// 5. 데이터 읽기
					byte[] buffer = new byte[256];
					int readByteCount = inputStream.read(buffer); // block
					if (readByteCount == -1) {
						// 정상종료(remote socket close() 불러서 정상적으로 소켓을 닫았다.)
						System.out.println("[server] closed by client");
						break;
					}
					String data = new String(buffer, 0, readByteCount, "UTF-8");
					System.out.println("[server] received:" + data);

					// 6. 쓰기
					outputStream.write(data.getBytes("UTF-8"));
				}
			}catch(SocketException ex){
				System.out.println("[server] abnormal closed by");
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				try {

					// 5. 자원정리(소켓닫기)
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null && serverSocket.isClosed() == false) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
