package com.spring.rest.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.rest.common.BaseResponse;
import com.spring.rest.common.CustomMessage;
import com.spring.rest.dto.EmployeeDTO;
import com.spring.rest.entity.EmployeeEntity;
import com.spring.rest.repo.EmployeeRepo;

@Service
@Transactional
public class EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	private static final String TOPIC = "Employee";

	
	@Cacheable(value= "employeeCacheList", unless= "#result.size() == 0")
	public List<EmployeeDTO> findEmpList(Integer pageNo, Integer pageSize, String sortBy) {
		 Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return StreamSupport.stream(employeeRepo.findAll(paging).spliterator(), false).map(this::copyEmployeEntityToDto)
				.collect(Collectors.toList());
	}

	@Cacheable(value= "employeeCache", key= "#employeeId")
	public EmployeeDTO findByEmpId(String employeeId) {
		EmployeeEntity employeeEntity = employeeRepo.findByEmployeeId(employeeId);
		return copyEmployeEntityToDto(employeeEntity);
	}

	@Caching(
			put= { @CachePut(value= "employeeCache", key= "#employeeEntity.employeeId") },
			evict= { @CacheEvict(value= "employeeCacheList", allEntries= true) }
		)
	public BaseResponse createOrUpdateEmployee(EmployeeDTO employeeDTO) {
		EmployeeEntity employeeEntity = copyEmployeDtoToEntity(employeeDTO);
		employeeRepo.save(employeeEntity);
		return new BaseResponse("Employee" + CustomMessage.SAVE_SUCCESS_MESSAGE);
	}
	
	@Caching(
			evict= { 
				@CacheEvict(value= "employeeCache", key= "#employeeId"),
				@CacheEvict(value= "employeeCacheList", allEntries= true)
			}
		)
	public void deleteEmployee(String employeeId) {
		employeeRepo.deleteByEmployeeId(employeeId);
	}

	private EmployeeDTO copyEmployeEntityToDto(EmployeeEntity employeeEntity) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		BeanUtils.copyProperties(employeeEntity, employeeDTO);
		return employeeDTO;
	}

	private EmployeeEntity copyEmployeDtoToEntity(EmployeeDTO employeeDTO) {
		EmployeeEntity employeeEntity = new EmployeeEntity();
		BeanUtils.copyProperties(employeeDTO, employeeEntity);
		employeeEntity.setFileUpload(employeeDTO.getFileDownloadUri());
		return employeeEntity;
	}

}
