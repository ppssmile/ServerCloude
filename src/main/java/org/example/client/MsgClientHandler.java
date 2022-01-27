package org.example.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import java.util.Date;

public class MsgClientHandler extends ChannelDuplexHandler {



    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ChannelActive");
        ctx.pipeline().channel().alloc().buffer(128);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf m = (ByteBuf) msg;
        System.out.print("Принято сообщение:   ");
        try {
            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        } finally {
            m.release();
        }
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ByteBuf out = (ByteBuf) msg;
        try {
            while (out.isReadable()){
                System.out.println((char) out.readByte());
                System.out.flush();
            }
        } finally {
            out.release();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
