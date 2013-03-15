package com.sohu.sur.filter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * User: guoyong
 * Date: 11-3-31 下午1:37
 */
public class CacheResponseWrapper extends HttpServletResponseWrapper {

    protected HttpServletResponse origResponse = null;
    protected OutputStream cacheContent = null;
    protected ServletOutputStream stream = null;
    protected PrintWriter writer = null;

    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @throws IllegalArgumentException if the response is null
     */
    public CacheResponseWrapper(HttpServletResponse response, OutputStream cacheContent) {
        super(response);
        origResponse = response;
        this.cacheContent = cacheContent;

    }

    @Override
    public void flushBuffer() throws IOException {
        stream.flush();
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (writer != null) {
            return writer;
        }

        if (stream != null) {
            throw new IllegalStateException(
                    "getOutputStream() has already been called!");
        }

        stream = createOutputStream();
        writer = new PrintWriter(new OutputStreamWriter(stream, origResponse.getCharacterEncoding()));

        return writer;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (writer != null) {
            throw new IllegalStateException(
                    "getWriter() has already been called!");
        }

        if (stream == null)
            stream = createOutputStream();
        return stream;
    }

    public ServletOutputStream createOutputStream()
            throws IOException {
        return new CacheResponseStream(cacheContent);
    }
}
