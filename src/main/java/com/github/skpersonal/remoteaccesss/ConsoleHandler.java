package com.github.skpersonal.remoteaccesss;

import java.io.PrintWriter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class ConsoleHandler extends Handler {
    private PrintWriter writer;

    ConsoleHandler(PrintWriter writer) {
        this.writer = writer;
    }

    @Override
    public void publish(LogRecord record) {
        writer.println(record.getMessage());
    }

    @Override
    public void flush() {

    }

    @Override
    public void close() throws SecurityException {

    }
}
