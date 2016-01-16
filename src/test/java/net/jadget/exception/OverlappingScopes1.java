package net.jadget.exception;

import net.jadget.exception.ErrorCode;
import net.jadget.exception.ErrorCodes;
import net.jadget.exception.Range;


public enum OverlappingScopes1 implements ErrorCode {
    SOME_ERROR(1, "SomeError")
    
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

    private OverlappingScopes1(int code, String name) {
        this.code = code;
        this.name = name;
    }
    
    private final int code;
    private final String name;
    private static final Range scope = new Range(100, 199);
}
