package EventBus;

/**
 * 定义一个事件
 * @Author: yyl
 * @Date: 2018/11/13 17:06
 */

public class MessageEvent {
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
