package com.cubbery.sdk.http;

import com.cubbery.sdk.http.bean.DuitangRequest;
import com.cubbery.sdk.http.bean.DuitangRes;
import org.testng.annotations.Test;

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
}