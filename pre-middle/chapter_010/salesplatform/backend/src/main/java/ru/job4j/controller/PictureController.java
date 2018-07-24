package ru.job4j.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.dao.PictureDaoImpl;

/**
 * Get picture controller.
 */
@RestController
public class PictureController {

    @GetMapping(value = "/pic", produces = "image/*;charset=UTF-8")
    public byte[] getPicture(@RequestParam("post") Long postId,
                             @RequestParam("pic") Long picId) {
        return new PictureDaoImpl().findOne(picId).getData();
    }
}
