//
// ========================================================================
// Copyright (c) 1995-2020 Mort Bay Consulting Pty Ltd and others.
//
// This program and the accompanying materials are made available under
// the terms of the Eclipse Public License 2.0 which is available at
// https://www.eclipse.org/legal/epl-2.0
//
// This Source Code may also be made available under the following
// Secondary Licenses when the conditions for such availability set
// forth in the Eclipse Public License, v. 2.0 are satisfied:
// the Apache License v2.0 which is available at
// https://www.apache.org/licenses/LICENSE-2.0
//
// SPDX-License-Identifier: EPL-2.0 OR Apache-2.0
// ========================================================================
//

package org.eclipse.jetty.websocket.javax.common.messages;

import java.lang.invoke.MethodHandle;
import javax.websocket.Decoder;

import org.eclipse.jetty.util.Callback;
import org.eclipse.jetty.util.log.Log;
import org.eclipse.jetty.util.log.Logger;
import org.eclipse.jetty.websocket.core.Frame;
import org.eclipse.jetty.websocket.javax.common.JavaxWebSocketSession;
import org.eclipse.jetty.websocket.javax.common.MessageSink;

public abstract class DecodedMessageSink<T extends Decoder> extends AbstractMessageSink
{
    protected final Logger logger;
    private final T decoder;
    private final MethodHandle rawMethodHandle;
    private final MessageSink rawMessageSink;

    public DecodedMessageSink(JavaxWebSocketSession session, T decoder, MethodHandle methodHandle)
        throws NoSuchMethodException, IllegalAccessException
    {
        super(session, methodHandle);
        this.logger = Log.getLogger(this.getClass());
        this.decoder = decoder;
        this.rawMethodHandle = newRawMethodHandle();
        this.rawMessageSink = newRawMessageSink(session, rawMethodHandle);
    }

    protected abstract MethodHandle newRawMethodHandle()
        throws NoSuchMethodException, IllegalAccessException;

    protected abstract MessageSink newRawMessageSink(JavaxWebSocketSession session, MethodHandle rawMethodHandle);

    public T getDecoder()
    {
        return decoder;
    }

    @Override
    public void accept(Frame frame, Callback callback)
    {
        this.rawMessageSink.accept(frame, callback);
    }
}
