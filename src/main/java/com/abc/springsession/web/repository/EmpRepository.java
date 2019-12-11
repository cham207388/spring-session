package com.abc.springsession.web.repository;

import com.abc.springsession.domain.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpRepository extends CrudRepository<Employee, Integer> {
    Employee findByEmailAndPassword(String username, String password);
}
