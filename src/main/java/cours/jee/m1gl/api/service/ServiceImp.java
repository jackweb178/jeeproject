package cours.jee.m1gl.api.service;

import cours.jee.m1gl.api.dao.IService;
import cours.jee.m1gl.api.model.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceImp implements IServiceService {

    @Autowired
    private IService iServiceDao;

    @Override
    public List<Service> findAll() {
        return iServiceDao.findAll();
    }

    @Override
    public Service save(Service service) {
        return iServiceDao.save(service);
    }
}
