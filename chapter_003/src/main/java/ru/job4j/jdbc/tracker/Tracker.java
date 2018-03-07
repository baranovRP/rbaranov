package ru.job4j.jdbc.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Tracker.
 *
 * @version $Id$
 * @since 0.1
 */
public class Tracker {

    private static final Logger LOG = LoggerFactory.getLogger(Tracker.class);

    private Connection conn = null;
    private Config conf = new Config();

    public void init() {
        this.conn = Connector.connect();
    }

    /**
     * Add item to store
     *
     * @param item item
     * @return added item
     */
    public Item add(final Item item) {
        int id = -1;
        try (PreparedStatement pstmt =
                 conn.prepareStatement(conf.getQuery("insertItem"))) {
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getDescription());
            pstmt.executeUpdate();
            conn.commit();
            id = lastRowId();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
        }
        Item insertedItem = findById(id);
        LOG.info(String.format("Add item %s", insertedItem.toString()));
        return insertedItem;
    }

    /**
     * Update item
     *
     * @param id   item's id
     * @param item item
     */
    public void replace(int id, final Item item) {
        try (PreparedStatement prstmt =
                 conn.prepareStatement(conf.getQuery("replaceByID"))) {
            prstmt.setString(1, item.getName());
            prstmt.setString(2, item.getDescription());
            prstmt.setLong(3, item.getCreatedDate());
            prstmt.setLong(4, id);
            prstmt.executeUpdate();
            conn.commit();
            LOG.info(String.format("Update item with id { %d }", id));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
        }
        Item updatedItem = findById(id);
        LOG.info(String.format("New values item %s", updatedItem.toString()));
    }

    /**
     * Remove item from DB
     *
     * @param id item's id
     */
    public void delete(final int id) {
        try (PreparedStatement pstmt =
                 conn.prepareStatement(conf.getQuery("deleteByID"))) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            conn.commit();
            LOG.info(String.format("Delete item with id { %d }", id));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            try {
                conn.rollback();
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
        }
    }

    /**
     * Get all items from DB
     *
     * @return items sorted ASC
     */
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement prstm =
                 conn.prepareStatement(conf.getQuery("findAll"))) {
            rs = prstm.executeQuery();
            items = listItems(rs);
            rs.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e1) {
                    LOG.error(e1.getMessage(), e1);
                }
            }
        }
        return items;
    }

    /**
     * Get items by key(name)
     *
     * @param key name
     * @return items sorted ASC
     */
    public List<Item> findByName(final String key) {
        List<Item> items = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement prstm =
                 conn.prepareStatement(conf.getQuery("findByName"))) {
            prstm.setString(1, key);
            rs = prstm.executeQuery();
            items = listItems(rs);
            rs.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e1) {
                    LOG.error(e1.getMessage(), e1);
                }
            }
        }
        return items;
    }

    private List<Item> listItems(final ResultSet rs) throws SQLException {
        List<Item> items = new ArrayList<>();
        while (rs.next()) {
            Item item = new Item();
            item.setId(rs.getInt("id"));
            item.setName(rs.getString("name"));
            item.setDescription(rs.getString("description"));
            item.setCreatedDate(rs.getLong("created_date"));
            items.add(item);
            LOG.info("Select item { " + item.toString() + " }.");
        }
        return items;
    }

    /**
     * Get item by ID
     *
     * @param id item's id
     * @return item
     */
    public Item findById(int id) {
        Item item = new Item();
        ResultSet rs = null;
        try (PreparedStatement prstm =
                 conn.prepareStatement(conf.getQuery("findByID"))) {
            prstm.setInt(1, id);
            rs = prstm.executeQuery();
            while (rs.next()) {
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setDescription(rs.getString("description"));
                item.setCreatedDate(rs.getLong("created_date"));
            }
            LOG.info("Select item by id { " + item.toString() + " }.");
            rs.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e1) {
                    LOG.error(e1.getMessage(), e1);
                }
            }
        }
        return item;
    }

    private int lastRowId() {
        int id = -1;
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(conf.getQuery("lastRowID"));
            while (rs.next()) {
                id = rs.getInt(1);
            }
            LOG.info(String.format("Get last row id { %d }.", id));
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
        }
        return id;
    }
}