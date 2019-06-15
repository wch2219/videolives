package com.example.videolive.model.bean;

import com.google.gson.annotations.SerializedName;

public class InvitationBean extends BaseResult {


    /**
     * data : {"code":0,"msg":"返回邀请码成功","info":{"user_agent_code":"YH9PAF"}}
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
         * msg : 返回邀请码成功
         * info : {"user_agent_code":"YH9PAF"}
         */

        private int code;
        @SerializedName("msg")
        private String msgX;
        private InfoBean info;

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

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * user_agent_code : YH9PAF
             */

            private String user_agent_code;

            public String getUser_agent_code() {
                return user_agent_code;
            }

            public void setUser_agent_code(String user_agent_code) {
                this.user_agent_code = user_agent_code;
            }
        }
    }
}
