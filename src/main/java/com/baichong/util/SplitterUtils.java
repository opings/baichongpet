package com.baichong.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author zyz
 * @since 2019-07-22 20:46
 */
public class SplitterUtils {


    /**
     * 字符串转换为list
     *
     * @param str
     * @return
     */
    public static List<String> toList(String str) {
        return toList(str, ",");
    }


    /**
     * 字符串转换为list
     *
     * @param str
     * @param separator
     * @return
     */
    public static List<String> toList(String str, String separator) {
        if (StringUtils.isBlank(str)) {
            return Lists.newArrayList();
        }
        return Lists.newArrayList(Splitter.on(separator).trimResults()
                .omitEmptyStrings().split(str));
    }
}
