package net.jadget.exception;

import javax.annotation.Nonnull;


/**
 * An ErrorCode provides an unique, "human readable" numeric error code.
 * <p>
 * 
 * ErrorCodes are intended for the user of the application. They provide localization agnostic numeric identifiers for
 * errors. They can be used by the user for searching further information and help using Internet search engines or
 * similar tools.
 * 
 * 
 * <h3>Implementation</h3>
 * 
 * ErrorCodes are intended to be implemented using an enum to guarantee uniqueness (but applications are free to do
 * differently).
 * 
 * 
 * <h3>Ranges</h3>
 * 
 * To keep the different modules of an application independent from each other it is often a good practice to assign
 * error codes not from a single central location but let each module define its own error codes. To simplify the
 * creation of globally unique codes, this class supports <i>ranges</i>. Ranges are non-overlapping blocks of error 
 * codes that can be assigned to the different modules of an application. Each module is free to assign the codes from 
 * its range as it sees fit without having to worry about the codes assigned by a different module.
 * <p>
 * 
 * An example for a scheme following this approach could be: the UI system gets the range <code>200-299</code>, the
 * simulation system the range <code>300-399</code> etc.
 * <p>
 * 
 * It is highly recommended to use the {@link ErrorCodes#verifyValid(Enum[]...)} method as part of the application's
 * unit test suite to verify ranges are not overlapping and error codes are not leaking over range boundaries.
 * 
 * 
 * <h3>Usage / Evaluation</h3>
 * 
 * To minimize the amount of necessary boilerplate code in implementing classes, this interface provides only a bare 
 * minimum of methods. The {@link ErrorCodes} class provides utility methods for dealing with error codes.
 */
public interface ErrorCode {
    /**
     * Returns the offset of this error code relative to the range returned by {@link #getRange()}.
     * <p>
     * 
     * Use {@link ErrorCodes#toNumber(ErrorCode)} to get the globally unique number associated with an error code.
     * 
     * @return the numeric value, always >= 0
     */
    int offset();
    
    
    /**
     * Returns a human readable, short name for the error.
     * 
     * @return the name, never null
     */
    @Nonnull
    String getName();
    
    
    /**
     * Returns the range this error code belongs to.
     * 
     * @return the range, never null
     */
    @Nonnull
    Range getRange();
}
