package com.shawn.votesystem.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@ToString
@Getter
@Setter
@Accessors(chain = true)
public class PageItem implements Serializable {

    /** 第几页  */
    private int page;
    /** list数据 */
    private List<?> data;
    /** 总数 */
    private long total;

    /** 分页大小 */
    private int pageSize;

    /** 总页数*/
    private int totalPage;

    public PageItem(int page, List<?> data, long total,int pageSize) {
        this.page = page;
        this.data = data;
        this.total = total;
        this.pageSize = pageSize;
    }

    public PageItem() {

    }
}
