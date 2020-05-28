package cours.jee.m1gl.api.service;

import cours.jee.m1gl.api.model.Service;

import java.util.List;

public interface IServiceService {
    public List<Service> findAll();

    public Service save(Service service);
}
