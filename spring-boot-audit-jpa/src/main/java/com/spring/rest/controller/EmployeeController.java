package com.spring.rest.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.rest.common.BaseResponse;
import com.spring.rest.dto.EmployeeDTO;
import com.spring.rest.service.EmployeeService;
import com.spring.rest.service.FileStorageService;

@RestController
@RequestMapping("/employee")
@Validated
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private FileStorageService fileStorageService;

	@GetMapping(value = "/find")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(defaultValue = "0") Integer pageNo,
															@RequestParam(defaultValue = "10") Integer pageSize,
															@RequestParam(defaultValue = "employeeId") String sortBy) {
		List<EmployeeDTO> list = employeeService.findEmpList(pageNo, pageSize, sortBy);
		return new ResponseEntity<List<EmployeeDTO>>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/find/by-id")
	public ResponseEntity<EmployeeDTO> getEmployeeById(
			@Valid @RequestParam @NotNull(message = "Id must not be null") String id) {
		EmployeeDTO list = employeeService.findByEmpId(id);

		return new ResponseEntity<EmployeeDTO>(list, HttpStatus.OK);
	}

	@PostMapping(value = { "/add", "/update" })
	public ResponseEntity<BaseResponse> createOrUpdateEmployee(@ModelAttribute EmployeeDTO employeeDTO) {
		try {
			String fileName = fileStorageService.storeFile(employeeDTO.getMultipartFile());
			ServletUriComponentsBuilder servletUriComponentsBuilder = ServletUriComponentsBuilder
					.fromCurrentContextPath();
			servletUriComponentsBuilder.host("192.168.0.33");
			String fileDownloadUri = servletUriComponentsBuilder.path("/employee/downloadFile/").path(fileName)
					.toUriString();
			employeeDTO.setFileName(fileName);
			employeeDTO.setFileDownloadUri(fileDownloadUri);
			employeeDTO.setFileType(employeeDTO.getMultipartFile().getContentType());
			employeeDTO.setSize(employeeDTO.getMultipartFile().getSize());
		} catch (Exception e) {
			System.out.println(e);
		}

		BaseResponse response = employeeService.createOrUpdateEmployee(employeeDTO);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Resource resource = fileStorageService.loadFileAsResource(fileName);
		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			logger.info("Could not determine file type.");
		}
		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") String id) {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>("Data Delete sucessfully", HttpStatus.OK);
	}

}
