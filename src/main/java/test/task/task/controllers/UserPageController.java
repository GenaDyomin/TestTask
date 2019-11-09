package test.task.task.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.task.task.entity.Appear;
import test.task.task.service.AppearService;


import java.util.List;

@RestController
@RequestMapping("page")
public class UserPageController {

    @Autowired
    public AppearService appearService;

    @GetMapping
    public List list(String userName) {
        return appearService.getAllAppear(userName);
    }

    @GetMapping("{id}")
    public Appear getOne(@PathVariable("id") String id) {
        return appearService.getAppear(Long.parseLong(id));
    }


    @PostMapping
    public Appear create(@RequestBody Appear appear) {
        return appearService.insertAppear(appear);
    }

    @PutMapping
    public Appear update(@RequestBody Appear appear) {
        return appearService.updateAppear(appear);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") String id) {
        appearService.deleteAppear(Long.parseLong(id));
    }
}
