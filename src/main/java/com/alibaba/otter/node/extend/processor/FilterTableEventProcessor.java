package com.alibaba.otter.node.extend.processor;

import com.alibaba.otter.node.extend.processor.AbstractEventProcessor;
import com.alibaba.otter.shared.etl.model.EventData;
import com.alibaba.otter.shared.etl.model.EventType;

import java.util.regex.Pattern;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description TODO
 * @createTime 2023/7/8 10:35
 */

public class FilterTableEventProcessor extends AbstractEventProcessor {
    private String pattern = "private_bind_info_axe_\\d{4}_\\d{1}";
    @Override
    public boolean process(EventData eventData) {
        EventType eventType = eventData.getEventType();
        if(!eventType.isDml()){
            return true;
        }
        String tableName = eventData.getTableName();
        return !Pattern.matches(pattern,tableName);
    }
}
