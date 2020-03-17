package com.example.erge.ApiModel;

import java.util.ArrayList;
import java.util.List;

public class orderJsonModel {
    /**
     * address_id : 123
     * instructions : nothing
     * time : now
     * date : now
     * shop_id : 123456
     * shop_name : Sample
     * shop_lat : 8.5710
     * shop_long : 76.8663
     * shop_address :
     * shop_zip : 1234
     * shop_phone :
     * delivery_charge :
     * items : [{"itemId":"","parentItemId":"","name":"","msrp":5.5,"salePrice":7.5,"shortDescription":"","longDescription":"","mediumImage":"","modelNumber":"","sellerInfo":"","customerRating":"","numReviews":"","stock":"","purchaseCount":1}]
     */

    private String address_id;
    private String instructions;
    private String time;
    private String date;
    private String shop_id;
    private String shop_name;
    private String shop_lat;
    private String shop_long;
    private String shop_address;
    private String shop_zip;
    private String shop_phone;
    private String delivery_charge;
    private List<ItemsBean> items = new ArrayList<>();

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_lat() {
        return shop_lat;
    }

    public void setShop_lat(String shop_lat) {
        this.shop_lat = shop_lat;
    }

    public String getShop_long() {
        return shop_long;
    }

    public void setShop_long(String shop_long) {
        this.shop_long = shop_long;
    }

    public String getShop_address() {
        return shop_address;
    }

    public void setShop_address(String shop_address) {
        this.shop_address = shop_address;
    }

    public String getShop_zip() {
        return shop_zip;
    }

    public void setShop_zip(String shop_zip) {
        this.shop_zip = shop_zip;
    }

    public String getShop_phone() {
        return shop_phone;
    }

    public void setShop_phone(String shop_phone) {
        this.shop_phone = shop_phone;
    }

    public String getDelivery_charge() {
        return delivery_charge;
    }

    public void setDelivery_charge(String delivery_charge) {
        this.delivery_charge = delivery_charge;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * itemId :
         * parentItemId :
         * name :
         * msrp : 5.5
         * salePrice : 7.5
         * shortDescription :
         * longDescription :
         * mediumImage :
         * modelNumber :
         * sellerInfo :
         * customerRating :
         * numReviews :
         * stock :
         * purchaseCount : 1
         */

        private String itemId;
        private String parentItemId;
        private String name;
        private double msrp;
        private double salePrice;
        private String shortDescription;
        private String longDescription;
        private String mediumImage;
        private String modelNumber;
        private String sellerInfo;
        private String customerRating;
        private String numReviews;
        private String stock;
        private int purchaseCount;

        public String getItemId() {
            return itemId;
        }

        public void setItemId(String itemId) {
            this.itemId = itemId;
        }

        public String getParentItemId() {
            return parentItemId;
        }

        public void setParentItemId(String parentItemId) {
            this.parentItemId = parentItemId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getMsrp() {
            return msrp;
        }

        public void setMsrp(double msrp) {
            this.msrp = msrp;
        }

        public double getSalePrice() {
            return salePrice;
        }

        public void setSalePrice(double salePrice) {
            this.salePrice = salePrice;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public void setShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
        }

        public String getLongDescription() {
            return longDescription;
        }

        public void setLongDescription(String longDescription) {
            this.longDescription = longDescription;
        }

        public String getMediumImage() {
            return mediumImage;
        }

        public void setMediumImage(String mediumImage) {
            this.mediumImage = mediumImage;
        }

        public String getModelNumber() {
            return modelNumber;
        }

        public void setModelNumber(String modelNumber) {
            this.modelNumber = modelNumber;
        }

        public String getSellerInfo() {
            return sellerInfo;
        }

        public void setSellerInfo(String sellerInfo) {
            this.sellerInfo = sellerInfo;
        }

        public String getCustomerRating() {
            return customerRating;
        }

        public void setCustomerRating(String customerRating) {
            this.customerRating = customerRating;
        }

        public String getNumReviews() {
            return numReviews;
        }

        public void setNumReviews(String numReviews) {
            this.numReviews = numReviews;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public int getPurchaseCount() {
            return purchaseCount;
        }

        public void setPurchaseCount(int purchaseCount) {
            this.purchaseCount = purchaseCount;
        }
    }
}
