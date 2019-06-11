package com.example.videolive.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegisterBean extends BaseResult {


    /**
     * data : {"code":0,"info":[],"msg":"注册成功"}
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
         * info : []
         * msg : 注册成功
         */

        private int code;
        @SerializedName("msg")
        private String msgX;
        private List<?> info;

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

        public List<?> getInfo() {
            return info;
        }

        public void setInfo(List<?> info) {
            this.info = info;
        }
    }
}
