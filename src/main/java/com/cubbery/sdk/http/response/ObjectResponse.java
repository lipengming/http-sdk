/**
 * Copyright (c) 2015, cubbery.com. All rights reserved.
 */
package com.cubbery.sdk.http.response;

import com.cubbery.sdk.http.core.response.HttpResponse;
import com.google.gson.Gson;

/**
 * <b>项目名</b>： http-sdk <br>
 * <b>包名称</b>： com.cubbery.sdk.http.response <br>
 * <b>类名称</b>： ObjectResponse <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubbery</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/11/14 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class ObjectResponse<T> extends AbstractResponse<T> {

    private final Class<T> clazz;

    public ObjectResponse(Class<T> clazz, HttpResponse httpResponse) {
        super(httpResponse);
        this.clazz = clazz;
    }

    @Override
    public T content() {
        return new Gson().fromJson(httpResponse.getResponseBody(),clazz);
    }
}
