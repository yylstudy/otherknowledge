package com.alibaba.otter.node.extend.processor;

import com.alibaba.otter.shared.etl.model.EventColumn;
import com.alibaba.otter.shared.etl.model.EventData;
import com.alibaba.otter.shared.etl.model.EventType;

import java.util.List;
import java.util.Optional;

/**
 * @author yang.yonglian
 * @version 1.0.0
 * @Description 不同步xxl_job_registry表
 * @createTime 2023/7/8 10:35
 */

public class FilterXxlJobTableEventProcessor extends AbstractEventProcessor {
    @Override
    public boolean process(EventData eventData) {
        EventType eventType = eventData.getEventType();
        if(!eventType.isDml()){
            return true;
        }
        String tableName = eventData.getTableName();
        if(!"xxl_job_registry".equals(tableName)){
            return true;
        }
        List<EventColumn> eventColumns = eventData.getColumns();
        Optional<EventColumn> optional = eventColumns.stream().filter(eventColumn ->
                "registry_value".equals(eventColumn.getColumnName())).findFirst();
        if(optional.isPresent()){
            String value = optional.get().getColumnValue();
            return !value.contains("http://10.222");
        }
        return true;
    }
}
