package net.jadget.exception.example;

import net.jadget.exception.ErrorCode;
import net.jadget.exception.ErrorCodes;
import net.jadget.exception.Range;


/**
 * Error codes for MyApplication.
 */
public enum MyErrors implements ErrorCode {
    /**
     * Some error occurred.
     */
    SOME_ERROR(1, "SomeError"),
    
    /**
     * Another error occurred.
     */
    ANOTHER_ERROR(2, "AnotherError")
    
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

    private MyErrors(int code, String name) {
        this.code = code;
        this.name = name;
    }
    
    private final int code;
    private final String name;
    private static final Range scope = new Range(100, 199);
}
