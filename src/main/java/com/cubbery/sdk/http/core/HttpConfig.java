/**
 * Copyright (c) 2015, cubbery.com. All rights reserved.
 */
package com.cubbery.sdk.http.core;

/**
 * <b>项目名</b>： http-sdk <br>
 * <b>包名称</b>： com.cubbery.sdk.http.core <br>
 * <b>类名称</b>： HttpConfig <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubbery</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/11/14 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class HttpConfig  implements Cloneable {
    private HttpProxy httpProxy;
    private boolean redirectEnable;
    private int requestTimeout; //单位：milliseconds
    private int connectTimeout; //单位：milliseconds

    public HttpConfig() {
        this.redirectEnable = true;
        this.requestTimeout = -1;//never
        this.connectTimeout = -1;//never
    }

    public HttpProxy getHttpProxy() {
        return httpProxy;
    }

    public boolean isRedirectEnable() {
        return redirectEnable;
    }

    public int getRequestTimeout() {
        return requestTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setHttpProxy(HttpProxy httpProxy) {
        this.httpProxy = httpProxy;
    }

    public void setRedirectEnable(boolean redirectEnable) {
        this.redirectEnable = redirectEnable;
    }

    public void setRequestTimeout(int requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public static final HttpConfig DEFAULT = new HttpConfig();
}
