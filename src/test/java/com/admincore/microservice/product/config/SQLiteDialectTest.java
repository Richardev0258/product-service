package com.admincore.microservice.product.config;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SQLiteDialectTest {
    @Test
    void testGetIdentityColumnSupport() {
        SQLiteDialect dialect = new SQLiteDialect();
        assertTrue(dialect.getIdentityColumnSupport().supportsIdentityColumns());
        assertEquals("select last_insert_rowid()",
                dialect.getIdentityColumnSupport().getIdentitySelectString("products", "id", 0));
        assertEquals("integer", dialect.getIdentityColumnSupport().getIdentityColumnString(0));
    }

    @Test
    void testOtherOverrides() {
        SQLiteDialect dialect = new SQLiteDialect();

        assertTrue(dialect.supportsTemporaryTables());
        assertTrue(dialect.supportsIfExistsBeforeTableName());
        assertEquals("", dialect.getDropForeignKeyString());
        assertFalse(dialect.supportsCascadeDelete());
    }
}
