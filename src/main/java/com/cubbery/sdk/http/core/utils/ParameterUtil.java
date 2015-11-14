/**
 * Copyright (c) 2015, cubbery.com. All rights reserved.
 */
package com.cubbery.sdk.http.core.utils;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

/**
 * <b>项目名</b>： http-sdk <br>
 * <b>包名称</b>： com.cubbery.sdk.http.core.utils <br>
 * <b>类名称</b>： ParameterUtil <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubbery</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/11/14 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public final class ParameterUtil {
    public static void addParameter(Map<String, Collection<String>> parameters,String key,String value) {
        if (parameters == null || StringUtils.isBlank(key)) {
            return;
        }
        String realVal = StringUtils.defaultString(value);
        Collection<String> exist = parameters.get(key);
        if (exist == null) {
            exist = new LinkedList<String>();
            exist.add(realVal);
            parameters.put(key, exist);
        } else {
            exist.add(realVal);
        }

    }

    public static void setParameter(Map<String, Collection<String>> parameters, String key,Collection<String> value) {
        if (parameters == null || StringUtils.isBlank(key)) {
            return;
        }
        parameters.put(key, value);
    }

}
