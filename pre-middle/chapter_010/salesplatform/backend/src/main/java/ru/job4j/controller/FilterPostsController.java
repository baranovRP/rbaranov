package ru.job4j.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.DispatchFilter;
import ru.job4j.Filter;
import ru.job4j.model.Post;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Get filtered posts controller.
 */
@RestController
public class FilterPostsController {

    @GetMapping(value = "/filter", produces = "application/json;charset=UTF-8")
    public List<Post> getFilteredPostsCollection(final HttpServletRequest req) {
        ArrayList<String> queryParams = new ArrayList<>(req.getParameterMap().keySet());
        String condition = queryParams.get(0);
        Filter filter = Filter.filterFromName(condition);
        return new DispatchFilter().init().sent(filter, req.getParameter(condition));
    }
}
