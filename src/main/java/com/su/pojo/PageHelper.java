package com.su.pojo;


import java.util.List;

public class PageHelper<T> {

    private Integer totalPage;
    private Integer curPage;
    private List<T> objects;
    private Integer pageSize;
    private Integer totalCount;

    /**
     * @param page       目标页码
     * @param pageSize   每页条数
     * @param totalCount 总记录数<br>
     *                   <p>
     *                   示例:<br>
     *                   Integer totalCount = this.getTotalCount();<br>
     *                   PageHelper<Example> pageHelper = new PageHelper<>(page, pageSize, totalCount);<br>
     *                   List<Example> list = am.list(pageHelper.getIndex(), pageSize);<br>
     *                   pageHelper.setObjects(list);
     */
    public PageHelper(Integer page, Integer pageSize, Integer totalCount) {
        this.curPage = page;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = (int) Math.ceil(totalCount * 1.0 / pageSize);
    }


    /**
     * 根据curPage 返回mysql: limit index, length 中的index
     */
    public Integer getIndex() {
        return (this.curPage - 1) * this.pageSize;
    }

    public Integer getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurPage() {
        return curPage;
    }

    public void setCurPage(Integer curPage) {
        this.curPage = curPage;
    }

    public Object getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
