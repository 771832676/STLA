package com.stla.figure.bean;

import com.stla.figure.utility.PageUtil;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class pageBean<T>  implements Serializable {

    private static final long serialVersionUID = -1117395602161773192L;

    private int curPage=0;//当前页码
    private int pageCount = 10;//总条页数

    /**
     * 要分页的list数据
     */
    private List<T> myList;


    /**
     * 分页后的总页数
     */
    private int pageSum;

    /**
     * 总数据条数
     */
    private int recordCount;

    public List<T> getMyList() {
        return myList;
    }

    public int getPageSum() {
        return pageSum;
    }

    public void setPageSum(int pageSum) {
        this.pageSum = pageSum;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getCurPage() {
        curPage = curPage+pageCount;
        if (curPage > 0){
            curPage = curPage*pageCount-pageCount;
        }
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * 设置要分页的数据
     *
     * @param myList
     */
    public pageBean<T> setMyList(List<T> myList,int recordCount,int pageCount) {
        this.myList = myList;
        // 计算页数
        this.recordCount = recordCount;
        if (recordCount % pageCount == 0) {
            pageSum = recordCount / pageCount;
        } else {
            pageSum = recordCount / pageCount + 1;
        }

        return this;
    }
}
