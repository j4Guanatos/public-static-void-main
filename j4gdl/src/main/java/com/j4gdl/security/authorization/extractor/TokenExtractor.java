package com.j4gdl.security.authorization.extractor;

/**
 * Created by Emmanuel_Garcia on 3/30/2017.
 */
public interface TokenExtractor {
    public String extract(String payload);
}
