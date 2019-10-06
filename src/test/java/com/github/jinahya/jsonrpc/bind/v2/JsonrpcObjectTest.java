package com.github.jinahya.jsonrpc.bind.v2;

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
import java.lang.reflect.Constructor;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * An abstract class for testing subclasses of {@link JsonrpcObject}.
 *
 * @param <ObjectType> subclass type parameter
 */
public abstract class JsonrpcObjectTest<ObjectType extends JsonrpcObject<?>> {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance of specified class.
     *
     * @param clazz the class from the new object is created.
     * @param <T>   object type parameter.
     * @return a new instance of specified class.
     */
    static <T> T newInstance(final Class<? extends T> clazz) {
        try {
            final Constructor<? extends T> constructor
                    = requireNonNull(clazz, "clazz is null").getDeclaredConstructor();
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance();
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     *
     * @param objectClass a class of target object to test.
     */
    JsonrpcObjectTest(final Class<? extends ObjectType> objectClass) {
        super();
        this.objectClass = requireNonNull(objectClass, "objectClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Reads a value of specified type from the resource of specified name and accepts to specified consumer.
     *
     * @param name     the resource name
     * @param consumer the consumer to be accepted with read value.
     * @throws IOException if an I/O error occurs.
     */
    protected void acceptValueFromResource(final String name, final Consumer<? super ObjectType> consumer)
            throws IOException {
        consumer.accept(JsonbTests.fromResource(name, objectClass));
        consumer.accept(JacksonTests.readValueFromResource(name, objectClass));
        consumer.accept(GsonTests.valueFromResource(name, objectClass));
        consumer.accept(MoshiTests.fromResource(name, objectClass));
    }

    /**
     * Reads a value of specified type from the resource named as given and accepts to specified consumer along with an
     * argument supplied by specified supplier.
     *
     * @param name     the name of the resource to read.
     * @param consumer the consumer.
     * @param supplier the supplier for the second argument.
     * @param <U>      second argument type parameter
     * @throws IOException if an I/O error occurs.
     */
    protected <U> void acceptValueFromResource(final String name,
                                               final BiConsumer<? super ObjectType, ? super U> consumer,
                                               final Supplier<? extends U> supplier)
            throws IOException {
        acceptValueFromResource(name, v -> {
            consumer.accept(v, supplier.get());
        });
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance of {@link #objectClass}.
     *
     * @return a new instance of {@link #objectClass}.
     */
    protected ObjectType objectInstance() {
        return newInstance(objectClass);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The target object class to test.
     */
    protected final Class<? extends ObjectType> objectClass;
}
