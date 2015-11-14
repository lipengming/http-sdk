/**
 * Copyright (c) 2015, cubbery.com. All rights reserved.
 */
package com.cubbery.sdk.http.request;

import com.cubbery.sdk.http.HttpClientSdk;
import com.cubbery.sdk.http.Request;
import com.cubbery.sdk.http.Response;
import com.cubbery.sdk.http.core.HttpClient;
import com.cubbery.sdk.http.core.request.HttpRequest;
import com.cubbery.sdk.http.core.request.HttpRequestBuilder;
import com.cubbery.sdk.http.core.request.HttpRequestMethod;
import com.cubbery.sdk.http.core.response.HttpResponse;
import com.cubbery.sdk.http.utils.SignatureUtils;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * <b>项目名</b>： http-sdk <br>
 * <b>包名称</b>： com.cubbery.sdk.http.request <br>
 * <b>类名称</b>： AbstractRequest <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubbery</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/11/14 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public abstract class AbstractRequest<T extends Response> implements Request<T> {
    //META-INF/maven/groupId/artifactId/pom.properties
    private static final String MAVEN_POM_PROPERTIES_PATH = "META-INF/maven/com.cubbery.sdk/http-sdk/pom.properties";
    private static String MAVEN_VERSION;//<version>1.0.0</version>
    static {
        MAVEN_VERSION = SignatureUtils.mavenVersion(MAVEN_POM_PROPERTIES_PATH);
    }

    @Override
    public T execute(HttpClientSdk httpClientSdk) throws IOException {
        HttpRequestBuilder httpRequestBuilder = HttpRequestBuilder.newBuilder();
        httpRequestBuilder.setUrl(httpClientSdk.getHost() + getUri());
        httpRequestBuilder.setMethod(getMethod());
        Map<String, String[]> params = buildParameters();
        if(null != params) httpRequestBuilder.setParameters(buildParameters());

        httpRequestBuilder.addHeader(SignatureUtils.HEADER_ATTRIBUTE_AUTH_CONSUMER_KEY, httpClientSdk.getAppId());
        String timestamp = String.valueOf(System.currentTimeMillis());
        httpRequestBuilder.addHeader(SignatureUtils.HEADER_ATTRIBUTE_AUTH_TIMESTAMP, timestamp);
        String nonce = UUID.randomUUID().toString();
        httpRequestBuilder.addHeader(SignatureUtils.HEADER_ATTRIBUTE_AUTH_NONCE, nonce);
        String signature = SignatureUtils.md5Sign(httpClientSdk.getAppId(), httpClientSdk.getSecret(), timestamp, nonce, params);
        httpRequestBuilder.addHeader(SignatureUtils.HEADER_ATTRIBUTE_AUTH_SIGNATURE, signature);//验签，确保报文不被篡改
        httpRequestBuilder.addHeader(SignatureUtils.HEADER_ATTRIBUTE_CONSUMER_VERSION,MAVEN_VERSION);//version
        HttpRequest httpRequest = httpRequestBuilder.build();
        HttpResponse httpResponse = HttpClient.execute(httpRequest);
        return parseResponse(httpResponse);
    }

    /**
     * 请求地址uri
     *
     * @return
     */
    protected abstract String getUri();

    /**
     * 请求方式 Get或Post
     *
     * @return
     */
    protected abstract HttpRequestMethod getMethod();

    /**
     * 请求参数
     *
     * @return
     */
    protected abstract Map<String, String[]> buildParameters();

    /**
     * 分析请求的响应结果
     *
     * @param response
     * @return
     */
    protected abstract T parseResponse(HttpResponse response);
}
