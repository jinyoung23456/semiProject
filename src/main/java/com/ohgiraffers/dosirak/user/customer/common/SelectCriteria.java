package com.ohgiraffers.dosirak.user.customer.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class SelectCriteria {

    private int page;
    private int totalCount;
    private int limit;
    private int buttonAmount;
    private int maxPage;
    private int startPage;
    private int endPage;
    private int offset;
    private String searchCondition;
    private String searchValue;

    public SelectCriteria () {}

    public SelectCriteria(int page, int totalCount, int limit, int buttonAmount, int maxPage, int startPage, int endPage, int offset, String searchCondition, String searchValue) {
        this.page = page;
        this.totalCount = totalCount;
        this.limit = limit;
        this.buttonAmount = buttonAmount;
        this.maxPage = maxPage;
        this.startPage = startPage;
        this.endPage = endPage;
        this.offset = offset;
        this.searchCondition = searchCondition;
        this.searchValue = searchValue;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getButtonAmount() {
        return buttonAmount;
    }

    public void setButtonAmount(int buttonAmount) {
        this.buttonAmount = buttonAmount;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getSearchCondition() {
        return searchCondition;
    }

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    @Override
    public String toString() {
        return "SelectCriteria{" +
                "page=" + page +
                ", totalCount=" + totalCount +
                ", limit=" + limit +
                ", buttonAmount=" + buttonAmount +
                ", maxPage=" + maxPage +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", offset=" + offset +
                ", searchCondition='" + searchCondition + '\'' +
                ", searchValue='" + searchValue + '\'' +
                '}';
    }
}
