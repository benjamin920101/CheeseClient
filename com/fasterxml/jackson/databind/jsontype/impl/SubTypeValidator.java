/*
 * Decompiled with CFR 0.152.
 */
package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SubTypeValidator {
    protected static final String PREFIX_SPRING = "org.springframework.";
    protected static final String PREFIX_C3P0 = "com.mchange.v2.c3p0.";
    protected static final Set<String> DEFAULT_NO_DESER_CLASS_NAMES;
    protected Set<String> _cfgIllegalClassNames = DEFAULT_NO_DESER_CLASS_NAMES;
    private static final SubTypeValidator instance;

    protected SubTypeValidator() {
    }

    public static SubTypeValidator instance() {
        return instance;
    }

    public void validateSubType(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
        String full;
        block6: {
            block7: {
                block8: {
                    Class<?> raw = type.getRawClass();
                    full = raw.getName();
                    if (this._cfgIllegalClassNames.contains(full)) break block6;
                    if (raw.isInterface()) break block7;
                    if (!full.startsWith(PREFIX_SPRING)) break block8;
                    for (Class<?> cls = raw; cls != null && cls != Object.class; cls = cls.getSuperclass()) {
                        String name = cls.getSimpleName();
                        if (!"AbstractPointcutAdvisor".equals(name) && !"AbstractApplicationContext".equals(name)) {
                            continue;
                        }
                        break block6;
                    }
                    break block7;
                }
                if (full.startsWith(PREFIX_C3P0) && full.endsWith("DataSource")) break block6;
            }
            return;
        }
        ctxt.reportBadTypeDefinition(beanDesc, "Illegal type (%s) to deserialize: prevented for security reasons", full);
    }

    static {
        HashSet<String> s2 = new HashSet<String>();
        s2.add("org.apache.commons.collections.functors.InvokerTransformer");
        s2.add("org.apache.commons.collections.functors.InstantiateTransformer");
        s2.add("org.apache.commons.collections4.functors.InvokerTransformer");
        s2.add("org.apache.commons.collections4.functors.InstantiateTransformer");
        s2.add("org.codehaus.groovy.runtime.ConvertedClosure");
        s2.add("org.codehaus.groovy.runtime.MethodClosure");
        s2.add("org.springframework.beans.factory.ObjectFactory");
        s2.add("com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl");
        s2.add("org.apache.xalan.xsltc.trax.TemplatesImpl");
        s2.add("com.sun.rowset.JdbcRowSetImpl");
        s2.add("java.util.logging.FileHandler");
        s2.add("java.rmi.server.UnicastRemoteObject");
        s2.add("org.springframework.beans.factory.config.PropertyPathFactoryBean");
        s2.add("org.apache.tomcat.dbcp.dbcp2.BasicDataSource");
        s2.add("com.sun.org.apache.bcel.internal.util.ClassLoader");
        s2.add("org.hibernate.jmx.StatisticsService");
        s2.add("org.apache.ibatis.datasource.jndi.JndiDataSourceFactory");
        s2.add("org.apache.ibatis.parsing.XPathParser");
        s2.add("jodd.db.connection.DataSourceConnectionProvider");
        s2.add("oracle.jdbc.connector.OracleManagedConnectionFactory");
        s2.add("oracle.jdbc.rowset.OracleJDBCRowSet");
        s2.add("org.slf4j.ext.EventData");
        s2.add("flex.messaging.util.concurrent.AsynchBeansWorkManagerExecutor");
        s2.add("com.sun.deploy.security.ruleset.DRSHelper");
        s2.add("org.apache.axis2.jaxws.spi.handler.HandlerResolverImpl");
        s2.add("org.jboss.util.propertyeditor.DocumentEditor");
        s2.add("org.apache.openjpa.ee.RegistryManagedRuntime");
        s2.add("org.apache.openjpa.ee.JNDIManagedRuntime");
        s2.add("org.apache.axis2.transport.jms.JMSOutTransportInfo");
        s2.add("com.mysql.cj.jdbc.admin.MiniAdmin");
        s2.add("ch.qos.logback.core.db.DriverManagerConnectionSource");
        s2.add("org.jdom.transform.XSLTransformer");
        s2.add("org.jdom2.transform.XSLTransformer");
        s2.add("net.sf.ehcache.transaction.manager.DefaultTransactionManagerLookup");
        s2.add("net.sf.ehcache.hibernate.EhcacheJtaTransactionManagerLookup");
        s2.add("ch.qos.logback.core.db.JNDIConnectionSource");
        s2.add("com.zaxxer.hikari.HikariConfig");
        s2.add("com.zaxxer.hikari.HikariDataSource");
        s2.add("org.apache.cxf.jaxrs.provider.XSLTJaxbProvider");
        s2.add("org.apache.commons.configuration.JNDIConfiguration");
        s2.add("org.apache.commons.configuration2.JNDIConfiguration");
        s2.add("org.apache.xalan.lib.sql.JNDIConnectionPool");
        s2.add("org.apache.commons.dbcp.datasources.PerUserPoolDataSource");
        s2.add("org.apache.commons.dbcp.datasources.SharedPoolDataSource");
        s2.add("com.p6spy.engine.spy.P6DataSource");
        s2.add("org.apache.log4j.receivers.db.DriverManagerConnectionSource");
        s2.add("org.apache.log4j.receivers.db.JNDIConnectionSource");
        s2.add("net.sf.ehcache.transaction.manager.selector.GenericJndiSelector");
        s2.add("net.sf.ehcache.transaction.manager.selector.GlassfishSelector");
        DEFAULT_NO_DESER_CLASS_NAMES = Collections.unmodifiableSet(s2);
        instance = new SubTypeValidator();
    }
}

