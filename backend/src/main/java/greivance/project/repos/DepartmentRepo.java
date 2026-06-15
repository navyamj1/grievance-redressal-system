package greivance.project.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import greivance.project.entity.Department;

@Repository
public interface DepartmentRepo extends CrudRepository<Department, Long> {
    //automatically generate sql query to find department by name
    Department findByName(String name);

}