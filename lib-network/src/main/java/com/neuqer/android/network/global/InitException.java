
package com.neuqer.android.network.global;


public class InitException extends IllegalStateException {
    public InitException() {
    }

    public InitException(String s) {
        super(s);
    }

    public InitException(String message, Throwable cause) {
        super(message, cause);
    }
}
