package com.sohu.sur.filter;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * User: guoyong
 * Date: 11-3-31 下午1:48
 */
public class CacheResponseStream extends ServletOutputStream {

    protected boolean closed = false;
    protected OutputStream cacheContent = null;

    public CacheResponseStream(OutputStream cacheContent) throws IOException {
        super();
        closed = false;
        this.cacheContent = cacheContent;
    }

    @Override
    public void close() throws IOException {
        if (closed) {
            throw new IOException(
                    "This output stream has already been closed");
        }
        cacheContent.close();
        closed = true;
    }

    @Override
    public void flush() throws IOException {
        if (closed) {
            throw new IOException(
                    "Cannot flush a closed output stream");
        }
        cacheContent.flush();
    }

    @Override
    public void write(int b) throws IOException {
        if (closed) {
            throw new IOException(
                    "Cannot write to a closed output stream");
        }
        cacheContent.write((byte) b);
    }

    @Override
    public void write(byte b[]) throws IOException {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte b[], int off, int len)
            throws IOException {
        if (closed) {
            throw new IOException(
                    "Cannot write to a closed output stream");
        }
        cacheContent.write(b, off, len);
    }
}
