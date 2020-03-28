package com.zacharychao.netty.bio.demo.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.zacharychao.netty.bio.demo.handler.TimeServerHandler;

public class BioServer {
	private static final int PORT = 8000; 
	public static void main(String[] args) throws IOException {
		ServerSocket server = null;
		try {
			server = new ServerSocket(PORT);
			System.out.println("socket server has run with port " + PORT);
			Socket socket = null;
			while(true) {
				socket = server.accept();
				new Thread(new TimeServerHandler(socket)).start();;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			server.close();
		}
	}
}
