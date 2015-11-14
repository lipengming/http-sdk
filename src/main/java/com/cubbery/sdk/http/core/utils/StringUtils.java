/**
 * Copyright (c) 2015, cubbery.com. All rights reserved.
 */
package com.cubbery.sdk.http.core.utils;

import java.util.Iterator;

/**
 * <b>项目名</b>： http-sdk <br>
 * <b>包名称</b>： com.cubbery.sdk.http.core.utils <br>
 * <b>类名称</b>： StringUtils <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubbery</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/11/14 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class StringUtils {
    public static final String EMPTY = "";

    public static String defaultString(String str) {
        return str == null ? EMPTY : str;
    }

    public static String join(Iterable<?> iterable, String separator) {
        if (iterable == null) {
            return null;
        }
        return join(iterable.iterator(), separator);
    }

    public static String join(Iterator<?> iterator, String separator) {
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return EMPTY;
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return toString(first);
        }

        // Java default is 16, probably too small
        StringBuilder buf = new StringBuilder(256);
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            if (separator != null) {
                buf.append(separator);
            }
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static boolean isNotBlank(String cs) {
        return !isBlank(cs);
    }

    public static boolean isBlank(String cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }
}
