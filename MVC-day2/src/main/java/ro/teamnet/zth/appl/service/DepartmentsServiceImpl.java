package ro.teamnet.zth.appl.service;

import ro.teamnet.zth.appl.dao.DepartmentDao;
import ro.teamnet.zth.appl.dao.EmployeeDao;
import ro.teamnet.zth.appl.domain.Department;
import ro.teamnet.zth.appl.domain.Employee;

import java.util.List;

/**
 * Created by Alexandra.Soare on 7/21/2017.
 */
public class DepartmentsServiceImpl implements DepartmentService {
    private final DepartmentDao departmentDao = new DepartmentDao();

    @Override
    public List<Department> findAll() {
        return departmentDao.findAllDepartments();
    }

    @Override
    public Department findOne(Long departmentId) {
        return departmentDao.findDepartmentById(departmentId);
    }
}
