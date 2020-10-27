/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dtstack.flink.sql.side.db2.table;

import com.dtstack.flink.sql.side.AbstractSideTableInfo;
import com.dtstack.flink.sql.side.db2.Db2AllSideInfo;
import com.dtstack.flink.sql.side.rdb.all.AbstractRdbTableFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author: chuixue
 * @create: 2020-10-27 14:14
 * @description:
 **/
public class Db2TableFunction extends AbstractRdbTableFunction {
    private static final Logger LOG = LoggerFactory.getLogger(Db2TableFunction.class);

    private static final String DB2_DRIVER = "com.ibm.db2.jcc.DB2Driver";

    public Db2TableFunction(AbstractSideTableInfo sideTableInfo, String[] lookupKeys) {
        super(new Db2AllSideInfo(sideTableInfo, lookupKeys));
    }

    @Override
    public Connection getConn(String dbUrl, String userName, String password) {
        try {
            Class.forName(DB2_DRIVER);
            Connection conn = DriverManager.getConnection(dbUrl, userName, password);
            return conn;
        } catch (Exception e) {
            LOG.error("", e);
            throw new RuntimeException("", e);
        }
    }
}
