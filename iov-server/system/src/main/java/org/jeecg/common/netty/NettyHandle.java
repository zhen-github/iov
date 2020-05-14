package org.jeecg.common.netty;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class NettyHandle extends ChannelInboundHandlerAdapter {
    //消息处理类
    NettyDataEngine nettyDataEngine;

    public NettyHandle(NettyDataEngine nettyDataEngine) {
        this.nettyDataEngine = nettyDataEngine;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        try {
            ByteBuf buf = (ByteBuf) msg;
            nettyDataEngine.dataEngine(ctx.channel().id().asLongText(), buf);//调用消息解析
            ctx.writeAndFlush(Unpooled.copiedBuffer("收到".getBytes()));
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("连上了");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        nettyDataEngine.inactive(ctx);
        System.out.println("断开");
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.writeAndFlush(Unpooled.copiedBuffer("发生错误".getBytes()));
        //ctx.close();
    }
}
