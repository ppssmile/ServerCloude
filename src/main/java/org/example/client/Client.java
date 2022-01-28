package org.example.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Client {

    private static final int PORT = 8088;
    private static boolean isWorked = true;

    public Client() {}

    public void run() throws UnknownHostException, InterruptedException {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                                    ch.pipeline().addLast(new MsgClientHandler());

                        }
                    });
            System.out.println("Client initial");

            ChannelFuture channelFuture = bootstrap.connect(InetAddress.getLocalHost(), PORT).sync();
            System.out.println("Client started");
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }


    public static void main(String[] args) throws UnknownHostException, InterruptedException {

        Client c = new Client();
        c.run();
        System.out.println("Client started");

    }
}