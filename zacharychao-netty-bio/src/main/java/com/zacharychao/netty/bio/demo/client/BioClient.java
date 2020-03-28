package com.zacharychao.netty.bio.demo.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class BioClient {
	private static final int PORT = 8000;
	public static void main(String[] args) {
		Socket socket = null;
		BufferedReader reader = null;
		PrintWriter writer = null;
		try {
			socket = new Socket("127.0.0.1",PORT);
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			writer = new PrintWriter(socket.getOutputStream(),true);
			
			writer.println("hello world");
			String line = null;
			while((line = reader.readLine()) != null && line.trim().length() != 0) {
				System.out.println(line);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(writer != null) {
				writer.close();
			}
			
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
