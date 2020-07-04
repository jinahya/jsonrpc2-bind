package com.github.jinahya.jsonrpc.bind.v2.spi;

/*-
 * #%L
 * jsonrpc-bind
 * %%
 * Copyright (C) 2019 - 2020 Jinahya, Inc.
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

import com.github.jinahya.jsonrpc.bind.v2.DummyJsonrpcResponseMessage;
import com.github.jinahya.jsonrpc.bind.v2.JsonrpcResponseMessage;

public class DummyJsonrpcResponseMessageService
        implements JsonrpcResponseMessageService {

    @Override
    public JsonrpcResponseMessage newInstance() {
        return new DummyJsonrpcResponseMessage();
    }

    @Override
    public JsonrpcResponseMessage fromJson(Object source) {
        return null;
    }

    @Override
    public void toJson(JsonrpcResponseMessage message, Object target) {
        // does nothing.
    }
}
