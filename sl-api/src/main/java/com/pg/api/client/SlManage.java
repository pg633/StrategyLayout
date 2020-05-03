package com.pg.api.client;

import com.facebook.swift.codec.*;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.*;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.*;
import java.util.*;

@ThriftService("SlManage")
public interface SlManage
{
    @ThriftService("SlManage")
    public interface Async
    {
        @ThriftMethod(value = "loadAll")
        ListenableFuture<Boolean> loadAll();

        @ThriftMethod(value = "loadRedis",
                      exception = {
                          @ThriftException(type=com.pg.api.client.exception.SlException.class, id=1)
                      })
        ListenableFuture<Boolean> loadRedis();
    }
    @ThriftMethod(value = "loadAll")
    boolean loadAll() throws org.apache.thrift.TException;

    @ThriftMethod(value = "loadRedis",
                  exception = {
                      @ThriftException(type=com.pg.api.client.exception.SlException.class, id=1)
                  })
    boolean loadRedis() throws com.pg.api.client.exception.SlException, org.apache.thrift.TException;
}