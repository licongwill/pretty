package cn.pretty.core.lang.caller;

import java.io.Serializable;

/**
 * @ProjectName: pretty
 * @ClassName: StackTraceCaller
 * @Author: licong
 * @Description: 通过StackTrace方式获取调用者。此方式效率最低，不推荐使用
 * @Date: 2020/5/8 0:13
 * @Version: 1.0
 */
public class StackTraceCaller implements Caller,Serializable {

    private static final long serialVersionUID = 8614777341889843492L;

    private static final int OFFSET = 2;

    @Override
    public Class<?> getCaller() {
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (OFFSET + 1 >= stackTrace.length) {
            return null;
        }
        final String className = stackTrace[OFFSET + 1].getClassName();
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Class<?> getCallerCaller() {
        return null;
    }

    @Override
    public Class<?> getCaller(int depth) {
        return null;
    }

    @Override
    public boolean isCallerBy(Class<?> clazz) {
        return false;
    }
}
