/**
 * Copyright (c) 2015, cubbery.com. All rights reserved.
 */
package com.cubbery.sdk.http.core.request;

import com.cubbery.sdk.http.core.Constant;
import com.cubbery.sdk.http.core.HttpConfig;
import com.cubbery.sdk.http.core.utils.StringUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;

/**
 * <b>项目名</b>： http-sdk <br>
 * <b>包名称</b>： com.cubbery.sdk.http.core.request <br>
 * <b>类名称</b>： HttpRequest <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubbery</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/11/14 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public abstract class HttpRequest {

    protected HttpConfig httpConfig;

    protected final URL url;

    protected final Map<String, String> headers;

    protected final Map<String, String[]> parameters;

    protected static final String charset = Constant.DEFAULT_CHARSET;

    protected final Charset userCharset;

    public HttpRequest(HttpRequestBuilder builder) {
        this.url = builder.getUrl();
        this.headers = builder.getHeaders();
        this.userCharset = builder.getCharset();
        this.parameters = new HashMap<String, String[]>();
        Map<String, Collection<String>> params = builder.getParameters();
        for (Map.Entry<String, Collection<String>> entry : params.entrySet()) {
            Collection<String> collection = entry.getValue();
            this.parameters.put(entry.getKey(), collection == null ? new String[0] : collection.toArray(new String[collection.size()]));
        }
    }

    /**
     * 类型名称
     *
     * @return
     */
    public abstract String getMethodName();

    /**
     * 响应体
     *
     * @param connection
     * @throws IOException
     */
    public abstract void responseBody(HttpURLConnection connection) throws IOException;

    public void setHttpConfig(HttpConfig httpConfig) {
        this.httpConfig = httpConfig;
    }

    protected String getStrParams() throws IOException {
        List<String> paramList = new LinkedList<String>();
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            String key = entry.getKey();
            if (key != null) {
                String encodeKey = URLEncoder.encode(key, charset);
                for (String value : entry.getValue()) {
                    paramList.add(encodeKey + "=" + URLEncoder.encode(StringUtils.defaultString(value), charset));
                }
            }
        }
        String content = StringUtils.join(paramList, "&");
        return content;
    }

    public HttpConfig getHttpConfig() {
        return httpConfig;
    }

    public URL getUrl() {
        return url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, String[]> getParameters() {
        return parameters;
    }

    public static String getCharset() {
        return charset;
    }

    public Charset getUserCharset() {
        return userCharset;
    }

}
