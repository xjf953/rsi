package com.shawn.votesystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ResultMsg implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;

    private String msg;

    private Object data;

    public ResultMsg(){
        super();
    }


    public ResultMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultMsg(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess() {
        return 0 == this.code;
    }


    public static ResultMsg successMsg() {
        return new ResultMsg(0, "The operation succeeded!");
    }

    public static ResultMsg successMsg(String msg){
        return new ResultMsg(0,msg);
    }

    public static ResultMsg successMsg(String msg,Object data){
        return new ResultMsg(0,msg,data);
    }

    public static ResultMsg failMsg() {
        return new ResultMsg(1, "The operation failed!");
    }

    public static ResultMsg failMsg(String errorMsg) {
        return failMsg(1, errorMsg);
    }

    /** 扩展code错误码，只要不是0，都是失败 */
    public static ResultMsg failMsg(Integer code, String errorMsg) {
        return new ResultMsg(code, errorMsg);
    }
}
