/*
 * TestSchemaGenerator.java
 *
 * Created on October 6, 2006, 2:57 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.    
 */
package org.apache.openjpa.persistence.jdbc.schema;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.*;
import javax.sql.DataSource;
import org.apache.openjpa.jdbc.conf.JDBCConfiguration;
import org.apache.openjpa.jdbc.meta.ClassMapping;
import org.apache.openjpa.jdbc.meta.MappingRepository;
import org.apache.openjpa.jdbc.schema.Column;
import org.apache.openjpa.jdbc.schema.SchemaTool;
import org.apache.openjpa.jdbc.schema.Table;
import org.apache.openjpa.jdbc.sql.DBDictionary;

import org.apache.openjpa.persistence.jdbc.common.apps.*;

import java.lang.annotation.Annotation;
import junit.framework.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.apache.openjpa.persistence.OpenJPAEntityManagerFactory;
import org.apache.openjpa.persistence.OpenJPAEntityManager;
import org.apache.openjpa.persistence.OpenJPAEntityManagerFactorySPI;
import org.apache.openjpa.persistence.OpenJPAEntityManagerSPI;
import org.apache.openjpa.persistence.OpenJPAPersistence;

public class TestSchemaGenerator extends org.apache.openjpa.persistence.jdbc.kernel.BaseJDBCTest {

    @Override
    protected String getPersistenceUnitName() {
        // TODO Auto-generated method stub
        return "TestConv";
    }

    /** Creates a new instance of TestSchemaGenerator */
    public TestSchemaGenerator(String name) {
        super(name);
    }

    public void testSchemaGen() throws Exception {
        OpenJPAEntityManagerFactory pmf = (OpenJPAEntityManagerFactory) getEmf();
        OpenJPAEntityManager pm = pmf.createEntityManager();
        JDBCConfiguration conf = (JDBCConfiguration) ((OpenJPAEntityManagerFactorySPI) pmf).getConfiguration();

        StringWriter sw = new StringWriter();

        SchemaTool.Flags flags = new SchemaTool.Flags();
        flags.writer = sw;
        flags.primaryKeys = true;
        flags.foreignKeys = true;
        flags.indexes = true;
        flags.openjpaTables = true;
        flags.action = SchemaTool.ACTION_REFLECT;

        SchemaTool.run(conf, new String[0], flags, getClass().getClassLoader());

        sw.flush();
        String data = sw.toString();
        assertTrue(data.length() > 0);
    }
}
