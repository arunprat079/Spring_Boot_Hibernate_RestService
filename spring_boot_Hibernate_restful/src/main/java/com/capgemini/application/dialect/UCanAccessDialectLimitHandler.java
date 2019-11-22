package com.capgemini.application.dialect;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.hibernate.dialect.pagination.LimitHandler;
import org.hibernate.engine.spi.RowSelection;

/**
 * 
 * Hibernate dialect for UCanAccess - limit handler for query results
 * 
 */
public class UCanAccessDialectLimitHandler implements LimitHandler {

    @Override
    public int bindLimitParametersAtEndOfQuery(RowSelection selection, PreparedStatement statement, int index)
            throws SQLException {
         System.out.println("Prep State bindLimitEnd: " + statement);
        // (not used)
        return 0;
    }

    @Override
    public int bindLimitParametersAtStartOfQuery(RowSelection selection, PreparedStatement statement, int index)
            throws SQLException {
        // (not used)
        System.out.println("Prep State bindLimitStart: " + statement);
        return 0;
    }

    @Override
    public String processSql(String sql, RowSelection selection) {
        System.out.println("ProcessSQL:" + sql);
        return String.format("%s limit %d offset %d", sql, selection.getMaxRows(), selection.getFirstRow());
    }

    @Override
    public void setMaxRows(RowSelection selection, PreparedStatement statement) throws SQLException {
         System.out.println("Prep State setMax: " + statement);
        // (not used)
    }

    @Override
    public boolean supportsLimit() {
        return true;
    }

    @Override
    public boolean supportsLimitOffset() {
        return true;
    }

}