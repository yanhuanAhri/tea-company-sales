package com.yh.core.util;

import java.util.List;



public class URLBuilder {
    private StringBuilder queryB;
    private String host;

    public URLBuilder() {
        queryB = new StringBuilder();
    }
    public URLBuilder(String host) {
        queryB = new StringBuilder();
        host(host);
    }

    public URLBuilder paramArray(String name, List<?> value) {
        return paramArray(name, value == null ? null : value.toArray());
    }

    public URLBuilder paramPath(String name, Object value) {
        if (name == null || value == null)
            return this;
        String p = '{' + name + '}';
        host = host.replace(p, value.toString());
        return this;
    }

    public URLBuilder paramArrayPath(String name, List<?> value) {
        return paramArray(name, value == null ? null : value.toArray());
    }

    public URLBuilder paramArrayPath(String name, Object[] value) {
        return paramPath(name, parseArray(value));
    }

    public URLBuilder paramArray(String name, Object... value) {
        return param(name, parseArray(value));
    }
    
    public URLBuilder host(String host) {
        this.host = host;
        return this;
    }
    

    public URLBuilder param(String name, Object value) {
        if (name == null || value == null)
            return this;
        queryB.append(queryB.indexOf("?") == -1 ? '?' : '&');
        queryB.append(name).append('=').append(value.toString());
        return this;
    }

    private String parseArray(Object[] value) {
        if (value == null)
            return null;
        int iMax = value.length - 1;
        if (iMax == -1)
            return "";
        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append(value[i]);
            if (i == iMax)
                break;
            b.append(",");
        }
        return b.toString();
    }

    @Override
    public String toString() {
        return host + queryB.toString();
    }

    public String getURL() {
        return toString();
    }
}
