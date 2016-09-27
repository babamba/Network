package com.bit2016.network.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NsLookup {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("주소입력 \n>>");
		try {
			while(true){
			String host = scanner.nextLine();
			if(host.equals("exit")){	// 비교후 맞으면 true 아니면 false
				break;
			}
			InetAddress[] inetAddresses = InetAddress.getAllByName(host);
			for(InetAddress inetAddress : inetAddresses){
				System.out.println(inetAddress.getHostAddress());
			}
			System.out.print(">>");
		}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}finally{
			scanner.close();
			System.out.println("종료");
		}
	}
	}