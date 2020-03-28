package com.zacharychao.netty.w3cschool.discard.server;

import com.zacharychao.netty.w3cschool.discard.adapter.DiscardServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 丢弃任何进入的数据
 * 
 * @author Administrator
 *
 */
public class DiscardServer {
	private int port;

	public DiscardServer(int port) {
		this.port = port;
	}

	public void run() {
		// 创建多线程事件循环器，接收进来的连接
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// 创建多线程事件循环器，处理已经被接受进来的连接
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			// 启动NIO服务的辅助启动类
			ServerBootstrap server = new ServerBootstrap();
			server.group(bossGroup, workerGroup)
					// 指定使用NioServerSocketChannel类
					.channel(NioServerSocketChannel.class)
					//
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new DiscardServerHandler());
						}
					});

			//绑定端口，同步等待成功
			ChannelFuture f = server.bind(port).sync();
			//等待服务端监听端口关闭
			f.channel().closeFuture().sync();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) {
		int port;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		} else {
			port = 8080;
		}
		new DiscardServer(port).run();
	}
}
