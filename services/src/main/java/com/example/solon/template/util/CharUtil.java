package com.example.solon.template.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

/**
 * Title:字符工具类 Description:用于方便的获取或转换一些字符串值
 *
 * @author WangXuDong
 * @version 1.0
 */
public class CharUtil {
    private static final Logger log = LoggerFactory.getLogger(CharUtil.class);

    private static volatile Long localIdLastTime = System.currentTimeMillis();

    private static final String[] strs = new String[]{"a", "b", "c", "d", "e", "f", "g", "h",
            "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9"};

    private CharUtil() {
    }

    /**
     * 将String 转化成Integer 不能转化返回Null
     *
     * @param numStr
     */
    public static Integer stringParsInt(String numStr) {
        Integer numint;
        String numStrCopy = numStr;
        if (CharUtil.isEmpty(numStrCopy)) {
            numint = null;
        } else {
            try {
                if (numStrCopy.contains(".")) {
                    numStrCopy = numStrCopy.substring(0, numStrCopy.indexOf('.'));
                } else if (numStrCopy.indexOf('.') == 0) {
                    numStrCopy = "0";
                }
                numint = Integer.valueOf(numStrCopy);
            } catch (NumberFormatException e) {
                log.warn(e.getMessage());
                numint = null;
            }
        }
        return numint;
    }

    /**
     * 将String 转成Long 不能转返回null
     *
     * @param numStr
     */
    public static Long stringParsLong(String numStr) {
        Long num;
        if (CharUtil.isEmpty(numStr)) {
            num = null;
        } else {
            try {
                if (numStr.contains(".")) {
                    numStr = numStr.substring(0, numStr.indexOf('.'));
                } else if (numStr.indexOf('.') == 0) {
                    numStr = "0";
                }
                num = Long.valueOf(numStr);
            } catch (NumberFormatException e) {
                log.warn(e.getMessage());
                num = null;
            }
        }

        return num;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);

            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public static boolean isBlank(CharSequence str) {
        return isEmpty(str);
    }

    public static boolean isNotBlank(CharSequence str) {
        return !isBlank(str);
    }

    public static boolean isEmpty(CharSequence str) {
        return str == null || str.isEmpty();
    }

    public static boolean ifLength(String str, int length) {
        return str != null && !str.trim().isEmpty() && str.length() == length;
    }

    public static String removeZeroDecimal(String number) {
        if(number == null){
            return null;
        }
        if (number.contains(".")) {
            number = number.replaceAll("\\.?0*$", "");
        }
        return number;
    }

    /**
     * 获取一个字符串的实际长度，中文或中文符号每一个等于两个单位长度英文字母
     *
     * @param str
     * @return 一个字符串的实际长度
     */
    public static int stringLength(String str) {
        int length = 0;
        char[] arr = str.toCharArray();
        for (char c : arr) {
            if ((String.valueOf(c)).getBytes(StandardCharsets.UTF_8).length == 1) {
                length += 1;
            } else {
                length += 2;
            }
        }
        return length;
    }

    /**
     * 从开始截取相应长度的字符串，长度为实际长度，一个中文或中文符号占两个单位的长度
     * 字符串长度大于0
     *
     * @param str       字符串
     * @param maxLength 截取的最大长度,一个中文或中文符号占两个单位的长度
     * @return 新字符串
     */
    public static String subStringByte(String str, int maxLength) {
        StringBuilder nstr = new StringBuilder();
        if (str == null) {
            return null;
        }
        char[] arr = str.toCharArray();
        try {
            if (maxLength <= 0) {
                throw new Exception("字符串长度不能小于等于0,length:" + maxLength);
            }
            for (char c : arr) {
                if (maxLength > 0) {
                    if ((c + "").getBytes(StandardCharsets.UTF_8).length == 1) {
                        maxLength -= 1;
                    } else {
                        maxLength -= 2;
                    }
                    nstr.append(c);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return nstr.toString();

    }

    /**
     * 判断字符串是否是整数
     */
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是浮点数
     */
    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);

            return value.contains(".");
        } catch (NumberFormatException e) {

            return false;
        }
    }

    /**
     * 获取一个指定长度的随机数
     *
     * @param length 长度
     * @return 随机数字符串
     */
    public static String getRanNumber(int length) {
        if (length <= 0) {
            length = 1;
        }
        StringBuilder num = new StringBuilder();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < length; i++) {
            num.append(random.nextInt(i == 0 ? 1 : 0, 10));
        }
        return num.toString();
    }

    /**
     * 获取一个随机数在一定区域内包含 min 和 max
     *
     * @param min 最小数
     * @param max 最大数
     * @return 随机数
     */
    public static int getIntRanNumber(int min, int max) {
        if (min >= max) {
            return max;
        }
        return (int) (min + Math.random() * (max - min + 1));
    }

    /**
     * 获取十六进制的颜色代码.例如 "#6E36B4" , For HTML ,
     *
     * @return String
     */
    public static String getRandColorCode() {
        String r, g, b;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;

        return r + g + b;
    }

    /**
     * 随机生成指定长度的小写字母和数组组成的字符串
     *
     * @param length    字符串长度
     * @param allLetter 是否全是字母
     * @return 生成的字符串
     */
    public static String getRanString(int length, boolean allLetter) {

        StringBuilder retStr = new StringBuilder();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        if (allLetter) {

            for (int i = 0; i < length; i++) {
                int rad = random.nextInt(26);
                retStr.append(strs[rad]);
            }
        } else {
            for (int i = 0; i < length; i++) {
                int rad = random.nextInt(36);
                retStr.append(strs[rad]);
            }
        }
        return retStr.toString();
    }

    /**
     * 取两个字符之间的字符，返回一个字符串数组
     *
     * @param strSrc 源字符串
     * @param from   开始字符
     * @param to     结束字符
     */
    public static String[] getTokenString(String strSrc, String from, String to) {
        if (from == null || from.isEmpty()) {
            return null;
        }
        if (to == null || to.isEmpty()) {
            return null;
        }
        int nLast = strSrc.indexOf(from);
        if (nLast == -1) {
            return null;
        }
        int nNext;
        ArrayList<String> list = new ArrayList<String>();
        String str;
        while ((nNext = strSrc.indexOf(to, nLast + from.length())) != -1) {
            str = strSrc.substring(nLast + from.length(), nNext);
            list.add(str);
            nLast = nNext + to.length();
            nLast = strSrc.indexOf(from, nLast);
            if (nLast == -1) {
                break;
            }
        }
        if (list.size() == 0) {
            return null;
        }
        String[] s = new String[list.size()];
        list.toArray(s);

        return s;
    }

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }

    public static String formatCurrency(float val) {
        if (val == 0) {
            return "0.00";
        }
        DecimalFormat df = new DecimalFormat("0.00");

        return df.format(val);
    }


    /**
     * 数字转6位固定长度的字符，用于生产优惠码等
     *
     * @param num 将一个小于30^6-1的数字转换为长度为6的字符
     * @return 长度为6的唯一字符串
     */
    public static String numberToFixedLenCode(int num) {

        final char[] r = new char[]{'q', 'w', 'e', '8', 's', '2', 'd', 'z', 'x', '9', 'c', '7', 'p', '5', 'k', '3', 'm',
                'j', 'u', 'f', 'r', '4', 'v', 'y', 't', 'n', '6', 'b', 'g', 'h'};
        final char b = 'a';
        int binLen = r.length;
        int s = 6;

        char[] buf = new char[32];
        int charPos = 32;

        while ((num / binLen) > 0) {
            int ind = num % binLen;
            buf[--charPos] = r[ind];
            num /= binLen;
        }
        buf[--charPos] = r[num % binLen];
        String str = new String(buf, charPos, (32 - charPos));
        // 不够长度的自动随机补全
        if (str.length() < s) {
            StringBuilder sb = new StringBuilder();
            sb.append(b);
            ThreadLocalRandom rnd = ThreadLocalRandom.current();
            for (int i = 1; i < s - str.length(); i++) {
                sb.append(r[rnd.nextInt(binLen)]);
            }
            str += sb.toString();
        }

        return str.toUpperCase();
    }

    /**
     * 对numberToFixedLenCode生成的字符串进行解码
     *
     * @return ID
     */
    public static int numberToFixedLenCodeDecode(String code) {
        char[] r = new char[]{'q', 'w', 'e', '8', 's', '2', 'd', 'z', 'x', '9', 'c', '7', 'p', '5', 'k', '3', 'm', 'j',
                'u', 'f', 'r', '4', 'v', 'y', 't', 'n', '6', 'b', 'g', 'h'};
        char b = 'a';
        int binLen = r.length;
        int s = 6;
        code = code.toLowerCase();
        char[] chs = code.toCharArray();
        int res = 0;
        for (int i = 0; i < chs.length; i++) {
            int ind = 0;
            for (int j = 0; j < binLen; j++) {
                if (chs[i] == r[j]) {
                    ind = j;
                    break;
                }
            }
            if (chs[i] == b) {
                break;
            }
            if (i > 0) {
                res = res * binLen + ind;
            } else {
                res = ind;
            }
        }

        return res;
    }

    /**
     * 实际为为当前时间戳+自增键值,
     * 在同一个应用中不会出现重复,一般用于版本号
     * 前提是时间倒推，如果当前启动的时间小于上次启动的时间，则会出现问题。
     */
    public static synchronized String getLocalId() {
        long time = System.currentTimeMillis();
        if (localIdLastTime >= time) {
            time += (localIdLastTime - time + 1);
        }
        localIdLastTime = time;
        return Long.toString(time);
    }

    public static boolean isSql(String str) {
        if (isEmpty(str)) {
            return false;
        }
        String testStr = str.replaceAll("(\\r\\n|\\n|\\n\\r)", " ").replaceAll("(\\s+|\\s)", " ");
        return Pattern.compile("^(\\s)*(select)(\\s)+[\\S\\s]+(\\s)+(from)(\\s)+[\\S\\s]+$",
                Pattern.CASE_INSENSITIVE).matcher(testStr).find();
    }

    /**
     * 提取字符串中的数字
     *
     * @param str
     */
    public static String extractNumbers(String str) {
        try {
            return Pattern.compile("[^0-9]").matcher(str).replaceAll("").trim();
        } catch (Exception e) {

            log.error(e.getMessage(), e);
        }

        return null;
    }

}
