/**
 * 
 */
package io.swagger.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import io.swagger.exception.DataValidationException;
import io.swagger.exception.ResourceNotFoundException;
import io.swagger.model.ToDoItemNotFoundError;
import io.swagger.model.ToDoItemNotFoundErrorDetails;
import io.swagger.model.ToDoItemValidationError;
import io.swagger.model.ToDoItemValidationErrorDetails;


/**
 * @author dgpats
 *
 */
public abstract class TaskTodoHandler implements ApplicationEventPublisherAware {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	 private ApplicationEventPublisher eventPublisher;
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.warn("Method Argument not Valid Exception : " + ex.getMessage());
        return new ResponseEntity<>(validationError(ex), HttpStatus.BAD_REQUEST);       
    }
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletResponse response) {
        log.error("ResourceNotFoundException handler:" + ex.getMessage());

        ToDoItemNotFoundError fError = new ToDoItemNotFoundError();       
        ToDoItemNotFoundErrorDetails fErrorDetails = new ToDoItemNotFoundErrorDetails();
        fError.setName("NotFoundError");
        fErrorDetails.setMessage(ex.getMessage());
        fError.addDetailsItem(fErrorDetails);       
        return new ResponseEntity<>(fError, HttpStatus.NOT_FOUND);
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request, HttpServletResponse response) {
        log.warn("Method Argument Type Mismatch Exception to RestResponse : " + ex.getMessage());
        return new ResponseEntity<>(validationError(ex), HttpStatus.BAD_REQUEST);
    }
    
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataValidationException.class)
    @ResponseBody
    public ResponseEntity<?> handleDataStoreException(DataValidationException ex) {
        log.warn("Converting Data Store exception to RestResponse : " + ex.getMessage());
        return new ResponseEntity<>(validationError(ex), HttpStatus.BAD_REQUEST);
    }
    
   


    
    private ToDoItemValidationError validationError(Exception ex) {
        if (ex instanceof  MethodArgumentNotValidException) {
            return getToDoItemValidationError((MethodArgumentNotValidException) ex);
        }

        if (ex instanceof  MethodArgumentTypeMismatchException) {
            return getToDoItemValidationError((MethodArgumentTypeMismatchException) ex);
        }

        if (ex instanceof  DataValidationException) {            
            return getToDoItemValidationError_test((DataValidationException) ex);
        }
        return new ToDoItemValidationError();
    }
    
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
    
    private ToDoItemValidationError getToDoItemValidationError(MethodArgumentNotValidException ex) {
        if (ex.getBindingResult().getFieldError() != null
                && ex.getBindingResult().getFieldError().getRejectedValue() != null) {
            FieldError fieldError = ex.getBindingResult().getFieldError();
            return createToDoItemValidationError("params", fieldError.getField(),  fieldError.getDefaultMessage(), fieldError.getRejectedValue().toString());
        }
        return createToDoItemValidationError("params", "text", "Must be between 1 and 50 chars long", "");
    }
    
    private ToDoItemValidationError getToDoItemValidationError_test(DataValidationException ex) {
        if (ex.getMessage() != null) {            
            return buidValidationError("params", "text", "Must be between 1 and 50 chars long", "");
        }
        return buidValidationError("params", "text", "Must be between 1 and 50 chars long", "");
    }
    
    private ToDoItemValidationError getToDoItemValidationError(MethodArgumentTypeMismatchException ex) {
        if (ex.getName() != null && ex.getValue() != null) {
            String field = ex.getName();
            String value = ex.getValue().toString();

            String message = String.format("The value provided '%s' mismatches the type required for the argument %s", value, field);
            return createToDoItemValidationError("params", ex.getName(),message, ex.getValue().toString());
        }

        return createToDoItemValidationError("params", "parameter", "An error has occurred", "value");
    }


    private ToDoItemValidationError createToDoItemValidationError(String location,String param, String message, String value) {
            	
    	ToDoItemValidationError vError = new ToDoItemValidationError();
    	ToDoItemValidationErrorDetails fErrorDetails = new ToDoItemValidationErrorDetails();
    	
    	vError.addDetailsItem(fErrorDetails.location(location).msg(message).param(param).value(value));
    	
        return vError;
    }
    
    //buidValidationError
    
    private ToDoItemValidationError buidValidationError(String location,String param, String message, String value) {
    	
    	ToDoItemValidationError vError = new ToDoItemValidationError();    	
    	ToDoItemValidationErrorDetails fErrorDetails = new ToDoItemValidationErrorDetails();
    	List<ToDoItemValidationErrorDetails> details = new ArrayList<ToDoItemValidationErrorDetails>();
    	vError.addDetailsItem(fErrorDetails.location(location).msg(message).param(param).value(value));
    	details.add(fErrorDetails);
    	vError.setDetails(details);
    	vError.setName("ValidationError");
        return vError;
    }
}
