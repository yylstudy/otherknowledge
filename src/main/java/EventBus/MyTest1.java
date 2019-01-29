package EventBus;

import com.google.common.eventbus.EventBus;

/**
 * 学习guava的事件发布订阅
 * @Author: yyl
 * @Date: 2018/11/13 17:12
 */
public class MyTest1 {
    public static void main(String[] args){
        /**创建一个事件总线*/
        EventBus eventBus = new EventBus();
        /**创建一个事件订阅对象*/
        EventSubscribe eventSubscribe = new EventSubscribe();
        /**向事件总线中心注册事件订阅对象*/
        eventBus.register(eventSubscribe);
        MessageEvent messageEvent = new MessageEvent("2222");
        /**发布事件*/
        eventBus.post(messageEvent);
    }

}
