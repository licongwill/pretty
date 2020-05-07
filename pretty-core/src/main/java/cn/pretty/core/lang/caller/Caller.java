package cn.pretty.core.lang.caller;

/**
 * @ProjectName: pretty
 * @ClassName: Caller
 * @Author: licong
 * @Description: 调用者接口,可以通过此接口的实现类方法
 * 获取调用者、多级调用者以及判断是否被调用
 * @Date: 2020/5/7 23:36
 * @Version: 1.0
 */
public interface Caller {
    /**
     * @Author:     licong
     * @Description:  TODO 获取调用者
     * @Date:    2020/5/7 23:46
     * @Version:    1.0
     */
    Class<?> getCaller();

    /**
     * @Author:     licong
     * @Description:  TODO 获取调用者的调用者
     * @Date:    2020/5/7 23:52
     * @Version:    1.0
     */
    Class<?> getCallerCaller();

    /**
     * 获得调用者，指定第几级调用者 调用者层级关系：
     * @Author:     licong
     * @Description:  TODO
     * @Params:     depth 层级 0表示本身，1表示调用的类，2表示调用者的调用者，依次类推
     * @Version:    1.0
     */
    Class<?> getCaller(int depth);

    /**
     * @Author:     licong
     * @Description:  TODO 是否被指定类调用
     * @Params:     clazz
     * @Version:    1.0
     */
    boolean isCallerBy(Class<?> clazz);
}
