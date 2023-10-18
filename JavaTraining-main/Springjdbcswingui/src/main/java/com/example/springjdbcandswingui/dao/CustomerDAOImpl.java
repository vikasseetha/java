//package com.example.springjdbcandswingui.dao;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import com.example.springjdbcandswingui.entity.Customer;
//import com.example.springjdbcandswingui.entity.CustomerRowMapper;
//
//import java.util.List;
//
//@Repository
//public class CustomerDAOImpl implements CustomerDAO {
//
//	private static final String SELECT_ALL_QUERY = "SELECT * FROM customer";
//	private static final String SELECT_BY_ID_QUERY = "SELECT * FROM customer WHERE id = ?";
//	private static final String INSERT_QUERY = "INSERT INTO customer (name, email, address) VALUES (?, ?, ?)";
//	private static final String UPDATE_QUERY = "UPDATE customer SET name = ?, email = ?, address = ? WHERE id = ?";
//	private static final String DELETE_QUERY = "DELETE FROM customer WHERE id = ?";
//
//	@Autowired
//	private JdbcTemplate jdbcTemplate;
//
//	@Override
//	public List<Customer> getAllCustomers() {
//		return jdbcTemplate.query(SELECT_ALL_QUERY, new CustomerRowMapper());
//	}
//
//	@Override
//	public Customer getCustomerById(Long id) {
//		return jdbcTemplate.queryForObject(SELECT_BY_ID_QUERY, new Object[] { id }, new CustomerRowMapper());
//	}
//
//	@Override
//	public void addCustomer(Customer customer) {
//		jdbcTemplate.update(INSERT_QUERY, customer.getName(), customer.getEmail(), customer.getAddress());
//	}
//
//	@Override
//	public void updateCustomer(Customer customer) {
//		jdbcTemplate.update(UPDATE_QUERY, customer.getName(), customer.getEmail(), customer.getAddress(),
//				customer.getId());
//	}
//
//	@Override
//	public void deleteCustomer(Long id) {
//		jdbcTemplate.update(DELETE_QUERY, id);
//	}
//}

package com.example.springjdbcandswingui.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.springjdbcandswingui.entity.Customer;
import com.example.springjdbcandswingui.entity.CustomerRowMapper;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private static final String SELECT_ALL_QUERY = "SELECT * FROM customer";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM customer WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO customer (name, email, address, is_admin) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE customer SET name = ?, email = ?, address = ?, is_admin = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM customer WHERE id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Customer> getAllCustomers() {
        return jdbcTemplate.query(SELECT_ALL_QUERY, new CustomerRowMapper());
    }

    @Override
    public Customer getCustomerById(Long id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID_QUERY, new Object[] { id }, new CustomerRowMapper());
    }

    @Override
    public void addCustomer(Customer customer) {
        jdbcTemplate.update(INSERT_QUERY, customer.getName(), customer.getEmail(), customer.getAddress(),
                customer.isIs_admin());
    }

    @Override
    public void updateCustomer(Customer customer) {
        jdbcTemplate.update(UPDATE_QUERY, customer.getName(), customer.getEmail(), customer.getAddress(),
                customer.isIs_admin(), customer.getId());
    }

    @Override
    public void deleteCustomer(Long id) {
        jdbcTemplate.update(DELETE_QUERY, id);
    }
    
    @Override
    public List<Customer> getAdminCustomers() {
        String SELECT_ADMIN_CUSTOMERS_QUERY = "SELECT * FROM customer WHERE is_admin = true";
        return jdbcTemplate.query(SELECT_ADMIN_CUSTOMERS_QUERY, new BeanPropertyRowMapper<>(Customer.class));
    }
}

