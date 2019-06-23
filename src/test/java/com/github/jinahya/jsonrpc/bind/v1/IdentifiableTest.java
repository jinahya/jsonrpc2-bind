package com.github.jinahya.jsonrpc.bind.v1;

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

import com.github.jinahya.jsonrpc.bind.GsonTests;
import com.github.jinahya.jsonrpc.bind.JacksonTests;
import com.github.jinahya.jsonrpc.bind.JsonbTests;
import com.github.jinahya.jsonrpc.bind.MoshiTests;

import java.io.IOException;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

abstract class IdentifiableTest<_ObjectType extends Identifiable<IdType>, IdType> {

    IdentifiableTest(final Class<? extends _ObjectType> objectClass, final Class<? extends IdType> idClass) {
        super();
        this.objectClass = requireNonNull(objectClass, "objectClass is null");
        this.idClass = requireNonNull(idClass, "idClass is null");
    }

    protected void acceptValueFromResource(final String name, final Consumer<? super _ObjectType> consumer)
            throws IOException {
        consumer.accept(JsonbTests.fromResource(name, objectClass));
        consumer.accept(JacksonTests.readValueFromResource(name, objectClass));
        consumer.accept(GsonTests.fromResource(name, objectClass));
        consumer.accept(MoshiTests.fromResource(name, objectClass));
    }

    protected final Class<? extends _ObjectType> objectClass;

    protected final Class<? extends IdType> idClass;
}
