package org.lti.api;

import java.util.Map;

public interface LTIStore {

    public LTIToolProvider createToolProvider(String endpoint, String key, String secret, Map<String, String> params) throws LTIException;
    public LTIToolProvider createToolProvider(String endpoint, String key, String secret, Map<String, String> params, String version) throws LTIException;
}
