package com.example.erge.ApiModel;

public class DefaultAddressModel {

    /**
     * status : true
     * code : 200
     * message : Success
     */

    private boolean status;
    private int code;
    private String message;

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
}
