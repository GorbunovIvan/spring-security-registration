package com.example.controller;

import com.example.model.Book;
import com.example.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    
    private final BookService bookService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("books", bookService.getAll());
        return "books/books";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getById(id));
        return "books/book";
    }

    @PostMapping
    public String create(@ModelAttribute Book book) {
        bookService.create(book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }

    @ModelAttribute("newBook")
    public Book newBook() {
        return Book.builder().build();
    }

    @ModelAttribute("currentUser")
    public String currentUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if (name == null || name.equals("anonymousUser")) {
            return "";
        }
        return name;
    }
}
