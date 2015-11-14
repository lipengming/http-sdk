/**
 * Copyright (c) 2015, cubbery.com. All rights reserved.
 */
package com.cubbery.sdk.http.core.request;

/**
 * <b>项目名</b>： http-sdk <br>
 * <b>包名称</b>： com.cubbery.sdk.http.core.request <br>
 * <b>类名称</b>： HttpRequestMethod <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubbery</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/11/14 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public enum HttpRequestMethod {
    GET {
        @Override
        public HttpRequest create(HttpRequestBuilder builder) {
            return new GetHttpRequest(builder);
        }
    },
    POST {
        @Override
        public HttpRequest create(HttpRequestBuilder builder) {
            return new PostHttpRequest(builder);
        }
    };

    public abstract HttpRequest create(HttpRequestBuilder builder);
}
