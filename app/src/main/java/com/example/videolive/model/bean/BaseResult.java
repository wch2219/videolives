package com.example.videolive.model.bean;




public class BaseResult {

    /**
     * ret : 200
     * data : {"code":1001,"msg":"签名错误","info":[]}
     * msg :
     */

    private int ret;

    private String msg;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
