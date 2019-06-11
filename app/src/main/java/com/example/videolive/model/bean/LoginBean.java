package com.example.videolive.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginBean extends BaseResult {

    /**
     * data : {"code":0,"info":[{"avatar":"http://www.tlimit.to/default.jpg","avatar_thumb":"http://www.tlimit.to/default_thumb.jpg","birthday":"","city":"","coin":"200000","consumption":"0","id":"24205","isagent":"1","isreg":"1","level":"1","level_anchor":"1","login_type":"phone","province":"","sex":"2","signature":"这家伙很懒，什么都没留下","token":"379489025686de1e0c2f612e00ba80d7","user_nicename":"手机用户1978","votestotal":"0"}],"msg":""}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * code : 0
         * info : [{"avatar":"http://www.tlimit.to/default.jpg","avatar_thumb":"http://www.tlimit.to/default_thumb.jpg","birthday":"","city":"","coin":"200000","consumption":"0","id":"24205","isagent":"1","isreg":"1","level":"1","level_anchor":"1","login_type":"phone","province":"","sex":"2","signature":"这家伙很懒，什么都没留下","token":"379489025686de1e0c2f612e00ba80d7","user_nicename":"手机用户1978","votestotal":"0"}]
         * msg :
         */

        private int code;
        @SerializedName("msg")
        private String msgX;
        private List<InfoBean> info;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsgX() {
            return msgX;
        }

        public void setMsgX(String msgX) {
            this.msgX = msgX;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * avatar : http://www.tlimit.to/default.jpg
             * avatar_thumb : http://www.tlimit.to/default_thumb.jpg
             * birthday :
             * city :
             * coin : 200000
             * consumption : 0
             * id : 24205
             * isagent : 1
             * isreg : 1
             * level : 1
             * level_anchor : 1
             * login_type : phone
             * province :
             * sex : 2
             * signature : 这家伙很懒，什么都没留下
             * token : 379489025686de1e0c2f612e00ba80d7
             * user_nicename : 手机用户1978
             * votestotal : 0
             */

            private String avatar;
            private String avatar_thumb;
            private String birthday;
            private String city;
            private String coin;
            private String consumption;
            private String id;
            private String isagent;
            private String isreg;
            private String level;
            private String level_anchor;
            private String login_type;
            private String province;
            private String sex;
            private String signature;
            private String token;
            private String user_nicename;
            private String votestotal;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getAvatar_thumb() {
                return avatar_thumb;
            }

            public void setAvatar_thumb(String avatar_thumb) {
                this.avatar_thumb = avatar_thumb;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getCoin() {
                return coin;
            }

            public void setCoin(String coin) {
                this.coin = coin;
            }

            public String getConsumption() {
                return consumption;
            }

            public void setConsumption(String consumption) {
                this.consumption = consumption;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getIsagent() {
                return isagent;
            }

            public void setIsagent(String isagent) {
                this.isagent = isagent;
            }

            public String getIsreg() {
                return isreg;
            }

            public void setIsreg(String isreg) {
                this.isreg = isreg;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }

            public String getLevel_anchor() {
                return level_anchor;
            }

            public void setLevel_anchor(String level_anchor) {
                this.level_anchor = level_anchor;
            }

            public String getLogin_type() {
                return login_type;
            }

            public void setLogin_type(String login_type) {
                this.login_type = login_type;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getUser_nicename() {
                return user_nicename;
            }

            public void setUser_nicename(String user_nicename) {
                this.user_nicename = user_nicename;
            }

            public String getVotestotal() {
                return votestotal;
            }

            public void setVotestotal(String votestotal) {
                this.votestotal = votestotal;
            }
        }
    }
}
