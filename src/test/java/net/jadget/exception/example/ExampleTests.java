package net.jadget.exception.example;

import org.junit.Test;

import net.jadget.exception.ErrorCodes;


public class ExampleTests {
    @Test
    public void testExampleErrorCodesAreValid() {
        ErrorCodes.verifyValid(MyErrors.values(), UIErrors.values());
    }
}
