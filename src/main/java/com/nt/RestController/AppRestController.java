package com.nt.RestController;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.Bind.AppBind;
import com.nt.Service.AppService;

@RestController
public class AppRestController {

	@Autowired
	private AppService service;
	
	Logger logger=LoggerFactory.getLogger(AppRestController.class);
	
	@PostMapping("/app")
	public ResponseEntity<String> appReg(@RequestBody AppBind appBind){
		logger.debug("Citizen Application Creation Proccess Start.");
		String status=service.appReg(appBind);	
		logger.info("Citizen Application Creation Successfull");
		logger.debug("Citizen Application Creation Proccess End.");
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
	
	@GetMapping("/getapps/{id}")
	public ResponseEntity<List<AppBind>>
	getApps(@PathVariable("id") Integer id){
		logger.debug("fetch Application process Start");
		List<AppBind> bind=service.getAppBaseOnRole(id);
		logger.info("Fetch Application Successfull");
		logger.debug("fetch Application process End");
		return new ResponseEntity<>(bind,HttpStatus.OK);
	}
	
}
