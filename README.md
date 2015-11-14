# http-sdk
[![Build Status](https://travis-ci.org/cncduLee/http-sdk.svg?branch=master)]

a simple http sdk tool,you can use it on modile app and as a powerful resetful client.

一个简单一样纯JAVA的HTTP工具组件。你可以用它来作为Android等移动应用访问后台服务的工具，
也可以用来访问网络。


### 1、use it as http request tool（作为http访问组件）

##### 1-1、direct access (直接访问)

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
    
##### 1-2、with proxy (代理访问)
    @Test
    public void testExecuteProxy() throws Exception {
        HttpConfig httpConfig = HttpConfig.DEFAULT;
        //httpConfig.setRequestTimeout(2000);//2s
        httpConfig.setHttpProxy(new HttpProxy("127.0.0.1",48102));//代理

        GetHttpRequest request = new GetHttpRequest(httpRequestBuilder);
        request.setHttpConfig(httpConfig);
        HttpResponse response = HttpClient.execute(request);
        assertEquals(response.getReturnCode(),200);
    }

### 2、use it as resetful request tool（作为restful访问组件）

    @Test
    public void testExecute() throws Exception {
        HttpClientSdk httpClientSdk = new HttpClientSdk("http://www.duitang.com");

        DuitangRequest request = new DuitangRequest();
        DuitangRes duitangRes = httpClientSdk.execute(request);
        assertEquals(duitangRes.isSuccess(),true);
        assertEquals(duitangRes.content().success,true);
    }
    
##### 2-1、entity (实体对象)

    public class Duitang {
        public boolean success;
        Data data;
    }
    
    class Data {
        List<Blog> blogs;
        boolean hasNext;
        boolean hasrp;
        boolean nopth;
        String pgsource;
    }
    
    class Blog {
        String albid;
        String albnm;
        String ava;
        String msg;
        String iht;
        String price;
    }
##### 2-1、request & response (访问对象)
    //设置访问参数
    public class DuitangRequest extends GetRequest<DuitangRes> {
    
        @Override
        protected String getUri() {
            return "/hot/masn/?_type=&pgsource=tp_&_=1447487204293&page=0";
        }
    
        @Override
        protected Map<String, String[]> buildParameters() {
            return null;
        }
    
        @Override
        protected DuitangRes parseResponse(HttpResponse response) {
            return new DuitangRes(response);
        }
    }
    
    //这是访问结果
    public class DuitangRes  extends ObjectResponse<Duitang> {
        protected DuitangRes( HttpResponse httpResponse) {
            super(Duitang.class, httpResponse);
        }
    
        protected DuitangRes(Class<Duitang> clazz, HttpResponse httpResponse) {
            super(clazz, httpResponse);
        }
    }
    
### 3、 summary （总结）
    
    1、简单（simple）
    2、扩展性良好(scalable)
    