package io.swagger.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import io.swagger.api.TodoApi;
import io.swagger.model.ToDoItem;
import io.swagger.model.ToDoItemAddRequest;
import io.swagger.model.ToDoItemNotFoundError;
import io.swagger.model.ToDoItemUpdateRequest;
import io.swagger.model.ToDoItemValidationError;
import io.swagger.model.ToDoItemValidationErrorDetails;
import junit.framework.TestCase;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TodoApiControllerTest extends TestCase {
	
	
	@Autowired
	private TodoApi cntrl;	
	
	@InjectMocks
	MockHttpServletRequest request;		
	
	@InjectMocks
	MockHttpServletResponse response;
	

	@Test
	public void TodoPostTest() {
		
		final ToDoItemAddRequest body = new ToDoItemAddRequest();	
		body.setText("Are you done with Auto Api assignment.");
		ResponseEntity<?>  res = cntrl.todoPost(body);
		if(res.hasBody()) {
			Object obj = res.getBody();
			if(obj instanceof ToDoItem) {
				assertTrue(true);
				ToDoItem todoItem = (ToDoItem) obj;				
				assertEquals("1", todoItem.getId().toString());
				assertEquals("Are you done with Auto Api assignment.", todoItem.getText());				
				assertEquals(new Boolean(false), todoItem.isIsCompleted());
			}			
		}		
	} 	
	
	@Test
	public void todoIdPatchTest() {
		
		final ToDoItemUpdateRequest body = new ToDoItemUpdateRequest();		
		body.setText("Are you done with Auto Api assignment.");
		body.isCompleted(true);
		Integer id = 1;
		ResponseEntity<?>  res = cntrl.todoIdPatch(id, body, request, response);
		if(res.hasBody()) {
			Object obj = res.getBody();
			if(obj instanceof ToDoItem) {
				assertTrue(true);
				ToDoItem todoItem = (ToDoItem) obj;				
				assertEquals("1", todoItem.getId().toString());
				assertEquals("Are you done with Auto Api assignment.", todoItem.getText());				
				assertEquals(new Boolean(true), todoItem.isIsCompleted());
			}			
		}		
	} 
	
	@Test
	public void todoIdTest() {			
		final UriComponentsBuilder builder = UriComponentsBuilder.newInstance();		
		Integer id = 1;
		ResponseEntity<?>  res = cntrl.todoIdGet(id, request, response, builder);
		if(res.hasBody()) {
			Object obj = res.getBody();
			if(obj instanceof ToDoItem) {
				assertTrue(true);
				ToDoItem todoItem = (ToDoItem) obj;				
				assertEquals("1", todoItem.getId().toString());
				assertEquals("Are you done with Auto Api assignment.", todoItem.getText());				
				assertEquals(new Boolean(false), todoItem.isIsCompleted());
			}			
		}
	} 	
	
	
	@Test
	public void TodoPost_ValidationTest() {
		
		final ToDoItemAddRequest body = new ToDoItemAddRequest();			 
		ResponseEntity<?>  res = cntrl.todoPost(body);
		if(res.hasBody()) {
			Object obj = res.getBody();
			if(obj instanceof ToDoItemValidationError) {
				assertTrue(true);
				ToDoItemValidationError validationError = (ToDoItemValidationError) obj;
				assertEquals("ValidationError", validationError.getName());
				
				List<ToDoItemValidationErrorDetails> listDetails = validationError.getDetails();				
				assertEquals(1, listDetails.size());
				for(ToDoItemValidationErrorDetails errorDetails: listDetails) {
					assertEquals("text", errorDetails.getParam());
					assertEquals("params", errorDetails.getLocation());
					assertEquals("Must be between 1 and 50 chars long", errorDetails.getMsg());
				}				
			}			
		}		
	}
	
	@Test
	public void todoIdGet_ValidationTest() {	
		Integer id = null;
		final UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
		ResponseEntity<?>  res = cntrl.todoIdGet(id, request, response, builder);
		if(res.hasBody()) {
			Object obj = res.getBody();
			if(obj instanceof ToDoItemValidationError) {
				assertTrue(true);
				ToDoItemValidationError validationError = (ToDoItemValidationError) obj;
				assertEquals("ValidationError", validationError.getName());
				
				List<ToDoItemValidationErrorDetails> listDetails = validationError.getDetails();				
				assertEquals(1, listDetails.size());
				for(ToDoItemValidationErrorDetails errorDetails: listDetails) {
					assertEquals("text", errorDetails.getParam());
					assertEquals("params", errorDetails.getLocation());
					assertEquals("Must be between 1 and 50 chars long", errorDetails.getMsg());
				}				
			}			
		}
	}
	
	@Test
	public void todoIdGet_NotFoundTest() {				
		Integer id = 3;
		final UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
		ResponseEntity<?>  res = cntrl.todoIdGet(id, request, response, builder);
		if(res.hasBody()) {
			Object obj = res.getBody();
			if(obj instanceof ToDoItemNotFoundError) {
				assertTrue(true);
				ToDoItemNotFoundError notFoundError = (ToDoItemNotFoundError) obj;				
				assertEquals("NotFoundError", notFoundError.getName());
				assertEquals("Item with" + id + "not found", notFoundError.getDetails().get(0).getMessage());				
			}			
		}	
	}
	
	@Test
	public void todoIdPatch_ValidationTest() {
		ToDoItemUpdateRequest body = new ToDoItemUpdateRequest();
		Integer id = null;		
		ResponseEntity<?>  res = cntrl.todoIdPatch(id, body, request, response);
		if(res.hasBody()) {
			Object obj = res.getBody();
			if(obj instanceof ToDoItemValidationError) {
				assertTrue(true);
				ToDoItemValidationError validationError = (ToDoItemValidationError) obj;
				assertEquals("ValidationError", validationError.getName());
				
				List<ToDoItemValidationErrorDetails> listDetails = validationError.getDetails();				
				assertEquals(1, listDetails.size());
				for(ToDoItemValidationErrorDetails errorDetails: listDetails) {
					assertEquals("text", errorDetails.getParam());
					assertEquals("params", errorDetails.getLocation());
					assertEquals("Must be between 1 and 50 chars long", errorDetails.getMsg());
				}				
			}			
		}		
	}
	
	@Test
	public void todoIdPatch_NotFoundTest() {		
		
		Integer id = null;		
		final ToDoItemUpdateRequest body = new ToDoItemUpdateRequest();			
		ResponseEntity<?>  res = cntrl.todoIdPatch(id, body, request, response);
		if(res.hasBody()) {
			Object obj = res.getBody();
			if(obj instanceof ToDoItemNotFoundError) {
				assertTrue(true);				
				ToDoItemNotFoundError notFoundError = (ToDoItemNotFoundError) obj;				
				assertEquals("NotFoundError", notFoundError.getName());
				assertEquals("Item with" + id + "not found", notFoundError.getDetails().get(0).getMessage());				
			}			
		}		
	}	
}
