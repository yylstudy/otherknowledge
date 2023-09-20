package otter;

import com.alibaba.otter.node.extend.processor.AbstractEventProcessor;
import com.alibaba.otter.shared.etl.model.EventData;
import com.alibaba.otter.shared.etl.model.EventType;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2021/11/15 19:33
 */

public class FilterCreateEventProcessor extends AbstractEventProcessor {
    @Override
    public boolean process(EventData eventData) {
        EventType eventType = eventData.getEventType();
        return eventType.isDdl()
                &&eventType.isCreate()
                ;
    }
}
