package ua.nure.kopaniev.builder;

import lombok.NonNull;

/**
 * Request builder.
 */
public class RequestBuilder {
    private StringBuilder sb = new StringBuilder();

    public RequestBuilder add(@NonNull String key, @NonNull String value) {
        sb.append(key).append("=").append(value).append("&");
        return this;
    }

    @Override
    public String toString() {
        return sb.substring(0, sb.length() - 1);
    }
}
