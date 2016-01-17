package net.jadget.exception;

import org.junit.Test;

import net.jadget.exception.example.MyErrors;

import static org.junit.Assert.*;


/**
 * Tests for {@link JadgetException}.
 */
public class JadgetExceptionTests {
    @Test
    public void testErrorCodeNameIsAddedToMessage() {
        Exception e = new JadgetException().withErrorCode(MyErrors.SOME_ERROR);
        
        assertTrue(e.getMessage().contains(MyErrors.SOME_ERROR.getName()));
    }
    
    
    @Test
    public void testErrorCodeNumberIsAddedToMessage() {
        Exception e = new JadgetException().withErrorCode(MyErrors.SOME_ERROR);
        
        assertTrue(e.getMessage().contains(Integer.toString(ErrorCodes.toNumber(MyErrors.SOME_ERROR))));
    }
    
    
    @Test
    public void testGetErrorCode() {
        JadgetException e = new JadgetException().withErrorCode(MyErrors.SOME_ERROR);
        
        assertTrue(e.getErrorCode() == MyErrors.SOME_ERROR);
        
        
        JadgetException e2 = new JadgetException();
        
        assertNull(e2.getErrorCode());
    }
    
    
    @Test
    public void testHasErrorCode() {
        JadgetException e = new JadgetException().withErrorCode(MyErrors.SOME_ERROR);
        
        assertTrue(e.hasErrorCode());
        
        
        JadgetException e2 = new JadgetException();
        
        assertFalse(e2.hasErrorCode());
    }
    
    
    @Test
    public void testContextIsAddedToMessage() {
        Exception e = new JadgetException().with("someKey", "someValue");
        
        assertTrue(e.getMessage().contains("someKey"));
        assertTrue(e.getMessage().contains("someValue"));
    }
    
    
    @Test
    public void testGetContext() {
        JadgetException e = new JadgetException().with("someKey", "someValue");
        
        assertTrue(e.getContext().get("someKey").equals("someValue"));
    }
    
    
    @Test
    public void testPlaceholderIsReplaced() {
        Exception e = new JadgetException("something {} happened", "bad");
        
        assertEquals("something bad happened", e.getMessage());
    }
    
    
    @Test
    public void testPlaceholderWithCause() {
        Exception cause = new RuntimeException();
        Exception e = new JadgetException("something {} happened", "bad", cause);
        
        assertTrue(e.getCause() == cause);
    }
}
