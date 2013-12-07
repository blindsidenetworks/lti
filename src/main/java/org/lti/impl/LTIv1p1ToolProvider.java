package org.lti.impl;

import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import net.oauth.OAuth;
import net.oauth.OAuthMessage;
import net.oauth.signature.HMAC_SHA1;

import org.json.JSONArray;
import org.json.JSONObject;
import org.lti.api.LTIException;
import org.lti.api.LTIToolProvider;

public class LTIv1p1ToolProvider extends LTIv1p1 implements LTIToolProvider{

    private static final Logger log = Logger.getLogger(LTIv1p0ToolProvider.class);

    protected Map<String, String> params;
    protected String oauth_consumer_key;
    protected String oauth_nonce;
    protected String oauth_callback;
    protected String oauth_signature;
    protected String oauth_signature_method;
    protected String oauth_version;
    protected String oauth_timestamp;
    
    protected String endpoint;
    protected String key;
    protected String secret;
    
    public LTIv1p1ToolProvider() {}
    
    public LTIv1p1ToolProvider(String endpoint, String key, String secret, Map<String, String> params) throws LTIException, Exception {
        log.info("LTIv1p0ToolProvider initializad");

        this.endpoint = endpoint;
        this.key = key;
        this.secret = secret;

        if( params.containsKey(OAuth.OAUTH_CONSUMER_KEY)) oauth_consumer_key = params.get(OAuth.OAUTH_CONSUMER_KEY); else throw new AmbasadoroException("Parameter [" + OAuth.OAUTH_CONSUMER_KEY + "] not included", "OAuthError");
        if( params.containsKey(OAuth.OAUTH_SIGNATURE)) oauth_signature = params.get(OAuth.OAUTH_SIGNATURE); else throw new AmbasadoroException("Parameter [" + OAuth.OAUTH_SIGNATURE + "] not included", "OAuthError");
        this.params = params;
    }

    public String getLTIVersion(){
        return LTIv1p1.VERSION;
    }
    
    public boolean hasValidSignature() throws LTIException, Exception {
        boolean response = false;
        log.debug("Checking if the OAuth signature is valid. endpoint=" + this.endpoint + ", secret=" + this.secret );
        Object postProp = sanitizePrametersForBaseString();
        
        OAuthMessage oam = new OAuthMessage("POST", this.endpoint, ((Properties)postProp).entrySet());
        HMAC_SHA1 hmac = new HMAC_SHA1();
        hmac.setConsumerSecret(this.secret);
        String baseString = HMAC_SHA1.getBaseString(oam);
        System.out.println("Base Message String = [ " + baseString + " ]\n");
        if( hmac.isValid(oauth_signature, baseString) )
            response = true;
        System.out.println("Calculated: " + hmac.getSignature(baseString) + " Received: " + oauth_signature);

        return response;
    }

    public boolean hasRequiredParameters(JSONArray requiredParameters) throws LTIException, Exception {
        boolean response = true;
        String missingParams = "";
        for (int i = 0; i < requiredParameters.length(); i++) {
            if( !params.containsKey(requiredParameters.getString(i)) ){
                if( missingParams.length()>0) missingParams += ", ";
                missingParams += requiredParameters.getString(i);
                response = false;
            }
        }
        if(!response) throw new LTIException("Required Parameters [" + missingParams + "] not included", "ToolProviderError");
        else return response;
    }

    public void overrideParameters(JSONArray overrides) throws Exception {
        JSONObject override;
        String source;
        String target;

        for (int i = 0; i < overrides.length(); i++) {
            override = overrides.getJSONObject(i);
            source = override.getString("source");
            target = override.getString("target");
            if( params.containsKey(target) ){
                params.put(source, params.get(target));
            }
        }
        
    }

    protected Properties sanitizePrametersForBaseString() {
        Properties reqProp = new Properties();
        for (String key : params.keySet()) {
            if (key.equals("oauth_signature") ) {
                // We don't need this as part of the base string
                continue;
            }
            String value = params.get(key);
            reqProp.setProperty(key, value);
        }

        return reqProp;
    }

    public Map<String, String> getParameters(){
        return params;
    }
    
    public String getParameter(String key){
        return params.get(key);
    }

    public void putParameter(String key, String value){
        params.put(key, value);
    }

}
