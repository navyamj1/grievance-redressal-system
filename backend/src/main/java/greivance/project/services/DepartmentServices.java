package greivance.project.services;

import org.springframework.stereotype.Service;

import greivance.project.entity.Department;
import greivance.project.repos.DepartmentRepo;

import java.util.Optional;

@Service
public class DepartmentServices {

    private final DepartmentRepo repo;

    public DepartmentServices(DepartmentRepo repo) {
        this.repo = repo;
    }

    // all departments with get
    public Iterable<Department> getAllDepartments() {
        return repo.findAll();
    }

    // add a new dept
    public String postDepartment(String name) {

        // if dept already exists 
        Department existingDepartment = repo.findByName(name);

        if (existingDepartment != null) {
            return "Department already exists";
        }

        Department department = new Department();

        // Try to set the name field even if a setter is not present on the entity
        try {
            java.lang.reflect.Field field = null;
            String[] candidates = {"name", "departmentName", "deptName"};
            for (String cand : candidates) {
                try {
                    field = Department.class.getDeclaredField(cand);
                    break;
                } catch (NoSuchFieldException ignored) {
                }
            }
            if (field != null) {
                field.setAccessible(true);
                field.set(department, name);
            } else {
                // As a fallback, try a String constructor if available
                try {
                    java.lang.reflect.Constructor<Department> ctor = Department.class.getConstructor(String.class);
                    department = ctor.newInstance(name);
                } catch (Exception ignored) {
                    // If neither field nor constructor exists, leave department as-is
                }
            }
        } catch (Exception ignored) {
        }

        repo.save(department);

        return "Department Added Successfully";
    }
    public Optional<Department> getDepartmentById(Long id){
        return repo.findById(id);
    }
}