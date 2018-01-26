package hello;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,Integer>{
    Role findFirstByRole(String role);
}
