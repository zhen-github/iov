package org.jeecg.common.netty;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
//这是具体连接的处理
public class NettyHandle extends ChannelInboundHandlerAdapter {
    //消息处理类
    NettyDataEngine nettyDataEngine;

    public NettyHandle(NettyDataEngine nettyDataEngine) {
        this.nettyDataEngine = nettyDataEngine;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {//接收消息

        try {
            ByteBuf buf = (ByteBuf) msg;
            nettyDataEngine.dataEngine(ctx.channel().id().asLongText(), buf);//调用消息解析
            ctx.writeAndFlush(Unpooled.copiedBuffer("收到".getBytes()));
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {//发生连接时调用
        super.channelActive(ctx);
        System.out.println("连上了");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {//断开时调用
        nettyDataEngine.inactive(ctx);//处理断开的后续工作
        System.out.println("断开");
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {//发生错误时调用
        cause.printStackTrace();
        ctx.writeAndFlush(Unpooled.copiedBuffer("发生错误".getBytes()));
        //ctx.close();
    }
}
