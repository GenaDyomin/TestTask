package test.task.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.task.task.dao.AppearDao;
import test.task.task.entity.Appear;

import java.util.List;

@Service
public class AppearServiceImpl implements AppearService {

   @Autowired
   public AppearDao appearDao;

    @Override
    public List<Appear> getAllAppear(String username) {
        return appearDao.getAllAppear(username);
    }

    @Override
    public Appear getAppear(Long id) {
        return appearDao.getAppear(id);
    }

    @Override
    public Appear insertAppear(Appear appear) {
        return appearDao.insertAppear(appear);
    }

    @Override
    public Appear updateAppear(Appear appear) {
        return appearDao.updateAppear(appear);
    }

    @Override
    public void deleteAppear(Long id) {
        appearDao.deleteAppear(id);
    }
}
