package com.example.erge.ApiModel;

public class UpdateProfilePicModel {
    /**
     * status : true
     * code : 200
     * message : success
     * data : {"id":"234","email":"joyal@gmail.com","first_name":"Jo","last_name":"","phone":"9061243470","gender":" ","profile_pic":"https://dev.sicsapp.com/erge/uploads/profile/20bdc2bb287a1ac2cc06d5fd71c3e03f.png","social_profile_pic":""}
     */

    private boolean status;
    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 234
         * email : joyal@gmail.com
         * first_name : Jo
         * last_name :
         * phone : 9061243470
         * gender :
         * profile_pic : https://dev.sicsapp.com/erge/uploads/profile/20bdc2bb287a1ac2cc06d5fd71c3e03f.png
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
}
