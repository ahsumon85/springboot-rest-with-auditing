package com.spring.rest.repo;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.spring.rest.entity.EmployeeEntity;

public interface EmployeeRepo extends PagingAndSortingRepository<EmployeeEntity, Long> {

	public EmployeeEntity findByEmployeeId(String empId);

	public void deleteByEmployeeId(String employeeId);

}
