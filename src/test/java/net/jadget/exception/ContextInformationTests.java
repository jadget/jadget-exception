package net.jadget.exception;

import org.junit.Test;

import net.jadget.exception.example.MyErrors;


public class ContextInformationTests {
    @Test
    public void test() {
        new JadgetException("something {} happend", "bad")
            .withErrorCode(MyErrors.SOME_ERROR)
            .with("name", "value")
            .with("other", "different")
            .printStackTrace();
    }
}
