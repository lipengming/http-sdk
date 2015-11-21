/**
 * Copyright (c) 2015, cubbery.com. All rights reserved.
 */
package com.cubbery.sdk.http.core.response;

import com.cubbery.sdk.http.core.Constant;
import com.cubbery.sdk.http.core.KeepAlive;
import com.cubbery.sdk.http.core.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>项目名</b>： http-sdk <br>
 * <b>包名称</b>： com.cubbery.sdk.http.core <br>
 * <b>类名称</b>： HttpResponse <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubbery</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/11/14 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class HttpResponse {

    private final int returnCode;

    private final Map<String, String> headers;

    private final String responseBody;

    public HttpResponse(int returnCode, Map<String, String> headers, String responseBody) {
        this.returnCode = returnCode;
        this.headers = headers;
        this.responseBody = responseBody;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public String getHeaderConnection() {
        return headers == null ? null : headers.get(Constant.CONNECTION);
    }

    public String getHeaderKeepAliveTimeOut() {

        return headers == null ? null : headers.get(Constant.KEEP_ALIVE);
    }

    public KeepAlive getKeepAlive() {
        if(headers == null || headers.get(Constant.CONNECTION) == null) {
            return KeepAlive.UNKNOWN;
        }
        if(KeepAlive.KEEP_ALIVE.equalsIgnoreCase(headers.get(Constant.CONNECTION))) {
            return KeepAlive.TRUE;
        }
        return KeepAlive.FALSE;
    }

    public String getHeaderContentEncoding() {
        return headers == null ? null : headers.get(Constant.CONTENT_ENCODING);
    }

    public String getHeaderContentType() {
        return headers == null ? null : headers.get(Constant.CONTENT_TYPE);
    }

    public String getHeaderDate() {
        return headers == null ? null : headers.get(Constant.DATE);
    }

    public String getContentLength() {
        return headers == null ? null : headers.get(Constant.CONTENT_LENGTH);
    }

    public String getHeaderByName(String name) {
        return headers == null ? null : headers.get(name);
    }

    public static Builder createInstance() {
        return new Builder();
    }

    public final static class Builder {
        private int returnCode;

        private String responseBody;

        private Map<String, String> headers;

        private Builder() {}

        public HttpResponse build() {
            return new HttpResponse(returnCode, headers,responseBody);
        }

        public Builder setReturnCode(int returnCode) {
            this.returnCode = returnCode;
            return this;
        }

        public Builder setResponseBody(String responseBody) {
            this.responseBody = responseBody;
            return this;
        }

        public Builder setHeaders(Map<String, List<String>> responseHeaders) {
            headers = new HashMap<String, String>();
            if (responseHeaders == null) {
                return this;
            }
            for (Map.Entry<String, List<String>> entry : responseHeaders.entrySet()) {
                if (entry.getKey() == null) {
                    continue;
                }
                headers.put(entry.getKey(), StringUtils.join(entry.getValue(), ";"));
            }
            return this;
        }
    }
}
