package com.bit2016.network.thread;

public class MultiThreadEx03 {
	public static void main(String[] args) {
		Thread thread1 = new DigitThread();
		Thread thread2 = new AlphabetThread();
		Thread thread3 = new Thread(new UpperCaseAlphabetRunnableImpl());// 상속하고 인터페이스 runnable구현한 클래스 생성자로 객체 생성
		
		thread1.start();
		thread2.start();
		thread3.start();
	}
}
