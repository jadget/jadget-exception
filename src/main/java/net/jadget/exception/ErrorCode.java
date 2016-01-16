package net.jadget.exception;

import javax.annotation.Nonnull;


/**
 * An ErrorCode provides an unique, "user understandable" identifier for an error.
 * <p>
 * 
 * ErrorCodes are intended for the user of the application. They provide localization agnostic "identifiers" for errors.
 * They can also be used by the user for searching further information and help using Internet search engines or similar
 * tools.
 * <p>
 * 
 * ErrorCode are intended to be implemented using an enum to guarantee uniqueness (but applications are free to do this
 * differently).
 * 
 * 
 * <h3>Error Scopes</h3>
 * 
 * To keep the different modules of an application independent from each other it is often a good practice to assign
 * error codes not from a single central enum but let each module define its own error codes. To ensure that error codes
 * are still globally unique, this class supports scopes, i.e., ranges of error codes that are guaranteed to be
 * non-overlapping. Each subsystem is then free to assign the codes from its scope/ range as it sees fit.
 * <p>
 * 
 * An example for a scheme following this approach could be: the UI system gets the scope <code>200-299</code>, the
 * simulation system the scope <code>300-399</code> etc.
 * <p>
 */
public interface ErrorCode {
    /**
     * Returns an unique integer value for the error.
     * 
     * @return the error identifier
     */
    int getIntValue();
    
    
    /**
     * Returns a human readable, short name for the error.
     * 
     * @return the name, never null
     */
    @Nonnull
    String getName();
}
