//package com.example.springjdbcandswingui.springjdbcandswingui;
//
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class BookController {
//
//    @GetMapping("/books")
//    public String getAllBooks() {
//		return null;
//        // Return all books
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/admin/books")
//    public String getAdminBooks() {
//		return null;
//        // Return books accessible only to admins
//    }
//}
//
