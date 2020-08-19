package cn.pretty.core.util;

/**
 * @author licong
 * @version 1.0
 * @date 2020/8/19 15:12
 */
public class CharUtil {
    public static final char SPACE = ' ';

    public static final char TAB = ' ';

    public static final char DOT = '.';

    public static final char SLASH = '/';

    public static final char BACKSLASH = '\\';

    public static final char CR = '\r';

    public static final char LF = '\n';

    public static final char UNDERLINE = '_';

    public static final char DASHED = '-';

    public static final char COMMA = ',';

    public static final char DELIM_START = '{';

    public static final char DELIM_END = '}';

    public static final char BRACKET_START='[';

    public static final char BRACKET_END = ']';

    public static final char COLON = ':';

    public static final char DOUBLE_QUOTES = '"';

    public static final char SINGLE_QUOTE = '\'';

    public static final char AMP = '&';

    /**
     * 是否空白符<br>
     * 空白符包括空格、制表符、全角空格和不间断空格<br>
     *
     * @param c 字符
     * @return 是否空白符
     * @see Character#isWhitespace(int)
     * @see Character#isSpaceChar(int)
     * @since 4.0.10
     */
    public static boolean isBlankChar(int c) {
        return Character.isWhitespace(c)
                || Character.isSpaceChar(c)
                || c == '\ufeff'
                || c == '\u202a';
    }

    /**
     * 是否空白符<br>
     * 空白符包括空格、制表符、全角空格和不间断空格<br>
     *
     * @param c 字符
     * @return 是否空白符
     * @see Character#isWhitespace(int)
     * @see Character#isSpaceChar(int)
     * @since 4.0.10
     */
    public static boolean isBlankChar(char c) {
        return isBlankChar((int) c);
    }

}
