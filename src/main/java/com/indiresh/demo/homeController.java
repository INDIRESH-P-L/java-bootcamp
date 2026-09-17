package com.indiresh.demo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/")
public class homeController {
    @GetMapping("/hello")
    public String home() {
        return "Hello, World!";
    }

    @PostMapping("/create")
    public String create(@RequestBody String payload) {
        return payload + " created successfully!";
    }

    @PutMapping("/update")
    public String update(@RequestBody(required = false) String entity) {
        if (entity == null || entity.isEmpty()) {
            entity = "Item";
        }
        return entity + " updated successfully!";
    }

    @PutMapping("/update/{id}")
    public String putMethodName(@PathVariable String id, @RequestBody(required = false) String entity) {
        if (entity == null || entity.isEmpty()) {
            entity = "Item";
        }
        return entity + " with ID " + id + " updated successfully!";
    }


    @DeleteMapping("/delete")
    public String deleteWithoutId() {
        return "Item deleted successfully!";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        return "Item with ID " + id + " deleted successfully!"; 
    }
}