/**
 * Copyright (c) 2015, cubbery.com. All rights reserved.
 */
package com.cubbery.sdk.http;

import com.cubbery.sdk.http.core.utils.StringUtils;

import java.io.IOException;

/**
 * <b>项目名</b>： http-sdk <br>
 * <b>包名称</b>： com.cubbery.sdk.http <br>
 * <b>类名称</b>： HttpSdk <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubbery</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/11/14 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class HttpClientSdk {
    private String host = "http://api.cubbery.com";
    private String appId;
    private String secret;

    public HttpClientSdk(String host) {
        this(host,null,null);
    }

    public HttpClientSdk(String host, String appId, String secret) {
        if (StringUtils.isNotBlank(host)) {
            this.host = host;
        }
        this.appId = appId;
        this.secret = secret;
    }

    public <T extends Response<?>> T execute(Request<T> request) throws IOException {
        return request.execute(this);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "HttpClientSdk{" +
                "host='" + host + '\'' +
                ", appId='" + appId + '\'' +
                ", secret='" + secret + '\'' +
                '}';
    }
}
