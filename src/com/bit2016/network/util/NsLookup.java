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
			InetAddress[] inetAddresses = InetAddress.getAllByName(host);
			if("exit".equals(host)){	// 비교후 맞으면 true 아니면 false
				break;
			}
			for(InetAddress inetAddress : inetAddresses){
				System.out.println(inetAddress.getHostAddress());
			}
			System.out.print(">>");
		}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		scanner.close();
	}
	}