//package com.example.springjdbcandswingui.entity;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "ORDER_ITEMS")
//public class OrderItem {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
////	@ManyToOne
////	@JoinColumn(name = "book_id")
////	private Book book;
//	
////	   @ManyToOne(fetch = FetchType.LAZY)
//	@ManyToOne
//	    @JoinColumn(name = "book_id")
//	    private Book book;
//	private int quantity;
//
//	@ManyToOne
//	@JoinColumn(name = "order_id")
//	private Order order;
//
//	public OrderItem() {
//		// Default constructor is required for JPA
//	}
//
//	public OrderItem(Book book, int quantity) {
//		this.book = book;
//		this.quantity = quantity;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Book getBook() {
//		return book;
//	}
//
//	public void setBook(Book book) {
//		this.book = book;
//	}
//
//	public int getQuantity() {
//		return quantity;
//	}
//
//	public void setQuantity(int quantity) {
//		this.quantity = quantity;
//	}
//
//	public Order getOrder() {
//		return order;
//	}
//
//	public void setOrder(Order order) {
//		this.order = order;
//	}
//}
