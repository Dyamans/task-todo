package io.swagger.api;

import io.swagger.model.ToDoItem;
import io.swagger.model.ToDoItemAddRequest;
import io.swagger.model.ToDoItemUpdateRequest;
import io.swagger.service.ApiService;

import io.swagger.annotations.*;
import io.swagger.exception.DataValidationException;
import io.swagger.exception.ResourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;
import java.util.function.Function;


@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-11T03:29:22.478Z")

/*
 * This is a controller class for Todo operaions. You can refer the service class for the backend operations.
 * 
 */
@RestController
public class TodoApiController extends TaskTodoHandler implements TodoApi {
	

    private static final Logger log = LoggerFactory.getLogger(TodoApiController.class);

       
    @Autowired
    private ApiService apiService;   
   
    public ResponseEntity<?> todoPost(@Valid @RequestBody ToDoItemAddRequest body) {  
    	
    	//error 400 validation check
    	if((body != null && body.getText() == null) || body == null)
    		return handleDataStoreException(new DataValidationException());     	
    	
    	ToDoItem toDoItem = this.apiService.todoPost(addItemData.apply(body));   
    	
    	log.info("todoPost operation");
    		
    	return new ResponseEntity<>(toDoItem, HttpStatus.OK);
    }

    public ResponseEntity<?> todoIdGet(@ApiParam(value = "id",required=true) @PathVariable("id") Integer id, 
    									HttpServletRequest request, HttpServletResponse response, UriComponentsBuilder builder) {
    	
    	//error 400 validation    	
    	if(id == null)
    		return handleDataStoreException(new DataValidationException()); 
    	
    	ToDoItem toDoItem = this.apiService.todoIdGet(id);
    	if(toDoItem == null)
    		return handleResourceNotFoundException(new ResourceNotFoundException("Item with" + id + "not found"), response);    	
    	
    	log.info("todoIdGet operation");
    	//response with 200.
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(request.getContentLength());
        headers.setLocation(builder.path( request.getServletPath()).build().toUri());
        return new ResponseEntity<>(toDoItem,headers, HttpStatus.OK);
    }

    public ResponseEntity<?> todoIdPatch(@PathVariable("id") Integer id, @Valid @RequestBody ToDoItemUpdateRequest body, 
    																	HttpServletRequest request, HttpServletResponse response) {
    	
    	//error 400 validation    	
    	if(id == null)
    		return handleDataStoreException(new DataValidationException());
    	
    	if((body != null && body.getText() == null) || body == null)
    		return handleDataStoreException(new DataValidationException()); 
    	
    	ToDoItem toDoItem = this.apiService.updateToDoItem(body, id);
    	
    	//error 404 validation.
    	if(toDoItem == null)
    		return handleResourceNotFoundException(new ResourceNotFoundException("Item with" + id + "not found"), response);  
    	
    	log.info("todoIdPatch operation");
    	
    	//200 response
        return new ResponseEntity<>(toDoItem, HttpStatus.OK);
    }
    
    private Function<ToDoItemAddRequest, ToDoItem> addItemData = TodoApiController::add;
    
    private static ToDoItem add(final ToDoItemAddRequest toDoItemAddRequest) {
        return new ToDoItem(toDoItemAddRequest.getText(), false, LocalDateTime.now().toString());
    }  
}
