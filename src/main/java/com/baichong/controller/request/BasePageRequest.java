package com.baichong.controller.request;

import lombok.Data;

/**
 * @author zyz
 * @since 2020-03-22 14:22
 */
@Data
public class BasePageRequest extends BaseRequest {

    /**
     * 页码
     */
    private Integer pageNo = 1;
    /**
     * 每页数量
     */
    private Integer pageSize = 20;


    public Integer getStartIndex() {
        return (pageNo - 1) * pageSize;
    }

    public Integer getPageNo() {
        return pageNo == null ? 1 : pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize == null ? 10 : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
