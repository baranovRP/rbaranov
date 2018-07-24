package ru.job4j.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.dao.MetadataStore;
import ru.job4j.model.Metadata;

/**
 * Metadata controller.
 * Return metadata for add post page. (e.g. dropdown values etc.)
 */
@RestController
public class MetadataController {

    @GetMapping(value = "/metadata", produces = "application/json;charset=UTF-8")
    public Metadata getMetadata() {
        return new MetadataStore().getMetadata();
    }
}
