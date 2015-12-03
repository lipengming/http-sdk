/**
 * Copyright (c) 2015, cubbery.com. All rights reserved.
 */
package com.cubbery.sdk.http.core;

/**
 * <b>项目名</b>： http-sdk <br>
 * <b>包名称</b>： com.cubbery.sdk.http <br>
 * <b>类名称</b>： Constant <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubbery</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/11/14 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public interface Constant {
    static final String DEFAULT_CHARSET = "GBK";
    static final String CONNECTION = "Connection";
    static final String KEEP_ALIVE = "Keep-Alive";//共享连接，减少连接数量
    static final String CONTENT_ENCODING = "Content-Encoding";
    static final String CONTENT_TYPE = "Content-Type";//mime类型
    static final String DATE = "Date";//日期
    static final String CONTENT_LENGTH = "Content-Length";//大小
}
