package test.task.task.dao;

import test.task.task.entity.Appear;

import java.util.List;

public interface AppearDao {

    List<Appear> getAllAppear(String username);
    Appear getAppear(Long id);
    Appear insertAppear(Appear appear);
    Appear updateAppear(Appear appear);
    void deleteAppear(Long id);

}
