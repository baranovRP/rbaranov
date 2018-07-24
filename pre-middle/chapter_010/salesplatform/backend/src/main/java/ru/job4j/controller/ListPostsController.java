package ru.job4j.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.dao.PostDaoImpl;
import ru.job4j.model.Post;

import java.util.List;

/**
 * Controller to list advertisements.
 */
@RestController
public class ListPostsController {

    @GetMapping(value = "/list", produces = "application/json;charset=UTF-8")
    public List<Post> getPostsCollection() {
        // TODOset password to empty
        return new PostDaoImpl().findAll();
    }
}
