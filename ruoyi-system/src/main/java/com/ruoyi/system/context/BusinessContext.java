package com.ruoyi.system.context;

import cn.hutool.core.util.ObjectUtil;
import com.yomahub.liteflow.context.ContextBean;
import com.yomahub.liteflow.exception.NullParamException;
import lombok.Data;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 上下文参数
 */
@Data
@ContextBean("BusinessContext")
public class BusinessContext {
    private final ConcurrentHashMap<String, Object> dataMap = new ConcurrentHashMap();
    private <T> void putDataMap(String key, T t) {
        if (ObjectUtil.isNull(t)) {
            throw new NullParamException("data can't accept null param");
        } else {
            this.dataMap.put(key, t);
        }
    }

    public boolean hasData(String key) {
        return this.dataMap.containsKey(key);
    }
    public <T> T getData(String key) {
        return (T) this.dataMap.get(key);
    }

    public <T> void setData(String key, T t) {
        this.putDataMap(key, t);
    }

  /*  private Integer Y1;

    private Integer Y2;

    private Integer X1;

    private Integer O1;

    private Integer O2;

    private Integer O3;

    private Integer Z1;

    private Integer Z2;

    private Integer P1;

    private Integer R1;

    private Integer R2;

    private Integer R3;

    private Integer Q1;

    private Integer S1;

    private Integer T1;
*/
}
