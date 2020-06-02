package org.jeecg.common.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class NettyServer  implements CommandLineRunner {
    @Value("${netty.port}")
    private int port;

    @Autowired
    private NettyDataEngine nettyDataEngine;

    public void start() {
        //开启http服务
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new NettyHandle(nettyDataEngine));//处理连接
                    }
                });
        try {
            ChannelFuture f= bootstrap.bind(port).sync();
          //  f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            //bossGroup.shutdownGracefully();
           // workerGroup.shutdownGracefully();
            e.printStackTrace();
        }
    }

    @Override
    public void run(String... args) throws Exception { //http服务启动
        this.start();
        System.out.println("netty启动，监听端口："+this.port);
    }
}
