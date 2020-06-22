package cn.pretty.core.exceptions;

/**
 * TODO 工具类异常
 * @author licong
 * @version 1.0
 * @date 2020/5/12 11:26
 */
public class UtilException extends RuntimeException {
    private static final long serialVersionUID = 4910640487035555494L;

    public UtilException(String message){
        super(message);
    }

    public UtilException(String message,Throwable throwable){
        super(message,throwable);
    }
}
