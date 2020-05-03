package com.pg.sl.api.client;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;

@ThriftService("SlUnitManager")
public interface SlUnitManager
{
    @ThriftService("SlUnitManager")
    public interface Async
    {
        @ThriftMethod(value = "calculate")
        ListenableFuture<Map<String, String>> calculate(
            @ThriftField(value=1, name="unitNameList", requiredness=Requiredness.NONE) final List<String> unitNameList,
            @ThriftField(value=2, name="contextMap", requiredness=Requiredness.NONE) final Map<String, String> contextMap
        );

        @ThriftMethod(value = "batchCalculate")
        ListenableFuture<List<String>> batchCalculate(
            @ThriftField(value=1, name="unitNameList", requiredness=Requiredness.NONE) final List<String> unitNameList,
            @ThriftField(value=2, name="contextMapList", requiredness=Requiredness.NONE) final List<Map<String, String>> contextMapList
        );

        @ThriftMethod(value = "store")
        ListenableFuture<String> store(
            @ThriftField(value=1, name="unitNameList", requiredness=Requiredness.NONE) final List<String> unitNameList,
            @ThriftField(value=2, name="contextMap", requiredness=Requiredness.NONE) final Map<String, String> contextMap
        );
    }
    @ThriftMethod(value = "calculate")
    Map<String, String> calculate(
        @ThriftField(value=1, name="unitNameList", requiredness=Requiredness.NONE) final List<String> unitNameList,
        @ThriftField(value=2, name="contextMap", requiredness=Requiredness.NONE) final Map<String, String> contextMap
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "batchCalculate")
    List<String> batchCalculate(
        @ThriftField(value=1, name="unitNameList", requiredness=Requiredness.NONE) final List<String> unitNameList,
        @ThriftField(value=2, name="contextMapList", requiredness=Requiredness.NONE) final List<Map<String, String>> contextMapList
    ) throws org.apache.thrift.TException;

    @ThriftMethod(value = "store")
    String store(
        @ThriftField(value=1, name="unitNameList", requiredness=Requiredness.NONE) final List<String> unitNameList,
        @ThriftField(value=2, name="contextMap", requiredness=Requiredness.NONE) final Map<String, String> contextMap
    ) throws org.apache.thrift.TException;
}