package com.epam.preprod.kopaniev.builder;

/**
 * Created by Vladyslav_Kopaniev on 11/24/2015.
 */
public class RequestBuilder {
    private StringBuilder sb = new StringBuilder();

    public RequestBuilder add(String key, String value) {
        if (value != null && !"".equals(value)) {
            sb.append(key).append("=").append(value).append("&");
        }
        return this;
    }

    @Override
    public String toString() {
        return sb.substring(0, sb.length() - 1);
    }
}
