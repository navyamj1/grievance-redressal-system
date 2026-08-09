package greivance.project.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import greivance.project.entity.Department;
import greivance.project.services.DepartmentServices;

@RestController
public class DepartmentController {

    private final DepartmentServices departmentServices;

    public DepartmentController(DepartmentServices departmentServices) {
        this.departmentServices = departmentServices;
    }

    @GetMapping("/departments")
    public @ResponseBody Iterable<Department> getDepartments() {
        return departmentServices.GetAllDepartments();
    }

    @PostMapping("/post-department")
    public @ResponseBody String postDepartment(
            @RequestParam String name
    ) {
        return departmentServices.AddDepartment(name);
    }
}