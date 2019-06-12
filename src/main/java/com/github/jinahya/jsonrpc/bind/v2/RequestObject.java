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

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * Represents request objects.
 *
 * @param <ParamsType> {@value #PROPERTY_NAME_PARAMS} type parameter
 * @see <a href="https://www.jsonrpc.org/specification#request_object">4. Request Object (JSON-RPC 2.0
 * Specification)</a>
 */
public class RequestObject<IdType, ParamsType> extends JsonrpcObject<IdType> {

    /**
     * The property name for {@code method}.
     */
    public static final String PROPERTY_NAME_METHOD = "method";

    /**
     * The property name for {@code params}.
     */
    public static final String PROPERTY_NAME_PARAMS = "params";

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return super.toString() + "{"
               + "method=" + method
               + ",params=" + params
               + "}";
    }

//    /**
//     * Checks if method name is reserved for rpc-internal.
//     *
//     * @return {@code true} if {@code method} is reserved for rpc-internal, {@code false} otherwise
//     */
//    @JsonIgnore
//    @JsonbTransient
//    @AssertFalse
//    private boolean isMethodNameForRpcInternal() {
//        return method != null && method.startsWith("rpc.");
//    }

//    /**
//     * Checks if this request object is a notification. This method checks whether {@link #getId()} method returns
//     * {@code null} or not.
//     *
//     * @return {@code true} if this request object is a notification, {@code false} otherwise
//     * @see <a href="https://www.jsonrpc.org/specification#notification">4.1 Notification (JSON-RPC 2.0
//     * Specification)</a>
//     */
//    @JsonIgnore
//    @JsonbTransient
//    public boolean isNotification() {
//        return getId() == null;
//    }

    /**
     * Returns the current value of {@value #PROPERTY_NAME_METHOD} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_METHOD} property.
     */
    public String getMethod() {
        return method;
    }

    /**
     * Replace the current value of {@value #PROPERTY_NAME_METHOD} property with given.
     *
     * @param method new value for {@value #PROPERTY_NAME_METHOD} property.
     */
    public void setMethod(final String method) {
        this.method = method;
    }

    /**
     * Returns the current value of {@value #PROPERTY_NAME_PARAMS} property.
     *
     * @return the current value of {@value #PROPERTY_NAME_PARAMS} property.
     */
    public ParamsType getParams() {
        return params;
    }

    /**
     * Replaces the current value of {@value #PROPERTY_NAME_PARAMS} property with given.
     *
     * @param params new value for {@value #PROPERTY_NAME_PARAMS} property.
     */
    public void setParams(final ParamsType params) {
        this.params = params;
    }

    @NotBlank
    private String method;

    @Valid
    private ParamsType params;
}
