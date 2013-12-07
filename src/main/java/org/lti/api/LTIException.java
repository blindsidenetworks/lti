package org.lti.api;

public class LTIException extends Exception {
    private static final long serialVersionUID = 4842929374437922453L;

    public static final String  MESSAGEKEY_HTTPERROR            = "httpError";
    public static final String  MESSAGEKEY_NOTFOUND             = "notFound";
    public static final String  MESSAGEKEY_NOACTION             = "noActionSpecified";
    public static final String  MESSAGEKEY_INTERNALERROR        = "internalError";
    public static final String  MESSAGEKEY_UNREACHABLE          = "unreachableServerError";
    public static final String  MESSAGEKEY_INVALIDRESPONSE      = "invalidResponseError";
    public static final String  MESSAGEKEY_GENERALERROR         = "generalError";

    private String messageKey;

    public LTIException(String messageKey, String message, Throwable cause) {
        super(message, cause);
        this.messageKey = messageKey;
    }

    public LTIException(String messageKey, String message) {
        super(message);
        this.messageKey = messageKey;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }
    
    public String getPrettyMessage() {
        String _message = getMessage();
        String _messageKey = getMessageKey();
        
        StringBuilder pretty = new StringBuilder();
        if(_message != null) {
            pretty.append(_message);
        }
        if(_messageKey != null && !"".equals(_messageKey.trim())) {
            pretty.append(" (");
            pretty.append(_messageKey);
            pretty.append(")");
        }
        return pretty.toString();
    }

}
