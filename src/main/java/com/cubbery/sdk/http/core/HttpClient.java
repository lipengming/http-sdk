/**
 * Copyright (c) 2015, cubbery.com. All rights reserved.
 */
package com.cubbery.sdk.http.core;

import com.cubbery.sdk.http.core.request.HttpRequest;
import com.cubbery.sdk.http.core.response.HttpResponse;
import com.cubbery.sdk.http.core.utils.IOUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * <b>项目名</b>： http-sdk <br>
 * <b>包名称</b>： com.cubbery.sdk.http.core <br>
 * <b>类名称</b>： HttpClient <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubbery</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/11/14 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public final class HttpClient {
    private static final String CHARSET = "charset=";
    private static final char SPLITTER = ';';
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CTRL = "\r\n";

    private HttpClient() { }

    public static HttpResponse execute(HttpRequest httpRequest) throws IOException {
        HttpConfig httpConfig = httpRequest.getHttpConfig() == null ? HttpConfig.DEFAULT : httpRequest.getHttpConfig();

        HttpURLConnection connection = null;
        try {
            connection = getHttpURLConnection(httpRequest.getUrl(),httpConfig); //获取连接
            setTimeOut(httpConfig, connection);//设置超时
            connection.setInstanceFollowRedirects(httpConfig.isRedirectEnable());//设置重定向
            connection.setUseCaches(false);//不使用缓存
            connection.setDoInput(true);//可输入输出
            if(httpRequest instanceof HttpRequest) {//设置METHOD
                connection.setRequestMethod(httpRequest.getMethodName());
                connection.setDoOutput(true);
            }
            if (null != httpRequest.getHeaders()) {// 设置hea ders
                for (Map.Entry<String, String> entryTmp : httpRequest.getHeaders().entrySet()) {
                    connection.setRequestProperty(entryTmp.getKey(), entryTmp.getValue());
                }
            }
            return getHttpResponse(connection,httpRequest);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private static void setTimeOut(HttpConfig httpConfig, HttpURLConnection conn) {
        if (httpConfig.getConnectTimeout() > 0) {
            conn.setConnectTimeout(httpConfig.getConnectTimeout());
        }
        if (httpConfig.getRequestTimeout() > 0) {
            conn.setReadTimeout(httpConfig.getRequestTimeout());
        }
    }

    private static HttpURLConnection getHttpURLConnection(URL url, HttpConfig httpConfig) throws IOException {
        if (httpConfig.getHttpProxy() == null) {
            return (HttpURLConnection) (url).openConnection();
        } else {
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(httpConfig.getHttpProxy().getHost(), httpConfig.getHttpProxy().getPort()));
            return (HttpURLConnection) (url).openConnection(proxy);
        }
    }

    private static HttpResponse getHttpResponse(HttpURLConnection connection, HttpRequest httpRequest) throws IOException {
        BufferedReader in = null;//站在机器的角度，从远端读入内存
        //OutputStream out = null;//站在机器的角度，从内存写入远端
        try {
            httpRequest.responseBody(connection);//写入请求参数值（对于GET不需要，）
            String charset = getCharset(connection);//获取content type
            StringBuilder sb = new StringBuilder();
            //读取http返回的结果
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset == null ?  Constant.DEFAULT_CHARSET : charset));
            String line = null;
            while ((line = in.readLine()) != null) {
                sb.append(CTRL).append(line);
            }
            return HttpResponse.createInstance().setReturnCode(connection.getResponseCode())
                    .setResponseBody(sb.toString()).setHeaders(connection.getHeaderFields()).build();
        } finally {
            IOUtil.closeQuietly(in);//关闭流 IOUtil.closeQuietly(out);
        }
    }

    private static String getCharset(HttpURLConnection httpURLConnection) {
        List<String> list = httpURLConnection.getHeaderFields().get(CONTENT_TYPE);
        String charset = null;
        if (list != null) {
            for (String cell : list) {
                int charsetIndex = cell.indexOf(CHARSET);
                // 如果小于0 ，不存在，下一个
                if (charsetIndex >= 0) {
                    int indexBegin = charsetIndex + CHARSET.length();
                    if (indexBegin > 0) {
                        int indexEnd = cell.indexOf(SPLITTER, indexBegin);
                        if (indexEnd < 0) {
                            charset = cell.substring(indexBegin);
                        } else {
                            charset = cell.substring(indexBegin, indexEnd);
                        }
                        break;
                    }
                }
            }
        }
        return charset;
    }
}
