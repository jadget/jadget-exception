package net.jadget.exception.util;

import java.io.PrintStream;

import net.jadget.exception.ErrorCode;


public final class ErrorCodePrinter {
    
    @SafeVarargs
    public static void print(PrintStream out, Enum<? extends ErrorCode>[] ... codeSets) {
        for (Enum<? extends ErrorCode>[] codeSet : codeSets) {
            for (Enum<? extends ErrorCode> code : codeSet) {
                out.println(ErrorCodeUtil.formatErrorCode((ErrorCode) code));
            }
        }
    }
    
    
    /**
     * Pure static class.
     */
    private ErrorCodePrinter() {
    }
}
