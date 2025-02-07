package com.workintech.zoo.controller;


import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koalas")
public class KoalaController {

    public Map<Integer, Koala> koalas;


    @PostConstruct
    public void init(){
        koalas=new HashMap<>();
    }


     @GetMapping()
    public List<Koala> FindAllKoalas(){
        return  koalas.values().stream().toList();
     }


     @GetMapping("/{id}")
    public Koala FindKoalaById(@PathVariable Integer id) {
        if(!koalas.containsKey(id)){
            throw new ZooException("Koala with ID " + id + "not found", HttpStatus.NOT_FOUND);
        }
        return koalas.get(id);
     }


     @PostMapping
     public Koala SaveKoala(@RequestBody Koala koala){
        koalas.put(koala.getId(), koala);
        return koala;
     }

    @PutMapping("/{id}")
    public Koala UpdateKoala(@PathVariable Integer id, @RequestBody Koala updatedKoala) {
        if (!koalas.containsKey(id)) {
            throw new ZooException("Koala with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
        koalas.put(id, updatedKoala);
        return updatedKoala;
    }

    @DeleteMapping("/{id}")
    public String DeleteKoala(@PathVariable Integer id) {
        if (!koalas.containsKey(id)) {
            throw new ZooException("Koala with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
        koalas.remove(id);
        return "Koala with ID " + id + " deleted.";
    }

}
