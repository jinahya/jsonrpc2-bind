package com.github.jinahya.jsonrpc.bind.v1.examples.jsonrpc_org;

/*-
 * #%L
 * jsonrpc-bind
 * %%
 * Copyright (C) 2019 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.jinahya.jsonrpc.bind.v1.ResponseTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PostMessageResponseTest extends ResponseTest<PostMessageResponse, Integer, Object, Long> {

    PostMessageResponseTest() {
        super(PostMessageResponse.class, Integer.class, Object.class, Long.class);
    }

    @Override
    protected void acceptValueFromResource(final String name, final Consumer<? super PostMessageResponse> consumer)
            throws IOException {
        super.acceptValueFromResource(name, v -> {
            consumer.accept(v);
            final PostMessageRequest request = new PostMessageRequest();
            v.copyIdTo(request);
            assertEquals(v.getId(), request.getId());
        });
    }

    @Test
    void postMessage_01_response() throws IOException {
        acceptValueFromResource(
                "postMessage_01_response.json",
                v -> {
                    assertEquals(1, (int) v.getResult());
                    assertNull(v.getError());
                    assertEquals(99L, (long) v.getId());
                }
        );
    }

    @Test
    void postMessage_02_response() throws IOException {
        acceptValueFromResource(
                "postMessage_02_response.json",
                v -> {
                    assertEquals(1, (int) v.getResult());
                    assertNull(v.getError());
                    assertEquals(101L, (long) v.getId());
                }
        );
    }
}
