/**
 * Copyright (c) 2015, cubbery.com. All rights reserved.
 */
package com.cubbery.sdk.http.bean;

import com.cubbery.sdk.http.core.response.HttpResponse;
import com.cubbery.sdk.http.request.GetRequest;

import java.util.Map;

/**
 * <b>项目名</b>： http-sdk <br>
 * <b>包名称</b>： com.cubbery.sdk.http.bean <br>
 * <b>类名称</b>： DuitangRequest <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubbery</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/11/14 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
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
