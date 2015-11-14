/**
 * Copyright (c) 2015, cubbery.com. All rights reserved.
 */
package com.cubbery.sdk.http.request;

import com.cubbery.sdk.http.Response;
import com.cubbery.sdk.http.core.request.HttpRequestMethod;

/**
 * <b>项目名</b>： http-sdk <br>
 * <b>包名称</b>： com.cubbery.sdk.http.request <br>
 * <b>类名称</b>： PostRequest <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubbery</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/11/14 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public abstract class PostRequest<T extends Response> extends AbstractRequest<T> {
    @Override
    protected HttpRequestMethod getMethod() {
        return HttpRequestMethod.POST;
    }
}
