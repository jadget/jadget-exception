package net.jadget.exception.example;

import net.jadget.exception.ErrorCode;
import net.jadget.exception.util.ErrorCodeUtil;


/**
 * Error codes for MyApplication.
 */
public enum MyErrorCode implements ErrorCode {
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
    public int getIntValue() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return ErrorCodeUtil.formatErrorCode(this);
    }

    private MyErrorCode(int code, String name) {
        this.code = code;
        this.name = name;
    }
    
    private final int code;
    private final String name;
}
