/**
 * Copyright (c) 2015, cubbery.com. All rights reserved.
 */
package com.cubbery.sdk.http.response;

import com.cubbery.sdk.http.Response;
import com.cubbery.sdk.http.core.HttpClient;
import com.cubbery.sdk.http.core.response.HttpResponse;

/**
 * <b>项目名</b>： http-sdk <br>
 * <b>包名称</b>： com.cubbery.sdk.http.response <br>
 * <b>类名称</b>： AbstractResponse <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubbery</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/11/14 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public abstract class AbstractResponse<T> implements Response<T> {
    protected final HttpResponse httpResponse;

    protected AbstractResponse(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    @Override
    public int status() {
        return httpResponse.getReturnCode();
    }

    @Override
    public boolean isSuccess() {
        return status() == 200;
    }
}
