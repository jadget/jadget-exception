package net.jadget.exception;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public final class ErrorCodes {
    
    public static int toNumber(ErrorCode errorCode) {
        Range scope = errorCode.getRange();
        
        if (null == scope) {
            return -1;
        }
        
        return scope.offsetToNumber(errorCode.offset());
    }
    
    
    public static String formatErrorCode(ErrorCode errorCode) {
        int number = toNumber(errorCode);
        
        return errorCode.getName() + "(" + (number == -1 ? "<invalid>" : number) + ")";
    }
    
    
    @SafeVarargs
    public static void print(PrintStream out, Enum<? extends ErrorCode>[] ... codeSets) {
        for (Enum<? extends ErrorCode>[] codeSet : codeSets) {
            for (Enum<? extends ErrorCode> code : codeSet) {
                out.println(ErrorCodes.formatErrorCode((ErrorCode) code));
            }
        }
    }
    
    
    /**
     * Verifies the passed in enums are valid.
     * <p>
     * 
     * This method is intended to be used in unit tests and analyzes the passed in enums. It checks that
     * <ul>
     *  <li>error codes specified by the enums are unique
     *  <li>enums return a valid range (not null)
     *  <li>ranges used by the enums do not overlap
     * </ul>
     * 
     * If any of the checks fails, this method will throw an {@link RuntimeException}
     * 
     * @param enums the enums to check
     */
    @SafeVarargs
    public static void verifyValid(Enum<? extends ErrorCode>[] ... enums) {
        Set<Range> ranges = new HashSet<>();
        Map<Integer,ErrorCode> codes = new HashMap<>();
        
        for (Enum<? extends ErrorCode>[] codeSet : enums) {
            for (Enum<? extends ErrorCode> code : codeSet) {
                ErrorCode ec = (ErrorCode) code;
                
                if (null == ec.getRange()) {
                    throw new RuntimeException("error code invalid (null returned from getScope()): " + ec);
                }
                
                if (ec.getRange() instanceof Range) {
                    ranges.add((Range) ec.getRange());
                }
                
                int globalIntValue = ErrorCodes.toNumber(ec);
                
                if (codes.containsKey(globalIntValue)) {
                    throw new RuntimeException("duplicate error code " + globalIntValue + " provided by " + ec + 
                            " and " + codes.get(globalIntValue));
                }
                
                codes.put(globalIntValue, ec);
            }
        }
        
        for (Range range : ranges) {
            for (Range probe : ranges) {
                if (range == probe) {
                    continue;
                }
                
                if (range.overlaps(probe)) {
                    throw new RuntimeException("overlapping ranges: " + range + " and " + probe);
                }
            }
        }
    }
    

    /**
     * Pure static class.
     */
    private ErrorCodes() {
    }
}
