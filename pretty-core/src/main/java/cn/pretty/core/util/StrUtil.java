package cn.pretty.core.util;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * @author licong
 * @version 1.0
 * @date 2020/8/19 15:10
 */
public class StrUtil {
    public static final int INDEX_NOT_FOUND = -1;

    public static final char C_SPACE = CharUtil.SPACE;

    public static final char C_TAB = CharUtil.TAB;

    public static final char C_DOT = CharUtil.DOT;

    public static final char C_SLASH = CharUtil.SLASH;

    public static final char C_BACKSLASH = CharUtil.BACKSLASH;

    public static final char C_CR = CharUtil.CR;

    public static final char C_LF = CharUtil.LF;

    public static final char C_UNDERLINE = CharUtil.UNDERLINE;

    public static final char C_COMMA = CharUtil.COMMA;

    public static final char C_DELIM_START = CharUtil.DELIM_START;

    public static final char C_DELIM_END = CharUtil.DELIM_END;

    public static final char C_BRACKET_START = CharUtil.BRACKET_START;

    public static final char C_BRACKET_END = CharUtil.BRACKET_END;

    public static final char C_COLON = CharUtil.COLON;

    public static final String SPACE = " ";

    public static final String TAB = "  ";

    public static final String DOT = ".";

    public static final String DOUBLE_DOT = "..";

    public static final String SLASH = "/";

    public static final String BACKSLASH = "\\";

    public static final String EMPTY = "";

    public static final String NULL = "null";

    public static final String CR = "\r";

    public static final String LF = "\n";

    public static final String CRLF = "\r\n";

    public static final String UNDERLINE = "_";

    public static final String DASHED = "-";

    public static final String COMMA = ",";

    public static final String DELIM_START = "{";

    public static final String DELIM_END = "}";

    public static final String BRACKET_START = "[";

    public static final String BRACKET_END = "]";

    public static final String COLON = ":";

    public static final String HTML_NBSP = "&nbsp;";

    public static final String HTML_AMP = "&amp;";

    public static final String HTML_QUOTE = "&quot;";

    public static final String HTML_APOS = "&apos;";

    public static final String HTML_LT = "&lt;";

    public static final String HTML_GT = "&gt;";

    public static final String EMPTY_JSON = "{}";

    public static boolean isBlank(CharSequence str) {
        int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            // 只要有一个非空字符即为非空字符串
            if (false == CharUtil.isBlankChar(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 将对象转为字符串<br>
     *
     * <pre>
     * 1、Byte数组和ByteBuffer会被转换为对应字符串的数组
     * 2、对象数组会调用Arrays.toString方法
     * </pre>
     *
     * @param obj 对象
     * @return 字符串
     */
    public static String utf8Str(Object obj) {
        return str(obj, CharsetUtil.CHARSET_UTF_8);
    }


    /**
     * 将对象转为字符串
     * <pre>
     * 	 1、Byte数组和ByteBuffer会被转换为对应字符串的数组
     * 	 2、对象数组会调用Arrays.toString方法
     * </pre>
     *
     * @param obj     对象
     * @param charset 字符集
     * @return 字符串
     */
    public static String str(Object obj, Charset charset) {
        if (null == obj) {
            return null;
        }

        if (obj instanceof String) {
            return (String) obj;
        } else if (obj instanceof byte[]) {
            return str((byte[]) obj, charset);
        } else if (obj instanceof Byte[]) {
            return str((Byte[]) obj, charset);
        } else if (obj instanceof ByteBuffer) {
            return str((ByteBuffer) obj, charset);
        } else if (ArrayUtil.isArray(obj)) {
            return ArrayUtil.toString(obj);
        }

        return obj.toString();
    }

    /**
     * 将编码的byteBuffer数据转换为字符串
     *
     * @param data    数据
     * @param charset 字符集，如果为空使用当前系统字符集
     * @return 字符串
     */
    public static String str(ByteBuffer data, Charset charset) {
        if (null == charset) {
            charset = Charset.defaultCharset();
        }
        return charset.decode(data).toString();
    }

    /**
     * 解码字节码
     *
     * @param data    字符串
     * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
     * @return 解码后的字符串
     */
    public static String str(Byte[] data, Charset charset) {
        if (data == null) {
            return null;
        }

        byte[] bytes = new byte[data.length];
        Byte dataByte;
        for (int i = 0; i < data.length; i++) {
            dataByte = data[i];
            bytes[i] = (null == dataByte) ? -1 : dataByte;
        }

        return str(bytes, charset);
    }

    /**
     * 解码字节码
     *
     * @param data    字符串
     * @param charset 字符集，如果此字段为空，则解码的结果取决于平台
     * @return 解码后的字符串
     */
    public static String str(byte[] data, Charset charset) {
        if (data == null) {
            return null;
        }

        if (null == charset) {
            return new String(data);
        }
        return new String(data, charset);
    }
}
