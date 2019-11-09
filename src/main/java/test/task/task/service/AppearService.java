package test.task.task.service;

import test.task.task.entity.Appear;

import java.util.List;

public interface AppearService {

    List<Appear> getAllAppear(String username);
    Appear getAppear(Long id);
    Appear insertAppear(Appear appear);
    Appear updateAppear(Appear appear);
    void deleteAppear(Long id);
}
