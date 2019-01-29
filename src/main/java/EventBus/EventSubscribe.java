package EventBus;

import com.google.common.eventbus.Subscribe;

/**
 * @Author: yyl
 * @Date: 2018/11/13 17:09
 */
public class EventSubscribe {
    /**
     * 事件订阅方法，guava使用@Subscribe注解来定义订阅者的方法，当事件发布时，这个方法将被调用
     * @param event
     */
    @Subscribe
    public void onMessageEvent(MessageEvent event){
        System.out.println("事件发布，我收到了："+event.getMessage());
    }
}
