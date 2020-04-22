package cours.jee.m1gl.api.dao;

import cours.jee.m1gl.api.model.Role;
import cours.jee.m1gl.api.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByName(RoleName roleName);
}
