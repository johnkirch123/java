
package edu.frontrange.csc240.a7;

/**
 * This class is basically a wrapper on the superclass Exception, and has
 * the same constructors, and inherits the same methods.
 *
 * @author			Dr. Bruce K. Haddon, Instructor
 * @version			2.0, 2016-08-22, CSC-240 Assignment 7, per the Assignment
 *					Instructions
 */
@SuppressWarnings("serial")
public class StudentException extends Exception
{
/**
 * Constructs a new exception with <code>null</code> as its detail message.
 * The cause is not initialized, and may subsequently be initialized by a
 * call to {@link #initCause}.
 *
 * @see	Exception
 */
public StudentException()
{
	super();
}

/**
 * Constructs a new exception with the specified detail message.  The
 * cause is not initialized, and may subsequently be initialized by
 * a call to {@link #initCause}.
 *
 * @param message		the detail message. The detail message is saved for
 *						later retrieval by the {@link #getMessage()} method.
 *
 * @see	Exception
 */
public StudentException(String message)
{
	super(message);
}

/**
 * Constructs a new exception with the specified detail message and cause.
 * <p>
 * Note that the detail message associated with <code>cause</code> is
 * <i>not</i> automatically incorporated into this exception's detail message.
 *
 * @param message		the detail message (which is saved for later retrieval
 *						by the {@link #getMessage()} method).
 * @param cause			the cause (which is saved for later retrieval by the
 *						{@link #getCause()} method).  (A <tt>null</tt> value is
 *						permitted, and indicates that the cause is nonexistent
 *						or unknown.)
 * @since  1.4
 *
 * @see	Exception
 */
public StudentException(String message, Throwable cause)
{
	super(message, cause);
}

/**
 * Constructs a new exception with the specified cause and a detail message of
 * <tt>(cause==null ? null : cause.toString())</tt> (which typically contains
 * the class and detail message of <tt>cause</tt>). This constructor is useful
 * for exceptions that are little more than wrappers for other throwables.
 *
 * @param cause			the cause (which is saved for later retrieval by the
 *						{@link #getCause()} method).  (A <tt>null</tt> value is
 *						permitted, and indicates that the cause is nonexistent
 *						or unknown.)
 * @since  1.4
 *
 * @see	Exception
 */
public StudentException(Throwable cause)
{
	super(cause);
}
}
