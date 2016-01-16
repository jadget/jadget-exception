package net.jadget.exception.util;

import org.junit.Test;

import net.jadget.exception.example.MyErrorCode;
import net.jadget.exception.example.UIErrorCode;


public class ErrorCodePrinterTest {
    @Test
    public void testPrintOnStdOut() {
        ErrorCodePrinter.print(System.out, MyErrorCode.values(), UIErrorCode.values());
    }
}
