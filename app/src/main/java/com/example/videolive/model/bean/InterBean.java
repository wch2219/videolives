package com.example.videolive.model.bean;

import java.util.List;

public class InterBean {

    /**
     * ret : 200
     * data : {"code":700,"msg":"您的登陆状态失效，请重新登陆！","info":[]}
     * msg :
     */

    private int ret;
    private DataBean data;
    private String msg;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * code : 700
         * msg : 您的登陆状态失效，请重新登陆！
         * info : []
         */

        private int code;
        private String msg;
        private List<?> info;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public List<?> getInfo() {
            return info;
        }

        public void setInfo(List<?> info) {
            this.info = info;
        }
    }
}
