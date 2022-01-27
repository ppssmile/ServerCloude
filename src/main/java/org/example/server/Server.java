package org.example.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {
    private static int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new MsgServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(NioChannelOption.SO_KEEPALIVE, true);
            System.out.println("Server initial");

            ChannelFuture serverChannelFuture = serverBootstrap.bind(port).sync();
            serverChannelFuture.channel().closeFuture().sync();
            System.out.println("Getting future");
            System.out.println();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();

        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 8088;
        if(args.length>0){
            port = Integer.parseInt(args[0]);
        }

        new Server(port).run();

        System.out.println("Server started");
    }
}
