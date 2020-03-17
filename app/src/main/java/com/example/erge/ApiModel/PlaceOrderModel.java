package com.example.erge.ApiModel;

public class PlaceOrderModel {

    /**
     * status : true
     * code : 200
     * message : Orderd successfully
     * order_id : CVUD458ACE
     */

    private boolean status;
    private int code;
    private String message;
    private String order_id;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
