package com.lvmoney.common.exception;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class FrameRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer code;

    public FrameRuntimeException(Integer code) {
        super("系统错误");
        this.code = 450;
    }

    public FrameRuntimeException(String message) {
        super(message);
        this.code = 433;
    }

    public FrameRuntimeException(Integer code, String message) {
        super(code + ":" + message);
        this.code = code;
    }

    public FrameRuntimeException(Throwable cause) {
        super("系统错误", cause);
        this.code = 433;
    }

    public FrameRuntimeException(String message, Throwable cause) {
        super(message, cause);
        this.code = 433;
    }

    public FrameRuntimeException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
