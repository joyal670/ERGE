package com.example.erge.ApiModel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class currentOrderModel implements Serializable {

    /**
     * status : true
     * code : 200
     * message : loaded successfully
     * upcomming : [{"order_id":"WVUK1B1355","user_details":{"id":"234","email":"joyal@gmail.com","first_name":"joyal","last_name":"","phone":"9061243470","gender":" ","profile_pic":"https://dev.sicsapp.com/erge/uploads/profile/default.png","social_profile_pic":""},"shop_data":{"shop_id":"123456","profile_pic":"","shop_name":"Sample","shop_address":"","shop_details":null,"shop_lat":"8.571","shop_long":"76.8663"},"order_delivery_type":"now","order_delivery_time":"11:18:07","order_delivery_date":"2020-03-11","order_delivery_status":"Order ready to pickedup by driver","delivery_address":{"id":"801","order_id":"WVUK1B1355","location":"Los Angeles,California,United States,90007","lat":"34.0256262","long":"-118.28504399999998","address":"USC Village,South Hoover Street","remarks":null,"created_at":"2020-03-04 17:09:32","updated_at":"2020-03-04 17:09:32"},"delivery_charge":"","payment_method":"cod","payment_status":"pending","order_status":"ready","order_data":[{"id":"1037","order_id":"WVUK1B1355","product_id":"","product_parent_id":"","name":"","price":"7.5","sale_price":"5.5","short_description":"","long_description":"","medium_image":"","model_number":"","sellerInfo":"","customer_rating":"","num_reviews":"","quantity":"1","stock":"","verify_images":[]}],"delivered_by":{"id":"","email":"","first_name":"","last_name":"","phone":"","gender":"","profile_pic":"","social_profile_pic":"","user_id":"","insurance_doc":"","license_doc":"","pca_doc":"","vehicle_no":"","vehicle_type_name":"","vehicle_type_id":"","is_online":"","driver_stars":""},"remarks":"nothing","created_at":"2020-03-11 11:18:07"},{"order_id":"SENF3C7F72","user_details":{"id":"234","email":"joyal@gmail.com","first_name":"joyal","last_name":"","phone":"9061243470","gender":" ","profile_pic":"https://dev.sicsapp.com/erge/uploads/profile/default.png","social_profile_pic":""},"shop_data":{"shop_id":"123456","profile_pic":"","shop_name":"Sample","shop_address":"","shop_details":null,"shop_lat":"8.571","shop_long":"76.8663"},"order_delivery_type":"now","order_delivery_time":"11:16:19","order_delivery_date":"2020-03-11","order_delivery_status":"Order ready to pickedup by driver","delivery_address":{"id":"800","order_id":"SENF3C7F72","location":"Los Angeles,California,United States,90007","lat":"34.0256262","long":"-118.28504399999998","address":"USC Village,South Hoover Street","remarks":null,"created_at":"2020-03-04 17:09:32","updated_at":"2020-03-04 17:09:32"},"delivery_charge":"","payment_method":"cod","payment_status":"pending","order_status":"ready","order_data":[{"id":"1036","order_id":"SENF3C7F72","product_id":"","product_parent_id":"","name":"","price":"7.5","sale_price":"5.5","short_description":"","long_description":"","medium_image":"","model_number":"","sellerInfo":"","customer_rating":"","num_reviews":"","quantity":"1","stock":"","verify_images":[]}],"delivered_by":{"id":"","email":"","first_name":"","last_name":"","phone":"","gender":"","profile_pic":"","social_profile_pic":"","user_id":"","insurance_doc":"","license_doc":"","pca_doc":"","vehicle_no":"","vehicle_type_name":"","vehicle_type_id":"","is_online":"","driver_stars":""},"remarks":"nothing","created_at":"2020-03-11 11:16:19"},{"order_id":"NUXO6E78EB","user_details":{"id":"234","email":"joyal@gmail.com","first_name":"joyal","last_name":"","phone":"9061243470","gender":" ","profile_pic":"https://dev.sicsapp.com/erge/uploads/profile/default.png","social_profile_pic":""},"shop_data":{"shop_id":"123456","profile_pic":"","shop_name":"Sample","shop_address":"","shop_details":null,"shop_lat":"8.571","shop_long":"76.8663"},"order_delivery_type":"now","order_delivery_time":"10:43:50","order_delivery_date":"2020-03-11","order_delivery_status":"Order ready to pickedup by driver","delivery_address":{"id":"799","order_id":"NUXO6E78EB","location":"Los Angeles,California,United States,90007","lat":"34.0256262","long":"-118.28504399999998","address":"USC Village,South Hoover Street","remarks":null,"created_at":"2020-03-04 17:09:32","updated_at":"2020-03-04 17:09:32"},"delivery_charge":"","payment_method":"cod","payment_status":"pending","order_status":"ready","order_data":[{"id":"1035","order_id":"NUXO6E78EB","product_id":"","product_parent_id":"","name":"","price":"7.5","sale_price":"5.5","short_description":"","long_description":"","medium_image":"","model_number":"","sellerInfo":"","customer_rating":"","num_reviews":"","quantity":"1","stock":"","verify_images":[]}],"delivered_by":{"id":"","email":"","first_name":"","last_name":"","phone":"","gender":"","profile_pic":"","social_profile_pic":"","user_id":"","insurance_doc":"","license_doc":"","pca_doc":"","vehicle_no":"","vehicle_type_name":"","vehicle_type_id":"","is_online":"","driver_stars":""},"remarks":"nothing","created_at":"2020-03-11 10:43:50"}]
     * deliverd : [{"order_id":"WVUK1B1355","user_details":{"id":"234","email":"joyal@gmail.com","first_name":"joyal","last_name":"","phone":"9061243470","gender":" ","profile_pic":"https://dev.sicsapp.com/erge/uploads/profile/default.png","social_profile_pic":""},"shop_data":{"shop_id":"123456","profile_pic":"","shop_name":"Sample","shop_address":"","shop_details":null,"shop_lat":"8.571","shop_long":"76.8663"},"order_delivery_type":"now","order_delivery_time":"11:18:07","order_delivery_date":"2020-03-11","order_delivery_status":"Order ready to pickedup by driver","delivery_address":{"id":"801","order_id":"WVUK1B1355","location":"Los Angeles,California,United States,90007","lat":"34.0256262","long":"-118.28504399999998","address":"USC Village,South Hoover Street","remarks":null,"created_at":"2020-03-04 17:09:32","updated_at":"2020-03-04 17:09:32"},"delivery_charge":"","payment_method":"cod","payment_status":"pending","order_status":"ready","order_data":[{"id":"1037","order_id":"WVUK1B1355","product_id":"","product_parent_id":"","name":"","price":"7.5","sale_price":"5.5","short_description":"","long_description":"","medium_image":"","model_number":"","sellerInfo":"","customer_rating":"","num_reviews":"","quantity":"1","stock":"","verify_images":[]}],"delivered_by":{"id":"","email":"","first_name":"","last_name":"","phone":"","gender":"","profile_pic":"","social_profile_pic":"","user_id":"","insurance_doc":"","license_doc":"","pca_doc":"","vehicle_no":"","vehicle_type_name":"","vehicle_type_id":"","is_online":"","driver_stars":""},"remarks":"nothing","created_at":"2020-03-11 11:18:07"},{"order_id":"SENF3C7F72","user_details":{"id":"234","email":"joyal@gmail.com","first_name":"joyal","last_name":"","phone":"9061243470","gender":" ","profile_pic":"https://dev.sicsapp.com/erge/uploads/profile/default.png","social_profile_pic":""},"shop_data":{"shop_id":"123456","profile_pic":"","shop_name":"Sample","shop_address":"","shop_details":null,"shop_lat":"8.571","shop_long":"76.8663"},"order_delivery_type":"now","order_delivery_time":"11:16:19","order_delivery_date":"2020-03-11","order_delivery_status":"Order ready to pickedup by driver","delivery_address":{"id":"800","order_id":"SENF3C7F72","location":"Los Angeles,California,United States,90007","lat":"34.0256262","long":"-118.28504399999998","address":"USC Village,South Hoover Street","remarks":null,"created_at":"2020-03-04 17:09:32","updated_at":"2020-03-04 17:09:32"},"delivery_charge":"","payment_method":"cod","payment_status":"pending","order_status":"ready","order_data":[{"id":"1036","order_id":"SENF3C7F72","product_id":"","product_parent_id":"","name":"","price":"7.5","sale_price":"5.5","short_description":"","long_description":"","medium_image":"","model_number":"","sellerInfo":"","customer_rating":"","num_reviews":"","quantity":"1","stock":"","verify_images":[]}],"delivered_by":{"id":"","email":"","first_name":"","last_name":"","phone":"","gender":"","profile_pic":"","social_profile_pic":"","user_id":"","insurance_doc":"","license_doc":"","pca_doc":"","vehicle_no":"","vehicle_type_name":"","vehicle_type_id":"","is_online":"","driver_stars":""},"remarks":"nothing","created_at":"2020-03-11 11:16:19"},{"order_id":"NUXO6E78EB","user_details":{"id":"234","email":"joyal@gmail.com","first_name":"joyal","last_name":"","phone":"9061243470","gender":" ","profile_pic":"https://dev.sicsapp.com/erge/uploads/profile/default.png","social_profile_pic":""},"shop_data":{"shop_id":"123456","profile_pic":"","shop_name":"Sample","shop_address":"","shop_details":null,"shop_lat":"8.571","shop_long":"76.8663"},"order_delivery_type":"now","order_delivery_time":"10:43:50","order_delivery_date":"2020-03-11","order_delivery_status":"Order ready to pickedup by driver","delivery_address":{"id":"799","order_id":"NUXO6E78EB","location":"Los Angeles,California,United States,90007","lat":"34.0256262","long":"-118.28504399999998","address":"USC Village,South Hoover Street","remarks":null,"created_at":"2020-03-04 17:09:32","updated_at":"2020-03-04 17:09:32"},"delivery_charge":"","payment_method":"cod","payment_status":"pending","order_status":"ready","order_data":[{"id":"1035","order_id":"NUXO6E78EB","product_id":"","product_parent_id":"","name":"","price":"7.5","sale_price":"5.5","short_description":"","long_description":"","medium_image":"","model_number":"","sellerInfo":"","customer_rating":"","num_reviews":"","quantity":"1","stock":"","verify_images":[]}],"delivered_by":{"id":"","email":"","first_name":"","last_name":"","phone":"","gender":"","profile_pic":"","social_profile_pic":"","user_id":"","insurance_doc":"","license_doc":"","pca_doc":"","vehicle_no":"","vehicle_type_name":"","vehicle_type_id":"","is_online":"","driver_stars":""},"remarks":"nothing","created_at":"2020-03-11 10:43:50"}]
     */

    private boolean status;
    private int code;
    private String message;
    private List<UpcommingBean> upcomming;
    private List<DeliverdBean> deliverd;

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

    public List<UpcommingBean> getUpcomming() {
        return upcomming;
    }

    public void setUpcomming(List<UpcommingBean> upcomming) {
        this.upcomming = upcomming;
    }

    public List<DeliverdBean> getDeliverd() {
        return deliverd;
    }

    public void setDeliverd(List<DeliverdBean> deliverd) {
        this.deliverd = deliverd;
    }

    public static class UpcommingBean implements Serializable{
        /**
         * order_id : WVUK1B1355
         * user_details : {"id":"234","email":"joyal@gmail.com","first_name":"joyal","last_name":"","phone":"9061243470","gender":" ","profile_pic":"https://dev.sicsapp.com/erge/uploads/profile/default.png","social_profile_pic":""}
         * shop_data : {"shop_id":"123456","profile_pic":"","shop_name":"Sample","shop_address":"","shop_details":null,"shop_lat":"8.571","shop_long":"76.8663"}
         * order_delivery_type : now
         * order_delivery_time : 11:18:07
         * order_delivery_date : 2020-03-11
         * order_delivery_status : Order ready to pickedup by driver
         * delivery_address : {"id":"801","order_id":"WVUK1B1355","location":"Los Angeles,California,United States,90007","lat":"34.0256262","long":"-118.28504399999998","address":"USC Village,South Hoover Street","remarks":null,"created_at":"2020-03-04 17:09:32","updated_at":"2020-03-04 17:09:32"}
         * delivery_charge :
         * payment_method : cod
         * payment_status : pending
         * order_status : ready
         * order_data : [{"id":"1037","order_id":"WVUK1B1355","product_id":"","product_parent_id":"","name":"","price":"7.5","sale_price":"5.5","short_description":"","long_description":"","medium_image":"","model_number":"","sellerInfo":"","customer_rating":"","num_reviews":"","quantity":"1","stock":"","verify_images":[]}]
         * delivered_by : {"id":"","email":"","first_name":"","last_name":"","phone":"","gender":"","profile_pic":"","social_profile_pic":"","user_id":"","insurance_doc":"","license_doc":"","pca_doc":"","vehicle_no":"","vehicle_type_name":"","vehicle_type_id":"","is_online":"","driver_stars":""}
         * remarks : nothing
         * created_at : 2020-03-11 11:18:07
         */

        private String order_id;
        private UserDetailsBean user_details;
        private ShopDataBean shop_data;
        private String order_delivery_type;
        private String order_delivery_time;
        private String order_delivery_date;
        private String order_delivery_status;
        private DeliveryAddressBean delivery_address;
        private String delivery_charge;
        private String payment_method;
        private String payment_status;
        private String order_status;
        private DeliveredByBean delivered_by;
        private String remarks;
        private String created_at;
        private List<OrderDataBean> order_data;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public UserDetailsBean getUser_details() {
            return user_details;
        }

        public void setUser_details(UserDetailsBean user_details) {
            this.user_details = user_details;
        }

        public ShopDataBean getShop_data() {
            return shop_data;
        }

        public void setShop_data(ShopDataBean shop_data) {
            this.shop_data = shop_data;
        }

        public String getOrder_delivery_type() {
            return order_delivery_type;
        }

        public void setOrder_delivery_type(String order_delivery_type) {
            this.order_delivery_type = order_delivery_type;
        }

        public String getOrder_delivery_time() {
            return order_delivery_time;
        }

        public void setOrder_delivery_time(String order_delivery_time) {
            this.order_delivery_time = order_delivery_time;
        }

        public String getOrder_delivery_date() {
            return order_delivery_date;
        }

        public void setOrder_delivery_date(String order_delivery_date) {
            this.order_delivery_date = order_delivery_date;
        }

        public String getOrder_delivery_status() {
            return order_delivery_status;
        }

        public void setOrder_delivery_status(String order_delivery_status) {
            this.order_delivery_status = order_delivery_status;
        }

        public DeliveryAddressBean getDelivery_address() {
            return delivery_address;
        }

        public void setDelivery_address(DeliveryAddressBean delivery_address) {
            this.delivery_address = delivery_address;
        }

        public String getDelivery_charge() {
            return delivery_charge;
        }

        public void setDelivery_charge(String delivery_charge) {
            this.delivery_charge = delivery_charge;
        }

        public String getPayment_method() {
            return payment_method;
        }

        public void setPayment_method(String payment_method) {
            this.payment_method = payment_method;
        }

        public String getPayment_status() {
            return payment_status;
        }

        public void setPayment_status(String payment_status) {
            this.payment_status = payment_status;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public DeliveredByBean getDelivered_by() {
            return delivered_by;
        }

        public void setDelivered_by(DeliveredByBean delivered_by) {
            this.delivered_by = delivered_by;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public List<OrderDataBean> getOrder_data() {
            return order_data;
        }

        public void setOrder_data(List<OrderDataBean> order_data) {
            this.order_data = order_data;
        }

        public static class UserDetailsBean implements Serializable {
            /**
             * id : 234
             * email : joyal@gmail.com
             * first_name : joyal
             * last_name :
             * phone : 9061243470
             * gender :
             * profile_pic : https://dev.sicsapp.com/erge/uploads/profile/default.png
             * social_profile_pic :
             */

            private String id;
            private String email;
            private String first_name;
            private String last_name;
            private String phone;
            private String gender;
            private String profile_pic;
            private String social_profile_pic;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getFirst_name() {
                return first_name;
            }

            public void setFirst_name(String first_name) {
                this.first_name = first_name;
            }

            public String getLast_name() {
                return last_name;
            }

            public void setLast_name(String last_name) {
                this.last_name = last_name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getProfile_pic() {
                return profile_pic;
            }

            public void setProfile_pic(String profile_pic) {
                this.profile_pic = profile_pic;
            }

            public String getSocial_profile_pic() {
                return social_profile_pic;
            }

            public void setSocial_profile_pic(String social_profile_pic) {
                this.social_profile_pic = social_profile_pic;
            }
        }

        public static class ShopDataBean implements Serializable {
            /**
             * shop_id : 123456
             * profile_pic :
             * shop_name : Sample
             * shop_address :
             * shop_details : null
             * shop_lat : 8.571
             * shop_long : 76.8663
             */

            private String shop_id;
            private String profile_pic;
            private String shop_name;
            private String shop_address;
            private Object shop_details;
            private String shop_lat;
            private String shop_long;

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String getProfile_pic() {
                return profile_pic;
            }

            public void setProfile_pic(String profile_pic) {
                this.profile_pic = profile_pic;
            }

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public String getShop_address() {
                return shop_address;
            }

            public void setShop_address(String shop_address) {
                this.shop_address = shop_address;
            }

            public Object getShop_details() {
                return shop_details;
            }

            public void setShop_details(Object shop_details) {
                this.shop_details = shop_details;
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
        }

        public static class DeliveryAddressBean implements Serializable {
            /**
             * id : 801
             * order_id : WVUK1B1355
             * location : Los Angeles,California,United States,90007
             * lat : 34.0256262
             * long : -118.28504399999998
             * address : USC Village,South Hoover Street
             * remarks : null
             * created_at : 2020-03-04 17:09:32
             * updated_at : 2020-03-04 17:09:32
             */

            private String id;
            private String order_id;
            private String location;
            private String lat;
            @SerializedName("long")
            private String longX;
            private String address;
            private Object remarks;
            private String created_at;
            private String updated_at;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
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

            public Object getRemarks() {
                return remarks;
            }

            public void setRemarks(Object remarks) {
                this.remarks = remarks;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }
        }

        public static class DeliveredByBean implements Serializable {
            /**
             * id :
             * email :
             * first_name :
             * last_name :
             * phone :
             * gender :
             * profile_pic :
             * social_profile_pic :
             * user_id :
             * insurance_doc :
             * license_doc :
             * pca_doc :
             * vehicle_no :
             * vehicle_type_name :
             * vehicle_type_id :
             * is_online :
             * driver_stars :
             */

            private String id;
            private String email;
            private String first_name;
            private String last_name;
            private String phone;
            private String gender;
            private String profile_pic;
            private String social_profile_pic;
            private String user_id;
            private String insurance_doc;
            private String license_doc;
            private String pca_doc;
            private String vehicle_no;
            private String vehicle_type_name;
            private String vehicle_type_id;
            private String is_online;
            private String driver_stars;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getFirst_name() {
                return first_name;
            }

            public void setFirst_name(String first_name) {
                this.first_name = first_name;
            }

            public String getLast_name() {
                return last_name;
            }

            public void setLast_name(String last_name) {
                this.last_name = last_name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getProfile_pic() {
                return profile_pic;
            }

            public void setProfile_pic(String profile_pic) {
                this.profile_pic = profile_pic;
            }

            public String getSocial_profile_pic() {
                return social_profile_pic;
            }

            public void setSocial_profile_pic(String social_profile_pic) {
                this.social_profile_pic = social_profile_pic;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getInsurance_doc() {
                return insurance_doc;
            }

            public void setInsurance_doc(String insurance_doc) {
                this.insurance_doc = insurance_doc;
            }

            public String getLicense_doc() {
                return license_doc;
            }

            public void setLicense_doc(String license_doc) {
                this.license_doc = license_doc;
            }

            public String getPca_doc() {
                return pca_doc;
            }

            public void setPca_doc(String pca_doc) {
                this.pca_doc = pca_doc;
            }

            public String getVehicle_no() {
                return vehicle_no;
            }

            public void setVehicle_no(String vehicle_no) {
                this.vehicle_no = vehicle_no;
            }

            public String getVehicle_type_name() {
                return vehicle_type_name;
            }

            public void setVehicle_type_name(String vehicle_type_name) {
                this.vehicle_type_name = vehicle_type_name;
            }

            public String getVehicle_type_id() {
                return vehicle_type_id;
            }

            public void setVehicle_type_id(String vehicle_type_id) {
                this.vehicle_type_id = vehicle_type_id;
            }

            public String getIs_online() {
                return is_online;
            }

            public void setIs_online(String is_online) {
                this.is_online = is_online;
            }

            public String getDriver_stars() {
                return driver_stars;
            }

            public void setDriver_stars(String driver_stars) {
                this.driver_stars = driver_stars;
            }
        }

        public static class OrderDataBean implements Serializable {
            /**
             * id : 1037
             * order_id : WVUK1B1355
             * product_id :
             * product_parent_id :
             * name :
             * price : 7.5
             * sale_price : 5.5
             * short_description :
             * long_description :
             * medium_image :
             * model_number :
             * sellerInfo :
             * customer_rating :
             * num_reviews :
             * quantity : 1
             * stock :
             * verify_images : []
             */

            private String id;
            private String order_id;
            private String product_id;
            private String product_parent_id;
            private String name;
            private String price;
            private String sale_price;
            private String short_description;
            private String long_description;
            private String medium_image;
            private String model_number;
            private String sellerInfo;
            private String customer_rating;
            private String num_reviews;
            private String quantity;
            private String stock;
            private List<?> verify_images;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getProduct_parent_id() {
                return product_parent_id;
            }

            public void setProduct_parent_id(String product_parent_id) {
                this.product_parent_id = product_parent_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getSale_price() {
                return sale_price;
            }

            public void setSale_price(String sale_price) {
                this.sale_price = sale_price;
            }

            public String getShort_description() {
                return short_description;
            }

            public void setShort_description(String short_description) {
                this.short_description = short_description;
            }

            public String getLong_description() {
                return long_description;
            }

            public void setLong_description(String long_description) {
                this.long_description = long_description;
            }

            public String getMedium_image() {
                return medium_image;
            }

            public void setMedium_image(String medium_image) {
                this.medium_image = medium_image;
            }

            public String getModel_number() {
                return model_number;
            }

            public void setModel_number(String model_number) {
                this.model_number = model_number;
            }

            public String getSellerInfo() {
                return sellerInfo;
            }

            public void setSellerInfo(String sellerInfo) {
                this.sellerInfo = sellerInfo;
            }

            public String getCustomer_rating() {
                return customer_rating;
            }

            public void setCustomer_rating(String customer_rating) {
                this.customer_rating = customer_rating;
            }

            public String getNum_reviews() {
                return num_reviews;
            }

            public void setNum_reviews(String num_reviews) {
                this.num_reviews = num_reviews;
            }

            public String getQuantity() {
                return quantity;
            }

            public void setQuantity(String quantity) {
                this.quantity = quantity;
            }

            public String getStock() {
                return stock;
            }

            public void setStock(String stock) {
                this.stock = stock;
            }

            public List<?> getVerify_images() {
                return verify_images;
            }

            public void setVerify_images(List<?> verify_images) {
                this.verify_images = verify_images;
            }
        }
    }

    public static class DeliverdBean implements Serializable {
        /**
         * order_id : WVUK1B1355
         * user_details : {"id":"234","email":"joyal@gmail.com","first_name":"joyal","last_name":"","phone":"9061243470","gender":" ","profile_pic":"https://dev.sicsapp.com/erge/uploads/profile/default.png","social_profile_pic":""}
         * shop_data : {"shop_id":"123456","profile_pic":"","shop_name":"Sample","shop_address":"","shop_details":null,"shop_lat":"8.571","shop_long":"76.8663"}
         * order_delivery_type : now
         * order_delivery_time : 11:18:07
         * order_delivery_date : 2020-03-11
         * order_delivery_status : Order ready to pickedup by driver
         * delivery_address : {"id":"801","order_id":"WVUK1B1355","location":"Los Angeles,California,United States,90007","lat":"34.0256262","long":"-118.28504399999998","address":"USC Village,South Hoover Street","remarks":null,"created_at":"2020-03-04 17:09:32","updated_at":"2020-03-04 17:09:32"}
         * delivery_charge :
         * payment_method : cod
         * payment_status : pending
         * order_status : ready
         * order_data : [{"id":"1037","order_id":"WVUK1B1355","product_id":"","product_parent_id":"","name":"","price":"7.5","sale_price":"5.5","short_description":"","long_description":"","medium_image":"","model_number":"","sellerInfo":"","customer_rating":"","num_reviews":"","quantity":"1","stock":"","verify_images":[]}]
         * delivered_by : {"id":"","email":"","first_name":"","last_name":"","phone":"","gender":"","profile_pic":"","social_profile_pic":"","user_id":"","insurance_doc":"","license_doc":"","pca_doc":"","vehicle_no":"","vehicle_type_name":"","vehicle_type_id":"","is_online":"","driver_stars":""}
         * remarks : nothing
         * created_at : 2020-03-11 11:18:07
         */

        private String order_id;
        private UserDetailsBeanX user_details;
        private ShopDataBeanX shop_data;
        private String order_delivery_type;
        private String order_delivery_time;
        private String order_delivery_date;
        private String order_delivery_status;
        private DeliveryAddressBeanX delivery_address;
        private String delivery_charge;
        private String payment_method;
        private String payment_status;
        private String order_status;
        private DeliveredByBeanX delivered_by;
        private String remarks;
        private String created_at;
        private List<OrderDataBeanX> order_data;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public UserDetailsBeanX getUser_details() {
            return user_details;
        }

        public void setUser_details(UserDetailsBeanX user_details) {
            this.user_details = user_details;
        }

        public ShopDataBeanX getShop_data() {
            return shop_data;
        }

        public void setShop_data(ShopDataBeanX shop_data) {
            this.shop_data = shop_data;
        }

        public String getOrder_delivery_type() {
            return order_delivery_type;
        }

        public void setOrder_delivery_type(String order_delivery_type) {
            this.order_delivery_type = order_delivery_type;
        }

        public String getOrder_delivery_time() {
            return order_delivery_time;
        }

        public void setOrder_delivery_time(String order_delivery_time) {
            this.order_delivery_time = order_delivery_time;
        }

        public String getOrder_delivery_date() {
            return order_delivery_date;
        }

        public void setOrder_delivery_date(String order_delivery_date) {
            this.order_delivery_date = order_delivery_date;
        }

        public String getOrder_delivery_status() {
            return order_delivery_status;
        }

        public void setOrder_delivery_status(String order_delivery_status) {
            this.order_delivery_status = order_delivery_status;
        }

        public DeliveryAddressBeanX getDelivery_address() {
            return delivery_address;
        }

        public void setDelivery_address(DeliveryAddressBeanX delivery_address) {
            this.delivery_address = delivery_address;
        }

        public String getDelivery_charge() {
            return delivery_charge;
        }

        public void setDelivery_charge(String delivery_charge) {
            this.delivery_charge = delivery_charge;
        }

        public String getPayment_method() {
            return payment_method;
        }

        public void setPayment_method(String payment_method) {
            this.payment_method = payment_method;
        }

        public String getPayment_status() {
            return payment_status;
        }

        public void setPayment_status(String payment_status) {
            this.payment_status = payment_status;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public DeliveredByBeanX getDelivered_by() {
            return delivered_by;
        }

        public void setDelivered_by(DeliveredByBeanX delivered_by) {
            this.delivered_by = delivered_by;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public List<OrderDataBeanX> getOrder_data() {
            return order_data;
        }

        public void setOrder_data(List<OrderDataBeanX> order_data) {
            this.order_data = order_data;
        }

        public static class UserDetailsBeanX implements Serializable {
            /**
             * id : 234
             * email : joyal@gmail.com
             * first_name : joyal
             * last_name :
             * phone : 9061243470
             * gender :
             * profile_pic : https://dev.sicsapp.com/erge/uploads/profile/default.png
             * social_profile_pic :
             */

            private String id;
            private String email;
            private String first_name;
            private String last_name;
            private String phone;
            private String gender;
            private String profile_pic;
            private String social_profile_pic;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getFirst_name() {
                return first_name;
            }

            public void setFirst_name(String first_name) {
                this.first_name = first_name;
            }

            public String getLast_name() {
                return last_name;
            }

            public void setLast_name(String last_name) {
                this.last_name = last_name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getProfile_pic() {
                return profile_pic;
            }

            public void setProfile_pic(String profile_pic) {
                this.profile_pic = profile_pic;
            }

            public String getSocial_profile_pic() {
                return social_profile_pic;
            }

            public void setSocial_profile_pic(String social_profile_pic) {
                this.social_profile_pic = social_profile_pic;
            }
        }

        public static class ShopDataBeanX implements Serializable {
            /**
             * shop_id : 123456
             * profile_pic :
             * shop_name : Sample
             * shop_address :
             * shop_details : null
             * shop_lat : 8.571
             * shop_long : 76.8663
             */

            private String shop_id;
            private String profile_pic;
            private String shop_name;
            private String shop_address;
            private Object shop_details;
            private String shop_lat;
            private String shop_long;

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String getProfile_pic() {
                return profile_pic;
            }

            public void setProfile_pic(String profile_pic) {
                this.profile_pic = profile_pic;
            }

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public String getShop_address() {
                return shop_address;
            }

            public void setShop_address(String shop_address) {
                this.shop_address = shop_address;
            }

            public Object getShop_details() {
                return shop_details;
            }

            public void setShop_details(Object shop_details) {
                this.shop_details = shop_details;
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
        }

        public static class DeliveryAddressBeanX implements Serializable {
            /**
             * id : 801
             * order_id : WVUK1B1355
             * location : Los Angeles,California,United States,90007
             * lat : 34.0256262
             * long : -118.28504399999998
             * address : USC Village,South Hoover Street
             * remarks : null
             * created_at : 2020-03-04 17:09:32
             * updated_at : 2020-03-04 17:09:32
             */

            private String id;
            private String order_id;
            private String location;
            private String lat;
            @SerializedName("long")
            private String longX;
            private String address;
            private Object remarks;
            private String created_at;
            private String updated_at;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
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

            public Object getRemarks() {
                return remarks;
            }

            public void setRemarks(Object remarks) {
                this.remarks = remarks;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }
        }

        public static class DeliveredByBeanX implements Serializable {
            /**
             * id :
             * email :
             * first_name :
             * last_name :
             * phone :
             * gender :
             * profile_pic :
             * social_profile_pic :
             * user_id :
             * insurance_doc :
             * license_doc :
             * pca_doc :
             * vehicle_no :
             * vehicle_type_name :
             * vehicle_type_id :
             * is_online :
             * driver_stars :
             */

            private String id;
            private String email;
            private String first_name;
            private String last_name;
            private String phone;
            private String gender;
            private String profile_pic;
            private String social_profile_pic;
            private String user_id;
            private String insurance_doc;
            private String license_doc;
            private String pca_doc;
            private String vehicle_no;
            private String vehicle_type_name;
            private String vehicle_type_id;
            private String is_online;
            private String driver_stars;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getFirst_name() {
                return first_name;
            }

            public void setFirst_name(String first_name) {
                this.first_name = first_name;
            }

            public String getLast_name() {
                return last_name;
            }

            public void setLast_name(String last_name) {
                this.last_name = last_name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getProfile_pic() {
                return profile_pic;
            }

            public void setProfile_pic(String profile_pic) {
                this.profile_pic = profile_pic;
            }

            public String getSocial_profile_pic() {
                return social_profile_pic;
            }

            public void setSocial_profile_pic(String social_profile_pic) {
                this.social_profile_pic = social_profile_pic;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getInsurance_doc() {
                return insurance_doc;
            }

            public void setInsurance_doc(String insurance_doc) {
                this.insurance_doc = insurance_doc;
            }

            public String getLicense_doc() {
                return license_doc;
            }

            public void setLicense_doc(String license_doc) {
                this.license_doc = license_doc;
            }

            public String getPca_doc() {
                return pca_doc;
            }

            public void setPca_doc(String pca_doc) {
                this.pca_doc = pca_doc;
            }

            public String getVehicle_no() {
                return vehicle_no;
            }

            public void setVehicle_no(String vehicle_no) {
                this.vehicle_no = vehicle_no;
            }

            public String getVehicle_type_name() {
                return vehicle_type_name;
            }

            public void setVehicle_type_name(String vehicle_type_name) {
                this.vehicle_type_name = vehicle_type_name;
            }

            public String getVehicle_type_id() {
                return vehicle_type_id;
            }

            public void setVehicle_type_id(String vehicle_type_id) {
                this.vehicle_type_id = vehicle_type_id;
            }

            public String getIs_online() {
                return is_online;
            }

            public void setIs_online(String is_online) {
                this.is_online = is_online;
            }

            public String getDriver_stars() {
                return driver_stars;
            }

            public void setDriver_stars(String driver_stars) {
                this.driver_stars = driver_stars;
            }
        }

        public static class OrderDataBeanX implements Serializable {
            /**
             * id : 1037
             * order_id : WVUK1B1355
             * product_id :
             * product_parent_id :
             * name :
             * price : 7.5
             * sale_price : 5.5
             * short_description :
             * long_description :
             * medium_image :
             * model_number :
             * sellerInfo :
             * customer_rating :
             * num_reviews :
             * quantity : 1
             * stock :
             * verify_images : []
             */

            private String id;
            private String order_id;
            private String product_id;
            private String product_parent_id;
            private String name;
            private String price;
            private String sale_price;
            private String short_description;
            private String long_description;
            private String medium_image;
            private String model_number;
            private String sellerInfo;
            private String customer_rating;
            private String num_reviews;
            private String quantity;
            private String stock;
            private List<?> verify_images;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getProduct_parent_id() {
                return product_parent_id;
            }

            public void setProduct_parent_id(String product_parent_id) {
                this.product_parent_id = product_parent_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getSale_price() {
                return sale_price;
            }

            public void setSale_price(String sale_price) {
                this.sale_price = sale_price;
            }

            public String getShort_description() {
                return short_description;
            }

            public void setShort_description(String short_description) {
                this.short_description = short_description;
            }

            public String getLong_description() {
                return long_description;
            }

            public void setLong_description(String long_description) {
                this.long_description = long_description;
            }

            public String getMedium_image() {
                return medium_image;
            }

            public void setMedium_image(String medium_image) {
                this.medium_image = medium_image;
            }

            public String getModel_number() {
                return model_number;
            }

            public void setModel_number(String model_number) {
                this.model_number = model_number;
            }

            public String getSellerInfo() {
                return sellerInfo;
            }

            public void setSellerInfo(String sellerInfo) {
                this.sellerInfo = sellerInfo;
            }

            public String getCustomer_rating() {
                return customer_rating;
            }

            public void setCustomer_rating(String customer_rating) {
                this.customer_rating = customer_rating;
            }

            public String getNum_reviews() {
                return num_reviews;
            }

            public void setNum_reviews(String num_reviews) {
                this.num_reviews = num_reviews;
            }

            public String getQuantity() {
                return quantity;
            }

            public void setQuantity(String quantity) {
                this.quantity = quantity;
            }

            public String getStock() {
                return stock;
            }

            public void setStock(String stock) {
                this.stock = stock;
            }

            public List<?> getVerify_images() {
                return verify_images;
            }

            public void setVerify_images(List<?> verify_images) {
                this.verify_images = verify_images;
            }
        }
    }
}
