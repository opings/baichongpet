package com.baichong.util;

/**
 * @author zyz
 * @since 2020-02-21 11:15
 */
public class IDUtils {

    private static final SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker();

    /**
     * 获取雪花算法生成的ID
     *
     * @return
     */
    public static String getId() {
        return String.valueOf(snowflakeIdWorker.nextId());
    }
}

