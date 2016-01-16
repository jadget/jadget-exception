package net.jadget.exception;

import javax.annotation.Nonnull;


/**
 * Extended {@link ErrorCode} with support for scopes/namespaces.
 * <p>
 * 
 * To keep the different modules of an application independent from each other it is often a good practice to assign
 * error codes not from a single central enum but let each module define its own error codes. To ensure that error codes
 * are still globally unique, this class extends ErrorCode with scopes, i.e., ranges of error codes that are guaranteed
 * to be non-overlapping. Each subsystem is then free to assign the codes from its range as it sees fit.
 * <p>
 * 
 * An example for a scheme following this approach could be: the UI system gets the scope <code>200-299</code>, the
 * simulation system the scope <code>300-399</code> etc.
 * <p>
 * 
 * This library comes with built-in support for scopes based on ranges (provided by {@link RangeBasedScope}), other
 * strategies for generating globally unique identifiers can be provided using a custom {@link Scope} implementation.
 */
public interface ScopedErrorCode extends ErrorCode {
    /**
     * Returns the scope this error code belongs to.
     * 
     * @return the scope, never null
     */
    @Nonnull
    Scope getScope();
}
