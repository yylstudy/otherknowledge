package netty2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/12/9 15:35
 */

public class Server {
    public static void main(String[] args) throws Exception{
//        EventLoopGroup bossGroup = new NioEventLoopGroup();
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//        ServerBootstrap serverBootstrap = new ServerBootstrap();
//        serverBootstrap.group(bossGroup,workerGroup);
//        serverBootstrap.channel(NioServerSocketChannel.class);
//        serverBootstrap.option(ChannelOption.SO_BACKLOG,1024);
//        serverBootstrap.option(ChannelOption.SO_REUSEADDR,true);
//        serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE,true);
//        serverBootstrap.localAddress(9999);
//        serverBootstrap.childHandler(new ChannelInitializer<>() {
//            @Override
//            protected void initChannel(Channel channel) throws Exception {
//                channel.pipeline().addLast();
//            }
//        });
//        ChannelFuture channelFuture = serverBootstrap.bind().sync();
//        Channel channel = channelFuture.channel();
//        channel.closeFuture().sync();

    }
}
