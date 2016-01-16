package net.jadget.exception;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.Test;
import static org.junit.Assert.*;

import net.jadget.exception.ErrorCodes;
import net.jadget.exception.example.MyErrors;
import net.jadget.exception.example.UIErrors;


public class ErrorCodesTests {
    @Test
    public void testPrint() throws IOException {
        try (ByteArrayOutputStream buf = new ByteArrayOutputStream(); PrintStream out = new PrintStream(buf)) {
            ErrorCodes.print(out, MyErrors.values(), UIErrors.values());
            
            String output = buf.toString(StandardCharsets.US_ASCII.name());
            
            assertTrue(output.contains(MyErrors.SOME_ERROR.getName()));
            assertTrue(output.contains(MyErrors.ANOTHER_ERROR.getName()));
            assertTrue(output.contains(UIErrors.INIT_FAILED.getName()));
            assertTrue(output.contains(UIErrors.RESOURCE_MISSING.getName()));
        }
    }
    
    
    @Test(expected = RuntimeException.class)
    public void testVerifyDetectsOverlappingRanges1() {
        ErrorCodes.verifyValid(OverlappingScopes1.values(), OverlappingScopes2.values());
    }
    
    
    @Test(expected = RuntimeException.class)
    public void testVerifyDetectsOverlappingRanges2() {
        ErrorCodes.verifyValid(OverlappingScopes2.values(), OverlappingScopes3.values());
    }
    
    
    @Test(expected = RuntimeException.class)
    public void testVerifyDetectsDuplicateErrorCodes() {
        ErrorCodes.verifyValid(InternalDuplicates.values());
    }
    
    
    @Test(expected = RuntimeException.class)
    public void testVerifyDetectsDuplicateErrorCodes2() {
        ErrorCodes.verifyValid(ExternalDuplicates1.values(), ExternalDuplicates2.values());
    }
}
