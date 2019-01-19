package io.swagger.api;

import io.swagger.exception.DataValidationException;
import io.swagger.service.ApiService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-11T03:29:22.478Z")


/*
 * This is a controller class for Task operaions. You can refer the service class for the backend operations.
 * 
 */

@RestController
public class TasksApiController extends TaskTodoHandler implements TasksApi {

    private static final Logger log = LoggerFactory.getLogger(TasksApiController.class);

    @Autowired
    private ApiService apiService;   
   

    /**
     * This returns BalanceTestResult or ToDoItemValidationError object based on string input condition.
     */
    public ResponseEntity<?> tasksValidateBracketsGet(@RequestParam String input,
            HttpServletRequest request, HttpServletResponse response, UriComponentsBuilder builder) { 
    	
    	log.info("tasksValidateBracketsGet controller operation");
        
    	//400 validation error
    	if(input.isEmpty()) 
    		return handleDataStoreException(new DataValidationException());    				
    		
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentLength(request.getContentLength());
    	headers.setLocation(builder.path( request.getServletPath()).build().toUri());
    	return new ResponseEntity<>(apiService.isBalanced(input), headers, HttpStatus.OK);
    }   
    
    
}
