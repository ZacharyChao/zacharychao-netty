package com.zacharychao.netty.w3cschool;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 服务端处理channel
 * @author Administrator
 *
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter{
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		//丢弃收到的数据
		((ByteBuf)msg).release();
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		//出现异常时关闭连接
		cause.printStackTrace();
		ctx.close();
	}
}
