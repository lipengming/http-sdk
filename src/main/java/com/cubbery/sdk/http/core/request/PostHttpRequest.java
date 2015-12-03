/**
 * Copyright (c) 2015, cubbery.com. All rights reserved.
 */
package com.cubbery.sdk.http.core.request;

import com.cubbery.sdk.http.core.utils.IOUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

/**
 * <b>项目名</b>： http-sdk <br>
 * <b>包名称</b>： com.cubbery.sdk.http.core.request <br>
 * <b>类名称</b>： PostHttpRequest <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubbery</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/11/14 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class PostHttpRequest extends HttpRequest {
    private static final String POST = "POST";

    public PostHttpRequest(HttpRequestBuilder builder) {
        super(builder);
    }

    @Override
    public String getMethodName() {
        return POST;
    }

    @Override
    public void responseBody(HttpURLConnection connection) throws IOException {
        String strParams= getStrParams();
        OutputStream out = null;
        try {
            out = connection.getOutputStream();
            out.write(strParams.getBytes(userCharset));
        } finally {
            IOUtil.closeQuietly(out);
        }
    }
}
