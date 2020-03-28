package com.zacharychao.netty.bio.demo.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class TimeServerHandler implements Runnable{

	private Socket socket;
	
	public TimeServerHandler(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {

		BufferedReader reader = null;
		PrintWriter writer = null;
		
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(),true);
			String line = null;
			while((line = reader.readLine()) != null && line.trim().length() != 0) {
				System.out.println(line);
				writer.println(new Date().toString());
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
