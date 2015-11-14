/**
 * Copyright (c) 2015, cubbery.com. All rights reserved.
 */
package com.cubbery.sdk.http.core.request;

import com.cubbery.sdk.http.core.utils.ParameterUtil;
import com.cubbery.sdk.http.core.utils.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

/**
 * <b>项目名</b>： http-sdk <br>
 * <b>包名称</b>： com.cubbery.sdk.http.core.request <br>
 * <b>类名称</b>： HttpRequestBuilder <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubbery</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/11/14 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class HttpRequestBuilder {
    private URL url;
    private Charset charset;
    private HttpRequestMethod method;
    private Map<String, String> headers = new HashMap<String, String>();
    private Map<String, Collection<String>> parameters = new HashMap<String, Collection<String>>();

    public static HttpRequestBuilder newBuilder() {
        return new HttpRequestBuilder();
    }


    public URL getUrl() {
        return url;
    }

    public HttpRequestMethod getMethod() {
        return method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, Collection<String>> getParameters() {
        return parameters;
    }

    public HttpRequestBuilder setUrl(URL url) {
        this.url = url;
        return this;
    }

    public HttpRequestBuilder setUrl(String url) {
        try {
            URL realURL = new URL(url);
            this.url = realURL;
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException();
        }
        return this;
    }

    public HttpRequestBuilder setMethod(HttpRequestMethod method) {
        this.method = method;
        return this;
    }

    /**
     * @param key   不为空和空字符串
     *
     * @param value 任意
     * @return
     */
    public HttpRequestBuilder addHeader(String key, String value) {
        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException();
        }
        headers.put(key, StringUtils.defaultString(value));
        return this;
    }

    /**
     * @param headers key不允许null或者空字符串
     *
     * @return
     */
    public HttpRequestBuilder addHeaders(Map<String, String> headers) {
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            addHeader(entry.getKey(), entry.getValue());

        }
        return this;
    }

    public HttpRequestBuilder addParameter(String key, String value) {
        ParameterUtil.addParameter(parameters, key, value);
        return this;
    }

    public HttpRequestBuilder setParameter(String key, String[] value) {
        LinkedList<String> list;
        if (value == null) {
            list = new LinkedList<String>();
        } else {
            list = new LinkedList<String>(Arrays.asList(value));
        }

        return this.setParameter(key, list);
    }

    public HttpRequestBuilder setParameter(String key, Collection<String> values) {
        ParameterUtil.setParameter(parameters, key, values);
        return this;
    }

    public HttpRequestBuilder setParameter(Map<String, Collection<String>> params) {
        this.parameters.clear();
        if (params == null) {
            return this;
        }
        for (Map.Entry<String, Collection<String>> entry : params.entrySet()) {
            this.setParameter(entry.getKey(), entry.getValue());
        }
        return this;
    }

    /**
     * 重置所有parameter,会替换掉所有paramters
     *
     * @param params
     * @return
     */
    public HttpRequestBuilder setParameters(Map<String, String[]> params) {
        this.parameters.clear();
        if (params != null) {
            for (Map.Entry<String, String[]> entry : params.entrySet()) {
                if (StringUtils.isNotBlank(entry.getKey())) {
                    this.setParameter(entry.getKey(), entry.getValue());
                }
            }

        }
        return this;
    }

    public HttpRequest build() {
        return this.method.create(this);
    }

    public HttpRequestBuilder setCharset(String charset) {
        this.charset = Charset.forName(charset);
        return this;
    }


    public HttpRequestBuilder setHeaderAcceptEncoding(String acceptEncoding) {
        if (StringUtils.isNotBlank(acceptEncoding)) {
            headers.put("Accept-Encoding", acceptEncoding);
        }
        return this;
    }

    public HttpRequestBuilder setHeaderAccept(String accept) {
        if (StringUtils.isNotBlank(accept)) {
            headers.put("Accept", accept);
        }
        return this;
    }

    public HttpRequestBuilder setHeaderAcceptLanguage(String acceptLanguage) {
        if (StringUtils.isNotBlank(acceptLanguage)) {
            headers.put("Accept-Language", acceptLanguage);
        }
        return this;
    }

    public HttpRequestBuilder setKeepAlive(boolean isKeepAlive) {
        headers.put("Connection", isKeepAlive == true ? "keep-alive" : "close");
        return this;
    }

    public HttpRequestBuilder setUserAgent(String userAgent) {
        if (StringUtils.isNotBlank(userAgent)){
            headers.put("User-Agent", userAgent);
        }
        return this;
    }

    public HttpRequestBuilder setCookie(String cookie) {
        if (StringUtils.isNotBlank(cookie)){
            headers.put("Cookie", cookie);
        }
        return this;
    }

    public Charset getCharset() {
        return charset;
    }
}
