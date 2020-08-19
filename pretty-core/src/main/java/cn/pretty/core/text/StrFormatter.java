package cn.pretty.core.text;

import cn.pretty.core.util.ArrayUtil;
import cn.pretty.core.util.StrUtil;

/**
 * @author licong
 * @version 1.0
 * @date 2020/8/19 15:09
 */
public class StrFormatter {

    private static final int DEFAULT_SIZE = 80;

    public static String format(final String strPattern,final Object... argArray){
        if(StrUtil.isBlank(strPattern) || ArrayUtil.isEmpty(argArray)){
            return strPattern;
        }
        final int strPatternLength = strPattern.length();
        /*初始化定义长度*/
        StringBuilder sbuf = new StringBuilder(strPatternLength + DEFAULT_SIZE);
        /*记录处理的位置*/
        int handledPosition = 0;
        /*占位符所在位置*/
        int delimIndex;
        for(int argIndex = 0; argIndex < argArray.length; argIndex++){
            delimIndex = strPattern.indexOf(StrUtil.EMPTY_JSON, handledPosition);
            if(delimIndex == -1){
                /*不带占位符模板直接返回*/
                if(handledPosition == 0){
                    return strPattern;
                }
                sbuf.append(strPattern,handledPosition,strPatternLength);
            }
            /*转义符*/
            if(delimIndex > 0 && strPattern.charAt(delimIndex -1) == StrUtil.C_BACKSLASH){
                /*双转义符*/
                if(delimIndex > 1 && strPattern.charAt(delimIndex -2) == StrUtil.C_BACKSLASH){
                    sbuf.append(strPattern,handledPosition,delimIndex-1);
                    sbuf.append(StrUtil.utf8Str(argArray[argIndex]));
                    handledPosition = delimIndex + 2;
                }else {
                    // 占位符被转义
                    argIndex--;
                    sbuf.append(strPattern, handledPosition, delimIndex - 1);
                    sbuf.append(StrUtil.C_DELIM_START);
                    handledPosition = delimIndex + 1;
                }
            }else {// 正常占位符
                sbuf.append(strPattern, handledPosition, delimIndex);
                sbuf.append(StrUtil.utf8Str(argArray[argIndex]));
                handledPosition = delimIndex + 2;
            }
        }

        sbuf.append(strPattern, handledPosition, strPattern.length());

        return sbuf.toString();
    }
}
