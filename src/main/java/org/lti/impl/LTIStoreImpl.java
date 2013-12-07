package org.lti.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.lti.api.LTIException;
import org.lti.api.LTIStore;
import org.lti.api.LTIToolProvider;

public class LTIStoreImpl implements LTIStore {

    private static final Logger log = Logger.getLogger(LTIStoreImpl.class);

    private static final LTIStoreImpl INSTANCE = new LTIStoreImpl();

    private LTIStoreImpl() {}

    public static LTIStoreImpl getInstance() {
        return INSTANCE;
    }

    public LTIToolProvider createToolProvider(String endpoint, String key, String secret, Map<String, String> params) throws LTIException {
        return createToolProvider(endpoint, key, secret, params, null);
    }
    
    public LTIToolProvider createToolProvider(String endpoint, String key, String secret, Map<String, String> params, String version) throws LTIException {
        log.info("Creating LTIToolProvider");
        LTIToolProvider tp = null;
        if( version != null ) version = "1.0";
        try {
            log.debug("LTI version: " + version);
            if( version.equals("1.1"))
                tp = new LTIv1p1ToolProvider(endpoint, key, secret, params);
            //else if( version.equals("1.1.1"))
            //    tp = new LTIv1p1p1ToolProvider(endpoint, key, secret, params);
            //else if( version.equals("1.2"))
            //    tp = new LTIv1p2ToolProvider(endpoint, key, secret, params);
            //else if( version.equals("2.0"))
            //    tp = new LTIv2p0ToolProvider(endpoint, key, secret, params);
            else
                tp = new LTIv1p0ToolProvider(endpoint, key, secret, params);

        } catch ( Exception e ){
            throw new LTIException(LTIException.MESSAGEKEY_INTERNALERROR, "The tool provider could not be instantiated", e.getCause());
        }
        return tp;
    }

}
