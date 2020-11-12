package com.vz89.hometask.utils;

import javax.servlet.ServletException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RestUtils {
    private Pattern regExAllPattern;
    private Pattern regExIdPattern;
    private Long id;
    private GetRequestType getRequestType;

    public RestUtils(String pathInfo, String type) throws ServletException {
        regExAllPattern = Pattern.compile("/" + type);
        regExIdPattern = Pattern.compile("\\d+");
        checkUrl(pathInfo);
    }

    private void checkUrl(String pathInfo) throws ServletException {
        Matcher matcher = regExIdPattern.matcher(pathInfo);
        if (matcher.find()) {
            id = Long.parseLong(matcher.group());
            getRequestType = GetRequestType.GET_BY_ID;
            return;
        }
        if (regExAllPattern.matcher(pathInfo).find()) {
            getRequestType = GetRequestType.FIND_ALL;
            return;
        }
        throw new ServletException("Invalid URI");
    }

    public Long getId() {
        return id;
    }

    public GetRequestType getGetRequestType() {
        return getRequestType;
    }
}
