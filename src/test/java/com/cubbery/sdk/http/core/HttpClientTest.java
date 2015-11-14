package com.cubbery.sdk.http.core;

import com.cubbery.sdk.http.core.request.GetHttpRequest;
import com.cubbery.sdk.http.core.request.HttpRequest;
import com.cubbery.sdk.http.core.request.HttpRequestBuilder;
import com.cubbery.sdk.http.core.request.HttpRequestMethod;
import com.cubbery.sdk.http.core.response.HttpResponse;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class HttpClientTest {

    static HttpRequestBuilder httpRequestBuilder = new HttpRequestBuilder();
    static {
        httpRequestBuilder.setUrl("http://www.baidu.com");
    }

    @Test
    public void testExecute() throws Exception {
        HttpRequest request = HttpRequestMethod.GET.create(httpRequestBuilder);
        HttpResponse response = HttpClient.execute(request);
        assertEquals(response.getReturnCode(),200);
    }

    //@Test
    public void testExecuteProxy() throws Exception {
        HttpConfig httpConfig = HttpConfig.DEFAULT;
        //httpConfig.setRequestTimeout(2000);//2s
        httpConfig.setHttpProxy(new HttpProxy("127.0.0.1",48102));//代理

        GetHttpRequest request = new GetHttpRequest(httpRequestBuilder);
        request.setHttpConfig(httpConfig);
        HttpResponse response = HttpClient.execute(request);
        assertEquals(response.getReturnCode(),200);
    }
}