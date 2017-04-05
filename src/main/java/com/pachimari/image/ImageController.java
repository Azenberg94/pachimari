package com.pachimari.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.InputStream;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * Created by andrem on 04/04/2017.
 */
@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    GridFsTemplate gridFsTemplate;
    @Autowired
    private ImageRepository imageRepository;
    @PostMapping
    @ResponseStatus(CREATED)
    public ResponseEntity createImage(@RequestBody @Valid InputStream imageInputStream, BindingResult bindingResult){
        String id = gridFsTemplate.store(imageInputStream, "image", "image").getId().toString();
        return new ResponseEntity(id, HttpStatus.OK);
    }
}
