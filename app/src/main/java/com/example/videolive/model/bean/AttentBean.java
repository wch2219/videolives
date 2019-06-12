package com.example.videolive.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AttentBean extends BaseResult {

    /**
     * data : {"code":0,"msg":"","info":[{"isattent":"1"}]}
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
         * msg :
         * info : [{"isattent":"1"}]
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
             * isattent : 1
             */

            private String isattent;
            private String islike;
            private String likes;

            public String getIsattent() {
                return isattent;
            }

            public void setIsattent(String isattent) {
                this.isattent = isattent;
            }

            public String getIslike() {
                return islike;
            }

            public void setIslike(String islike) {
                this.islike = islike;
            }

            public String getLikes() {
                return likes;
            }

            public void setLikes(String likes) {
                this.likes = likes;
            }
        }
    }
}
