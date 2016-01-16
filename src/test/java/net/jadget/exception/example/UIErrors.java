package net.jadget.exception.example;

import net.jadget.exception.ErrorCode;
import net.jadget.exception.ErrorCodes;
import net.jadget.exception.Range;


/**
 * Error codes for UI system.
 * <p>
 * 
 * UI error are in the 2xx range.
 */
public enum UIErrors implements ErrorCode {
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
    public int offset() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Range getRange() {
        return scope;
    }
    
    @Override
    public String toString() {
        return ErrorCodes.formatErrorCode(this);
    }
    
    private UIErrors(int code, String name) {
        this.code = code;
        this.name = name;
    }
    
    private final int code;
    private final String name;
    private static final Range scope = new Range(200, 299);
}
