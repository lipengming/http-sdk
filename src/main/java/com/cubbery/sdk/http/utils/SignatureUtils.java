/**
 * Copyright (c) 2015, cubbery.com. All rights reserved.
 */
package com.cubbery.sdk.http.utils;


import com.cubbery.sdk.http.core.utils.IOUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * <b>项目名</b>： http-sdk <br>
 * <b>包名称</b>： com.cubbery.sdk.http <br>
 * <b>类名称</b>： SignatureUtils <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:cubber@cubbery.com">cubbery</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/11/14 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public final class SignatureUtils {
    private static Logger logger = LoggerFactory.getLogger(SignatureUtils.class);

    public static final String HEADER_ATTRIBUTE_AUTH_CONSUMER_KEY = "Auth-Consumer-Key";
    public static final String HEADER_ATTRIBUTE_AUTH_TIMESTAMP = "Auth-Timestamp";
    public static final String HEADER_ATTRIBUTE_AUTH_NONCE = "Auth-Nonce";
    public static final String HEADER_ATTRIBUTE_AUTH_SIGNATURE = "Auth-Signature";
    public static final String HEADER_ATTRIBUTE_AUTH_SECRET = "Auth-Secret";
    public static final String HEADER_ATTRIBUTE_CONSUMER_VERSION = "Consumer-Version";

    private static final String MAVEN_PROJECT_VERSION = "version";
    public static String md5Sign(String appid, String secret, String timestamp, String nonce, Map<String, String[]> params) {
        StringBuilder origin = new StringBuilder();
        if (params != null && params.size() > 0) {
            TreeMap<String, String[]> sortedParams = new TreeMap<String, String[]>(params);
            for (Map.Entry<String, String[]> e : sortedParams.entrySet()) {
                if (e.getValue() != null && e.getValue().length > 0) {
                    if (e.getValue().length == 1) {
                        origin.append(e.getKey()).append("=").append(e.getValue()[0]).append("&");
                    } else {
                        Arrays.sort(e.getValue());
                        for (String v : e.getValue()) {
                            origin.append(e.getKey()).append("=").append(v).append("&");
                        }
                    }
                }
            }
        }
        origin.append(HEADER_ATTRIBUTE_AUTH_CONSUMER_KEY).append("=").append(appid).append("&");
        origin.append(HEADER_ATTRIBUTE_AUTH_TIMESTAMP).append("=").append(timestamp).append("&");
        origin.append(HEADER_ATTRIBUTE_AUTH_NONCE).append("=").append(nonce).append("&");
        origin.append(HEADER_ATTRIBUTE_AUTH_SECRET).append("=");
        origin.append(secret);
        String oriSign = origin.toString();
        return DigestUtils.md5Hex(oriSign);
    }


    public static String mavenVersion(String path) {
        try {
            Properties pVersion = getProperties(path);
            return pVersion.getProperty(MAVEN_PROJECT_VERSION);
        } catch (Exception e) {
            logger.error("读取ScheduleConfig 的版本号配置文件错误:", e);
            return "";
        }
    }


    private static Properties getProperties(String path) throws Exception {
        logger.info("Load Property File With Path : {} ", path);
        InputStream inputStream = SignatureUtils.class.getClassLoader().getResourceAsStream(path);
        if (inputStream != null) {
            try {
                Properties p = new Properties();
                p.load(inputStream);
                return p;
            } catch (Exception e) {
                logger.error("Load Property File Error! Path: {}", path);
                throw e;
            } finally {
                IOUtil.closeQuietly(inputStream);
            }
        } else {
            logger.warn("Property is not exist, path: {} ", path);
            throw new FileNotFoundException(path);
        }
    }
}
