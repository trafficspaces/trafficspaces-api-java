/**
 ** Copyright (c) 2011 Trafficspaces Inc.
 ** 
 ** Permission is hereby granted, free of charge, to any person obtaining a copy
 ** of this software and associated documentation files (the "Software"), to deal
 ** in the Software without restriction, including without limitation the rights
 ** to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 ** copies of the Software, and to permit persons to whom the Software is
 ** furnished to do so, subject to the following conditions:
 ** 
 ** The above copyright notice and this permission notice shall be included in
 ** all copies or substantial portions of the Software.
 ** 
 ** THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 ** IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 ** FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 ** AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 ** LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 ** OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 ** THE SOFTWARE.
 ** 
 ** Reference Documentation: http://support.trafficspaces.com/kb/api/api-introduction
 **/
package com.trafficspaces.api.controller;

import java.io.PrintStream;
import java.io.PrintWriter;

public class TrafficspacesAPIException extends Exception {
 	/**
     * The root cause of the exception
     */
	private Throwable rootCause;

    /**
     * Indicates whether the "root cause" exception is printed in a stack trace.
     */
    private boolean canPrintRootCause = true;

    /**
     * The default message - if none is specified
     */
	private static final String DEFAULT_MESSAGE =
    		"An unidentified system error has occured";

	/**
     * Creates a new instance of the <code>TrafficspacesAPIException</code> class.
     */
    public TrafficspacesAPIException() {
    	this(null, DEFAULT_MESSAGE);
    }

	/**
     * Creates a new instance of the <code>TrafficspacesAPIException</code> class.
     */
    public TrafficspacesAPIException(String reason) {
        this(null, (reason != null) ? reason : DEFAULT_MESSAGE);
    }

	/**
     * Creates a new instance of the <code>TrafficspacesAPIException</code> class.
     */    
    public TrafficspacesAPIException(Throwable cause) {
        this(cause, cause.getMessage());
    }
    
	/**
     * Creates a new instance of the <code>TrafficspacesAPIException</code> class.
     */    
    public TrafficspacesAPIException(Throwable cause, String reason) {
        super(reason);
        rootCause = cause;
    }

    /**
     * @returns the root cause of the system exception
     */
	public final Throwable getRootCause() {
    	return rootCause;
    }

	public Throwable getCause() {
		return getRootCause();
	}
	
    public void printStackTrace() {
        synchronized (System.err) {
            super.printStackTrace();
            if (rootCause != null && canPrintRootCause) {
                canPrintRootCause = false; // curb potential recursion
                rootCause.printStackTrace();
            }
        }
    }

    public void printStackTrace(PrintStream out) {
        synchronized (out) {
            super.printStackTrace(out);
            if (rootCause != null && canPrintRootCause) {
                canPrintRootCause = false; // curb potential recursion
                rootCause.printStackTrace(out);
            }
        }
    }

    public void printStackTrace(PrintWriter out) {
        synchronized (out) {
            super.printStackTrace(out);
            if (rootCause != null && canPrintRootCause) {

                canPrintRootCause = false; // curb potential recursion
                rootCause.printStackTrace(out);
            }
        }
    }

    public void setCanPrintRootCause(boolean canPrintRootCause) {
        this.canPrintRootCause = canPrintRootCause;
    }

    public boolean getCanPrintRootCause() {
        return canPrintRootCause;
    }
}
