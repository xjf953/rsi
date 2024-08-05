package com.shawn.votesystem.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;

/**
 * RequestUtil
 *
 * @author fly.xiao
 * @date 2022/7/21 17:59
 * @since 1.0.0
 */
public class RequestUtil {

    /**
     * 获取发起请求的浏览器名称
     */
    public static String getBrowserName(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        Browser browser = userAgent.getBrowser();
        return browser.getName();
    }


    /**
     * 获取发起请求的操作系统名称
     */
    public static String getOsName(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        return operatingSystem.getName();
    }


    /**
     * 获取发起请求的浏览器版本号
     */
    public static String getBrowserVersion(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        //获取浏览器信息
        Browser browser = userAgent.getBrowser();
        //获取浏览器版本号
        Version version = browser.getVersion(header);
        return version.getVersion();
    }

    /**
     * 获取发起请求的IP地址
     */
    public static String getIp(HttpServletRequest request) {
        String ip = null;
        ip = request.getHeader("x-forwarded-for");
        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip = inet.getHostAddress();
            }
        }
        if ((ip != null) && (ip.length() > 15)) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
    }
    public static String getURI(HttpServletRequest request) {
        String localAddr = request.getLocalAddr();
        int port = request.getServerPort();
        return "http://" + localAddr + ":" + port;
    }

}