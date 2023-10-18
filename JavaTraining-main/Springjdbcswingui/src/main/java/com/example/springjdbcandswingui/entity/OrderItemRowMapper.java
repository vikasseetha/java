//package com.example.springjdbcandswingui.entity;
//
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class OrderItemRowMapper implements RowMapper<OrderItem> {
//
//    @Override
//    public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
//        OrderItem orderItem = new OrderItem();
//
//        // Map the columns from the result set to the OrderItem entity
//        orderItem.setId(rs.getLong("id"));
//        orderItem.setQuantity(rs.getInt("quantity"));
//
//        // Note: We are not mapping the associated Book and Order entities here.
//        // The Book and Order entities are loaded lazily (if needed) using the relationships.
//
//        return orderItem;
//    }
//}


