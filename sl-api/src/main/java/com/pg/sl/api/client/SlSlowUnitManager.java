package com.pg.sl.api.client;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;

@ThriftService("SlSlowUnitManager")
public interface SlSlowUnitManager
{
    @ThriftService("SlSlowUnitManager")
    public interface Async
    {
        @ThriftMethod(value = "calculate")
        ListenableFuture<Map<String, String>> calculate(
            @ThriftField(value=1, name="unitNameList", requiredness=Requiredness.NONE) final List<String> unitNameList,
            @ThriftField(value=2, name="contextMap", requiredness=Requiredness.NONE) final Map<String, String> contextMap
        );
    }
    @ThriftMethod(value = "calculate")
    Map<String, String> calculate(
        @ThriftField(value=1, name="unitNameList", requiredness=Requiredness.NONE) final List<String> unitNameList,
        @ThriftField(value=2, name="contextMap", requiredness=Requiredness.NONE) final Map<String, String> contextMap
    ) throws org.apache.thrift.TException;
}