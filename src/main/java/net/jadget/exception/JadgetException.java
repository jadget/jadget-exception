package net.jadget.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import org.slf4j.helpers.MessageFormatter;


/**
 * Extended RuntimeException with support for slf4j style placeholders, error codes and context information.
 *
 * <h3>Placeholders</h3>
 * 
 * The {@link #JadgetException(String, Object[])} constructor supports slf4j style error messages with placeholders.
 * <p>
 * 
 * Example:
 * 
 * <pre>
 * <code>new JadgetException("something {} happened", "bad");</code>
 * </pre>
 * 
 * will create an exception with a message of
 * 
 * <pre>
 * <code>"something bad happened"</code>
 * </pre>
 * 
 * The last parameter will be interpreted as cause if it is an instance of Throwable.
 * 
 * 
 * <h3>Error Codes</h3>
 * 
 * The {@link #withErrorCode(ErrorCode)} method can be used to associate an error code with an exception. The error code
 * will be added to the exception's message and is also available via the {@link #getErrorCode()} method. The latter is
 * for example useful to create localized messages for an exception. See the documentation of {@link ErrorCode} for more
 * details on how to create error codes.
 * 
 * 
 * <h3>Context Information</h3>
 * 
 * Additional information (name/value pairs) can be attached to exceptions via the {@link #with(String, Object)} method.
 * This is useful to provide additional context for an exception, e.g., the line number in which an error occurred while
 * parsing a file. This information is added to the error message and also available via {@link #getContext()} for
 * further analysis.
 */
@SuppressWarnings("serial")
public class JadgetException extends RuntimeException {
    /**
     * Creates a new exception.
     * 
     * @see RuntimeException#RuntimeException() RuntimeException()
     */
    public JadgetException() {
        super();
    }


    /**
     * Creates a new exception.
     * 
     * @see RuntimeException#RuntimeException(String,Throwable) RuntimeException(String,Throwable)
     */
    public JadgetException(String msg, Throwable cause) {
        super(msg, cause);
    }


    /**
     * Creates a new exception.
     * 
     * @see RuntimeException#RuntimeException(String) RuntimeException(String)
     */
    public JadgetException(String msg) {
        super(msg);
    }


    /**
     * Creates a new exception.
     * 
     * @see RuntimeException#RuntimeException(Throwable) RuntimeException(Throwable)
     */
    public JadgetException(Throwable cause) {
        super(cause);
    }
    
    
    /**
     * Creates a new exception.
     * <p>
     * 
     * This method supports placeholders similarly to slf4j logging functions. The last parameter will be interpreted
     * as cause if it is an instance of {@link Throwable}.
     * 
     * @param msg the message, must not be null
     * @param args values for placeholders contained in <code>msg</code>
     */
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
     * @param key the key, must not be null
     * @param value the value
     * @return this instance
     */
    public JadgetException with(@Nonnull String key, Object value) {
        if (null == key) {
            throw new IllegalArgumentException("key must not be null");
        }
        
        m_data.put(key, value);
        
        return this;
    }
    
    
    /**
     * Associates the specified error code with this exception.
     * 
     * @param errorCode the error code, will remove a previously set error code if null
     * @return this instance
     */
    public JadgetException withErrorCode(ErrorCode errorCode) {
        m_errorCode = errorCode;
        
        return this;
    }
    
    
    /**
     * Returns the error code associate with this exception.
     * 
     * @return the error code, or null if no error code is available
     */
    public ErrorCode getErrorCode() {
        return m_errorCode;
    }
    
    
    /**
     * Checks whether this exception has an associated error code.
     * 
     * @return true if an error code is available, false if not
     */
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
