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

package org.eclipse.jetty.websocket.api;

import java.nio.ByteBuffer;

/**
 * WebSocket PING/PONG Listener interface for incoming WebSocket PING/PONG frames.
 */
public interface WebSocketPingPongListener extends WebSocketConnectionListener
{
    /**
     * A WebSocket PING has been received.
     *
     * @param payload the ping payload
     */
    void onWebSocketPing(ByteBuffer payload);

    /**
     * A WebSocket PONG has been received.
     *
     * @param payload the pong payload
     */
    void onWebSocketPong(ByteBuffer payload);
}
