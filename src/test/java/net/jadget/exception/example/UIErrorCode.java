package net.jadget.exception.example;

import net.jadget.exception.RangeBasedScope;
import net.jadget.exception.Scope;
import net.jadget.exception.ScopedErrorCode;
import net.jadget.exception.util.ErrorCodeUtil;


/**
 * Error codes for UI system.
 * <p>
 * 
 * UI error are in the 2xx range.
 */
public enum UIErrorCode implements ScopedErrorCode {
    /**
     * Initialization of UI failed.
     */
    INIT_FAILED(1, "InitFailed"),
    
    /**
     * Required resource is missing.
     */
    RESOURCE_MISSING(2, "ResourceMissing")
    
    ;

    @Override
    public int getIntValue() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Scope getScope() {
        return scope;
    }
    
    @Override
    public String toString() {
        return ErrorCodeUtil.formatErrorCode(this);
    }
    
    private UIErrorCode(int code, String name) {
        this.code = code;
        this.name = name;
    }
    
    private final int code;
    private final String name;
    private final Scope scope = new RangeBasedScope(200, 299);
}
