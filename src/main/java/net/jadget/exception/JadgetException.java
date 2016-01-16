package net.jadget.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import org.slf4j.helpers.MessageFormatter;

import sun.java2d.loops.GeneralRenderer;


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
    
    
    /**
     * Adds the specified name/value pair as additional context information to this exception.
     * 
     * @param key
     * @param value
     * @return
     */
    public JadgetException with(String key, Object value) {
        m_data.put(key, value);
        
        return this;
    }
    
    
    public JadgetException withErrorCode(ErrorCode errorCode) {
        m_errorCode = errorCode;
        
        return this;
    }
    
    
    public ErrorCode getErrorCode() {
        return m_errorCode;
    }
    
    
    public boolean hasErrorCode() {
        return null != m_errorCode;
    }
    
    
    
    /**
     * Returns the context information stored under the specified key.
     * <p>
     * 
     * @param key the key, must not be null
     * @return the stored context information, or null if the key is not found
     * @see #with(String, Object)
     */
    public Object get(@Nonnull String key) {
        return m_data.get(key);
    }
    
    
    /**
     * Returns the context information added to this exception.
     * <p>
     * 
     * This method returns the map backing this exception, i.e., changes to the map will affect the context information
     * stored in the exception
     * 
     * @return a map of name/value pairs containing the context information added to this exception, never null
     */
    @Nonnull
    public Map<String,Object> getContext() {
        return m_data;
    }
    
    
    @Override
    public String getMessage() {
        return formatErrorCode() + super.getMessage() + formatAttachedValues();
    }
    
    
    private String formatErrorCode() {
        if (hasErrorCode()) {
            return getErrorCode().toString() + ": ";
        }
        
        return "";
    }
    
    
    private String formatAttachedValues() {
        if (m_data.isEmpty()) {
            return "";
        }
        
        StringBuilder buf = new StringBuilder(" (");
        
        List<String> keys = new ArrayList<>(m_data.keySet());
        Collections.sort(keys);
        
        boolean first = true;
        for (String key : keys) {
            if (first) {
                first = false;
            }
            else {
                buf.append(", ");
            }
            
            buf.append(key).append(" = ").append(m_data.get(key));
        }
        
        return buf.append(")").toString();
    }
    
    
    private ErrorCode m_errorCode;
    
    
    private final Map<String,Object> m_data = new HashMap<>();
}
