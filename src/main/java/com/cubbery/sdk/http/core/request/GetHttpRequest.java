/**
 * Copyright (c) 2015, cubbery.com. All rights reserved.
 */
package com.cubbery.sdk.http.core.request;

import com.cubbery.sdk.http.core.utils.StringUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <b>项目名</b>： http-sdk <br>
 * <b>包名称</b>： com.cubbery.sdk.http.core.request <br>
 * <b>类名称</b>： GetHttpRequest <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubbery</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/11/14 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class GetHttpRequest extends HttpRequest {

    private static final String GET = "GET";

    public GetHttpRequest(HttpRequestBuilder builder) {
        super(builder);
    }

    @Override
    public String getMethodName() {
        return GET;
    }

    @Override
    public void responseBody(HttpURLConnection connection) throws IOException {
        //nothing...
    }

    public URL getURL() throws IOException {
        if (url == null) {
            return null;
        }
        String params = getStrParams();
        if(StringUtils.isBlank(params)){
            return url;
        }
        String strURL = url.toString();
        int index = strURL.indexOf("?");
        if (index < 0) {
            return new URL(strURL + "?" + params);
        } else {
            return new URL(strURL + "&" + params);
        }
    }
}
