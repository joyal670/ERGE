package com.example.erge.ApiModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class viewAddressModel {

    /**
     * status : true
     * code : 200
     * message : Loaded successfully
     * data : [{"id":"112","location":"kazhakotam, leela sidegate hgfg","lat":"8.5605","long":"76.8812","address":"first floor, bla bla "},{"id":"111","location":"kazhakotam, leela sidegate hgfg","lat":"8.5605","long":"76.8812","address":"first floor, bla bla "},{"id":"110","location":"kazhakotam, leela sidegate hgfg","lat":"8.5605","long":"76.8812","address":"first floor, bla bla "},{"id":"109","location":"kazhakotam, leela sidegate ","lat":"8.5605","long":"76.8812","address":"first floor, bla bla "}]
     */

    private boolean status;
    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 112
         * location : kazhakotam, leela sidegate hgfg
         * lat : 8.5605
         * long : 76.8812
         * address : first floor, bla bla
         */

        private String id;
        private String location;
        private String lat;
        @SerializedName("long")
        private String longX;
        private String address;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLongX() {
            return longX;
        }

        public void setLongX(String longX) {
            this.longX = longX;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
