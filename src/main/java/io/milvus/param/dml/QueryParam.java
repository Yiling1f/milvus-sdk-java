/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.milvus.param.dml;

import com.google.common.collect.Lists;
import io.milvus.exception.ParamException;
import io.milvus.param.ParamUtils;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class QueryParam {
    private final String dbName;
    private final String collectionName;
    private final List<String> partitionNames;
    private final List<String> outFields;
    private final String expr;

    private QueryParam(@Nonnull Builder builder) {
        this.dbName = builder.dbName;
        this.collectionName = builder.collectionName;
        this.partitionNames = builder.partitionNames;
        this.outFields = builder.outFields;
        this.expr = builder.expr;
    }

    public String getDbName() {
        return dbName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public List<String> getPartitionNames() {
        return partitionNames;
    }

    public List<String> getOutFields() {
        return outFields;
    }

    public String getExpr() {
        return expr;
    }

    public static class Builder {
        private String dbName = ""; // reserved
        private String collectionName;
        private List<String> partitionNames = Lists.newArrayList();
        private List<String> outFields = new ArrayList<>();
        private String expr;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder withCollectionName(@Nonnull String collectionName) {
            this.collectionName = collectionName;
            return this;
        }

        public Builder withPartitionNames(@Nonnull List<String> partitionNames) {
            this.partitionNames = partitionNames;
            return this;
        }

        public Builder withOutFields(@Nonnull List<String> outFields) {
            this.outFields = outFields;
            return this;
        }

        public Builder withExpr(@Nonnull String expr) {
            this.expr = expr;
            return this;
        }

        public QueryParam build() throws ParamException {
            ParamUtils.CheckNullEmptyString(collectionName, "Collection name");
            ParamUtils.CheckNullEmptyString(expr, "Expression");

            return new QueryParam(this);
        }
    }
}