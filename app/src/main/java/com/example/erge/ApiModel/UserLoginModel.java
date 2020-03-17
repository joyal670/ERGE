package com.example.erge.ApiModel;

public class UserLoginModel {

    /**
     * status : true
     * code : 200
     * message : loged in successfully
     * user_data : {"id":"230","email":"jijin.sics@sicsapp.com","first_name":"sam","last_name":"","phone":"548974845145","gender":" ","profile_pic":"https://dev.sicsapp.com/erge/uploads/profile/default.png","social_profile_pic":"https://sasmple.com/url"}
     * user_token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6MjMwLCJlbWFpbCI6ImppamluLnNpY3NAc2ljc2FwcC5jb20iLCJmaXJzdF9uYW1lIjoic2FtIiwibGFzdF9uYW1lIjoiIiwiZ2VuZGVyIjoiICIsInByb2ZpbGVfcGljIjoiaHR0cHM6XC9cL2Rldi5zaWNzYXBwLmNvbVwvZXJnZVwvdXBsb2Fkc1wvcHJvZmlsZVwvZGVmYXVsdC5wbmciLCJ0b2tlbl9jcmVhdGVkX29uIjoiMjAyMC0wMi0xMyAxMTowNDo0NyIsInRva2VuIjoiOFVWQVdENENYSyJ9.0r-HTNHYnMRifm6b0K-FxcLuARtzEJkoJK7WjPtvM4s
     * is_push : 1
     */

    private boolean status;
    private int code;
    private String message;
    private UserDataBean user_data;
    private String user_token;
    private int is_push;

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

    public UserDataBean getUser_data() {
        return user_data;
    }

    public void setUser_data(UserDataBean user_data) {
        this.user_data = user_data;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public int getIs_push() {
        return is_push;
    }

    public void setIs_push(int is_push) {
        this.is_push = is_push;
    }

    public static class UserDataBean {
        /**
         * id : 230
         * email : jijin.sics@sicsapp.com
         * first_name : sam
         * last_name :
         * phone : 548974845145
         * gender :
         * profile_pic : https://dev.sicsapp.com/erge/uploads/profile/default.png
         * social_profile_pic : https://sasmple.com/url
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
