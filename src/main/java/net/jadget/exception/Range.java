package net.jadget.exception;


/**
 * A Range represents a contiguous block of numerical error codes.
 * <p>
 * 
 * Ranges are used to simplify the creation of globally unique error codes, see {@link ErrorCode}.
 */
public class Range {
    /**
     * Creates a new Range with values from [start,end].
     * <p>
     * 
     * Both start and end are included in the range!
     * 
     * @param start the first code in the range
     * @param end the last code in the range, must be &gt;= start
     */
    public Range(int start, int end) {
        if (end < start) {
            throw new IllegalArgumentException("end must be >= start");
        }
        
        m_start = start;
        m_end = end;
    }

    
    public int offsetToNumber(int offset) {
        if (offset > m_end - m_start) {
            return -1;
        }
        
        return m_start + offset;
    }
    
    
    public boolean overlaps(Range other) {
        return (m_start <= other.m_start && m_end >= other.m_start) || 
                (m_end >= other.m_end && m_start <= other.m_end);
    }
    
    
    @Override
    public String toString() {
        return "Range(" + m_start + ", " + m_end + ")";
    }


    private final int m_start;
    private final int m_end;
}
