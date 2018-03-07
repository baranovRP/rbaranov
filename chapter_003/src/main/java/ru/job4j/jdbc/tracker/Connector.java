package ru.job4j.jdbc.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class represent connection to DB.
 *
 * @version $Id$
 * @since 0.1
 */
public class Connector {

    private static final Logger LOG = LoggerFactory.getLogger(Connector.class);

    private static Connection conn = null;

    /**
     * Get connection to DB
     *
     * @return connection
     */
    public static Connection connect() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(Config.getURL());
                conn.setAutoCommit(false);
                LOG.info(String.format(
                    "Create connection to { %s }.", conn.getMetaData().getURL()));
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return conn;
    }

    /**
     * Close connection to DB
     */
    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e1) {
                    LOG.error(e1.getMessage(), e1);
                }
            }
        }
    }
}
