package net.jadget.exception;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.helpers.MessageFormatter;


public class JadgetException extends RuntimeException {
    public JadgetException() {
        super();
    }


    public JadgetException(String msg, Throwable cause) {
        super(msg, cause);
    }


    public JadgetException(String msg) {
        super(msg);
    }


    public JadgetException(Throwable cause) {
        super(cause);
    }
    
    
    public JadgetException(String msg, Object... args) {
        this(MessageFormatter.arrayFormat(msg, args).getMessage(), getThrowable(args));
    }


    private static Throwable getThrowable(Object... args) {
        if (args.length == 0) {
            return null;
        }
        
        Object last = args[args.length -1];
        
        if (last instanceof Throwable) {
            return (Throwable) last;
        }
        else {
            return null;
        }
    }
    
    
    public JadgetException with(String key, Object value) {
        m_data.put(key, value);
        
        return this;
    }
    
    
    public JadgetException errorCode(ErrorCode errorCode) {
        m_errorCode = errorCode;
        
        return this;
    }
    
    
    public ErrorCode getErrorCode() {
        return m_errorCode;
    }
    
    
    public boolean hasErrorCode() {
        return null != m_errorCode;
    }
    
    
    public Object get(String key) {
        return m_data.get(key);
    }
    
    
    public Map<String,Object> getContext() {
        return m_data;
    }
    
    
    private ErrorCode m_errorCode;
    
    
    private final Map<String,Object> m_data = new HashMap<>();
}
