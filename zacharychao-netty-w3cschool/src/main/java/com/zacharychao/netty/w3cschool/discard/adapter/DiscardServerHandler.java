package com.zacharychao.netty.w3cschool.discard.adapter;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.socksx.SocksPortUnificationServerHandler;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * 服务端处理channel
 * @author Administrator
 *
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter{
	@Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        System.out.println("EchoServerHandle channelReadComplete");
    }
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		ByteBuf in = (ByteBuf)msg;
		System.out.println("服务端收到数据: "+ in.toString(CharsetUtil.UTF_8));
		ctx.writeAndFlush(in);
		
		
//		ByteBuf in = (ByteBuf)msg;
//		try {
//			while(in.isReadable()) {
//				System.out.print((char)in.readByte());
//				System.out.flush();
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			ReferenceCountUtil.release(msg);
//		}
		
//		//丢弃收到的数据
//		((ByteBuf)msg).release();
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		//出现异常时关闭连接
		cause.printStackTrace();
		ctx.close();
	}
}
