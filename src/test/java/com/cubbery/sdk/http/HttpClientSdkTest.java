package com.cubbery.sdk.http;

import com.cubbery.sdk.http.bean.DuitangRequest;
import com.cubbery.sdk.http.bean.DuitangRes;
import com.cubbery.sdk.http.core.response.HttpResponse;
import com.cubbery.sdk.http.request.GetRequest;
import com.cubbery.sdk.http.response.AbstractResponse;
import com.cubbery.sdk.http.response.ObjectResponse;
import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

import static org.testng.Assert.assertEquals;


public class HttpClientSdkTest {

    @Test
    public void testExecute() throws Exception {
        HttpClientSdk httpClientSdk = new HttpClientSdk("http://www.duitang.com");

        DuitangRequest request = new DuitangRequest();
        DuitangRes duitangRes = httpClientSdk.execute(request);
        assertEquals(duitangRes.isSuccess(),true);
        assertEquals(duitangRes.content().success,true);
    }

    public void testSimple() throws Exception {
        HttpClientSdk httpClientSdk = new HttpClientSdk("http://www.duitang.com");
        DuitangRes duitangRes = httpClientSdk.execute(new GetRequest<DuitangRes>(){

            @Override
            protected String getUri() {
                return "/hot/masn/?_type=&pgsource=tp_&_=1447487204293&page=0";
            }

            @Override
            protected Map<String, String[]> buildParameters() {
                return null;
            }

            @Override
            protected DuitangRes parseResponse(final HttpResponse response) {
                return new AbstractResponse<DuitangRes>(response) {
                    @Override
                    public DuitangRes content() {
                        return new Gson().fromJson(httpResponse.getResponseBody(),DuitangRes.class);
                    }
                }.content();
            }
        });
        assertEquals(duitangRes.isSuccess(),true);
        assertEquals(duitangRes.content().success,true);
    }
}