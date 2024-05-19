package com.ohgiraffers.dosirak.user.order.model.dto;

public class OrderDTO {
    int detailitem_count;
    int detailitem_code;
    int suitbox_code;
    String detail_code;
    int order_code;

    public OrderDTO() {
    }

    public OrderDTO(int detailitem_count, int detailitem_code, int suitbox_code, String detail_code, int order_code) {
        this.detailitem_count = detailitem_count;
        this.detailitem_code = detailitem_code;
        this.suitbox_code = suitbox_code;
        this.detail_code = detail_code;
        this.order_code = order_code;
    }

    public int getDetailitem_count() {
        return detailitem_count;
    }

    public void setDetailitem_count(int detailitem_count) {
        this.detailitem_count = detailitem_count;
    }

    public int getDetailitem_code() {
        return detailitem_code;
    }

    public void setDetailitem_code(int detailitem_code) {
        this.detailitem_code = detailitem_code;
    }

    public int getSuitbox_code() {
        return suitbox_code;
    }

    public void setSuitbox_code(int suitbox_code) {
        this.suitbox_code = suitbox_code;
    }

    public String getDetail_code() {
        return detail_code;
    }

    public void setDetail_code(String detail_code) {
        this.detail_code = detail_code;
    }

    public int getOrder_code() {
        return order_code;
    }

    public void setOrder_code(int order_code) {
        this.order_code = order_code;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "detailitem_count=" + detailitem_count +
                ", detailitem_code=" + detailitem_code +
                ", suitbox_code=" + suitbox_code +
                ", detail_code='" + detail_code + '\'' +
                ", order_code=" + order_code +
                '}';
    }
}
