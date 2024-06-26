package org.egeiper.util;

@SuppressWarnings("PMD.MissingSerialVersionUID")
public class FileOrResourceNotFoundException extends RuntimeException {
    public FileOrResourceNotFoundException(final String message, final Exception e) {
        super(message, e);
    }
}
