package ru.job4j.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Class to work with database.
 * Set up connection URL, set up number of records and init DB.
 */
public class SQLStorage {

    private static final Logger LOG = LoggerFactory.getLogger(SQLStorage.class);

    private static final String TABLE = "TEST";
    private String url = "";
    private int number = 10;
    private Connection conn = null;

    /**
     * Get connection URL
     *
     * @return connection URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set connection URL
     *
     * @param url connection URL
     */
    public void setUrl(final String url) {
        this.url = url;
        LOG.info(String.format("Connection URL is: %s.", url));
    }

    /**
     * Get number of records in DB
     *
     * @return number of records
     */
    public int getNumber() {
        return number;
    }

    /**
     * Set number of records
     *
     * @param number number of records
     */
    public void setNumber(final int number) {
        this.number = number;
        LOG.info(String.format("Table volume is: %s records.", number));
    }

    /**
     * Init DB, before usage you should set up connection URL
     * and number of records
     */
    public void initDB() {
        createTableIfRequired(TABLE);
        populateTable(TABLE, this.number);
    }

    /**
     * Get dataset
     *
     * @return dataset
     */
    public Entries getDataSet() {
        Set<Entry> entries = new HashSet<>();
        String query = String.format("SELECT * FROM %s", TABLE);
        Statement st = null;
        ResultSet rs = null;
        try (Connection conn = this.connect()) {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                entries.add(new Entry(rs.getInt("field")));
            }
            LOG.info("Got dataset from DB.");
            rs.close();
            st.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e1) {
                    LOG.error(e1.getMessage(), e1);
                }
            }
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e1) {
                    LOG.error(e1.getMessage(), e1);
                }
            }
            try {
                conn.rollback();
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
        }
        return new Entries(entries);
    }

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.url);
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return conn;
    }

    private void createTableIfRequired(final String table) {
        String createTableQuery = String.format(
            "CREATE TABLE IF NOT EXISTS %s ("
                + "  field     INTEGER PRIMARY KEY"
                + ");", table);
        String cleanTableQuery = String.format("DELETE FROM  %s", table);
        try (Connection conn = this.connect()) {
            Statement stmt = conn.createStatement();
            stmt.execute(createTableQuery);
            stmt.execute(cleanTableQuery);
            conn.commit();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
        }
    }

    private void populateTable(final String table, final int number) {
        String sql = String.format("INSERT INTO %s (field) VALUES (?);", table);
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 1; i <= number; i++) {
                pstmt.setString(1, String.valueOf(i));
                pstmt.executeUpdate();
            }
            conn.commit();
            LOG.info(String.format("Inserted %s records in DB.", number));
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
