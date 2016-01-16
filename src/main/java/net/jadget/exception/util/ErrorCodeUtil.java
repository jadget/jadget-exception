package net.jadget.exception.util;

import net.jadget.exception.ErrorCode;
import net.jadget.exception.ScopedErrorCode;


public final class ErrorCodeUtil {
    
    public static int getGlobalIntValue(ErrorCode errorCode) {
        if (errorCode instanceof ScopedErrorCode) {
            return ((ScopedErrorCode) errorCode).getScope().toGlobalIntValue(errorCode.getIntValue());
        }
        else {
            return errorCode.getIntValue();
        }
    }
    
    
    public static String formatErrorCode(ErrorCode errorCode) {
        return errorCode.getName() + "(" + getGlobalIntValue(errorCode) + ")";
    }
    

    /**
     * Pure static class.
     */
    private ErrorCodeUtil() {
    }
}
