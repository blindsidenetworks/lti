package org.lti.api;

import java.util.Map;

import org.json.JSONArray;

public interface LTIToolProvider {
    public boolean hasValidSignature() throws LTIException, Exception;
    public boolean hasRequiredParameters(JSONArray requiredParameters) throws LTIException, Exception;
    public void overrideParameters(JSONArray overrides) throws Exception;
    public Map<String, String> getParameters();
    public String getParameter(String key);
    public void putParameter(String key, String value);
    public boolean hasParameter(String key);
}
