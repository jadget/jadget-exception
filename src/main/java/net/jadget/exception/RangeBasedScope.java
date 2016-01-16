package net.jadget.exception;


/**
 * Provides a {@link Scope} based on a fixed range of error codes.
 */
public class RangeBasedScope implements Scope {
    /**
     * Creates a new scope with the range [start,end].
     * <p>
     * 
     * Both start and end are included in the scope!
     * 
     * @param start the first code in the range
     * @param end the last code in the range, must be &gt;= start
     */
    public RangeBasedScope(int start, int end) {
        if (end < start) {
            throw new IllegalArgumentException("end must be >= start");
        }
        
        m_start = start;
        m_end = end;
    }

    
    @Override
    public int toGlobalIntValue(int localCode) {
        if (localCode > m_end - m_start) {
            throw new IndexOutOfBoundsException("code: " + localCode + ", range: " + (m_end - m_start));
        }
        
        return m_start + localCode;
    }
    
    
    private final int m_start;
    private final int m_end;
}
