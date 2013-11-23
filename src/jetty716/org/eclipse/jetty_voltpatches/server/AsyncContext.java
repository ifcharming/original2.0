// ========================================================================
// Copyright (c) 2004-2009 Mort Bay Consulting Pty. Ltd.
// ------------------------------------------------------------------------
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// and Apache License v2.0 which accompanies this distribution.
// The Eclipse Public License is available at 
// http://www.eclipse.org/legal/epl-v10.html
// The Apache License v2.0 is available at
// http://www.opensource.org/licenses/apache2.0.php
// You may elect to redistribute this code under either of these licenses. 
// ========================================================================

package org.eclipse.jetty_voltpatches.server;

import javax.servlet_voltpatches.ServletContext;
import javax.servlet_voltpatches.ServletRequest;
import javax.servlet_voltpatches.ServletResponse;

import org.eclipse.jetty_voltpatches.continuation.ContinuationListener;

/* temporary interface in anticipation of servlet 3.0 */
public interface AsyncContext 
{
    static final String ASYNC_REQUEST_URI = "javax.servlet_voltpatches.async.request_uri";
    static final String ASYNC_CONTEXT_PATH = "javax.servlet_voltpatches.async.context_path";
    static final String ASYNC_PATH_INFO = "javax.servlet_voltpatches.async.path_info";
    static final String ASYNC_SERVLET_PATH = "javax.servlet_voltpatches.async.servlet_path";
    static final String ASYNC_QUERY_STRING = "javax.servlet_voltpatches.async.query_string";

    public ServletRequest getRequest();
    public ServletResponse getResponse();
    public boolean hasOriginalRequestAndResponse();
    public void dispatch();
    public void dispatch(String path);
    public void dispatch(ServletContext context, String path);
    public void complete();
    public void start(Runnable run);
    public void setTimeout(long ms);
    public void addContinuationListener(ContinuationListener listener);
}

