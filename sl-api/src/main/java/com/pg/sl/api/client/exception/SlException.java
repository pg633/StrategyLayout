package com.pg.sl.api.client.exception;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftField.Recursiveness;
import java.util.*;

@ThriftStruct("SlException")
public final class SlException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public SlException() {
    }

    private int errorCode;

    @ThriftField(value=1, name="errorCode", requiredness=Requiredness.NONE)
    public int getErrorCode() { return errorCode; }

    @ThriftField
    public void setErrorCode(final int errorCode) { this.errorCode = errorCode; }

    private String message;

    @ThriftField(value=2, name="message", requiredness=Requiredness.NONE)
    public String getMessage() { return message; }

    @ThriftField
    public void setMessage(final String message) { this.message = message; }
}
