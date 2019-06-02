package com.example.videolive.model.bean;


import io.reactivex.annotations.NonNull;
import org.jetbrains.annotations.NotNull;

public class BaseResult {
    public BaseResult() {
        super();
    }

    public BaseResult(int code, @NonNull String mess, @NotNull Object o) {
       this.code = code;
       this.mess = mess;
       this.object =o;
    }

    private int code;
    private String mess;
    private Object object;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
