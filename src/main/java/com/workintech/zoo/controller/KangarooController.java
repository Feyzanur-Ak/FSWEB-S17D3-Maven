package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/kangaroos")
public class KangarooController {

    public  Map<Integer, Kangaroo> kangaroos;

    @Autowired
    public KangarooController(Map<Integer, Kangaroo> kangaroos) {
        this.kangaroos = kangaroos;
    }

    @PostConstruct
    public void init(){
        kangaroos=new HashMap<>();
    }

    @GetMapping()
    public List<Kangaroo> FindAllKangaroos(){
        return this.kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo FindAllKangarooById(@PathVariable Integer id) {
        if (!kangaroos.containsKey(id)) {
            throw new ZooException("Kangaroo with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return kangaroos.get(id);
    }

    @PostMapping
    public Kangaroo SaveKangaroo(@RequestBody Kangaroo kangaroo) {
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo UpdateKangaroo(@PathVariable Integer id, @RequestBody Kangaroo updatedKangaroo) {
        if (!kangaroos.containsKey(id)) {
            throw new ZooException("Kangaroo with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
        kangaroos.put(id, updatedKangaroo);
        return updatedKangaroo;
    }

    @DeleteMapping("/{id}")
    public String DeleteKangaroo(@PathVariable Integer id) {
        if (!kangaroos.containsKey(id)) {
            throw new ZooException("Kangaroo with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
        kangaroos.remove(id);
        return "Kangaroo with ID " + id + " deleted.";
    }



}
