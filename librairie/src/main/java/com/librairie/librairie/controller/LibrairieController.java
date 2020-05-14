package com.librairie.librairie.controller;

import com.librairie.librairie.dto.CollectionDto;
import com.librairie.librairie.service.ILibrairieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/librairie/")
public class LibrairieController {
    @Autowired
    private ILibrairieService librairieService;

    @GetMapping("/findAll")
    public ResponseEntity findAllLibrairie() {
        return new ResponseEntity(librairieService.findAll(), HttpStatus.ACCEPTED);
    }

    @PostMapping("/find")
    public ResponseEntity find(@RequestBody CollectionDto collectionDto) {
        return new ResponseEntity(librairieService.find(collectionDto), HttpStatus.ACCEPTED);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity findById(@PathVariable("id") long id) {
        return librairieService.findById(id);
    }

    @GetMapping("/reserve/{id}")
    public ResponseEntity<Boolean> reserve(@PathVariable("id") String id) {
        return librairieService.reserve(id);
    }

    @GetMapping("/getStock/{id}")
    public ResponseEntity<Integer> getStock(@PathVariable("id") String id) {
        return librairieService.getStock(id);
    }
}
