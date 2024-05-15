package com.example.solon.template.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @title: PlaceholderUtil
 * @author: trifolium.wang
 * @date: 2024/4/23
 */
public class PlaceholderUtil {

    private static final String REGEX_KEYWORD = ".$|()[]{}^?*+\\";

    /**
     * 从字符串中提取占位符参数
     *
     * @param str    被提取的字符串
     * @param prefix 占位符前缀
     * @param suffix 占位符后缀
     * @return 参数列表
     */
    public static List<String> extractParam(String str, CharSequence prefix, CharSequence suffix) {
        List<String> params = new ArrayList<>(5);
        if (CharUtil.isBlank(str)) {
            return params;
        }

        Pattern PARAM_REPLACE = prefixSuffixRegex(prefix, suffix);
        Matcher matcher = PARAM_REPLACE.matcher(str);
        while (matcher.find()) {
            String group = matcher.group();
            if (CharUtil.isNotBlank(prefix)) {
                group = group.substring(prefix.length());

            }
            if (CharUtil.isNotBlank(suffix)) {
                group = group.substring(0, group.length() - suffix.length());
            }
            params.add(group);
        }
        return params;
    }

    /**
     * 替换字符串中的参数
     *
     * @param str   字符串
     * @param param 参数列表
     */
    public static String replaceParam(String str, Map<String, String> param, CharSequence prefix, CharSequence suffix) {
        if (CharUtil.isBlank(str)) {
            return null;
        }
        if (param == null || param.isEmpty()) {
            return str;
        }
        Pattern PARAM_REPLACE = prefixSuffixRegex(prefix, suffix);
        Matcher matcher = PARAM_REPLACE.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String group = matcher.group();
            if (CharUtil.isNotBlank(prefix)) {
                group = group.substring(prefix.length());

            }
            if (CharUtil.isNotBlank(suffix)) {
                group = group.substring(0, group.length() - suffix.length());
            }

            String value = param.get(group);
            if (CharUtil.isNotBlank(value)) {
                matcher.appendReplacement(sb, value);
            } else {
                matcher.appendReplacement(sb, group);
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static Pattern prefixSuffixRegex(CharSequence prefix, CharSequence suffix) {
        String[] prefixChars = new String[0];
        String[] suffixChars = new String[0];
        if (CharUtil.isNotBlank(prefix)) {
            prefixChars = prefix.toString().split("");
            for (int i = 0; i < prefixChars.length; i++) {
                if (REGEX_KEYWORD.indexOf(prefixChars[i]) > 0) {
                    prefixChars[i] = "\\" + prefixChars[i];
                }
            }
        }
        if (CharUtil.isNotBlank(suffix)) {
            suffixChars = suffix.toString().split("");
            for (int i = 0; i < suffixChars.length; i++) {
                if (REGEX_KEYWORD.indexOf(suffixChars[i]) > 0) {
                    suffixChars[i] = "\\" + suffixChars[i];
                }
            }
        }
        return Pattern.compile("(" + join(prefixChars, "") + ")([\\w\\W]+?)(" + join(suffixChars, "") + ")");
    }

    private static String join(String[] strings, CharSequence delimiter) {
        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (String item : strings) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(delimiter);
            }
            sb.append(item);
        }
        return sb.toString();
    }
}
