/**
 * 
 */
package io.swagger.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.api.TasksApi;
import io.swagger.api.TasksApiController;
import io.swagger.model.BalanceTestResult;
import io.swagger.model.ToDoItemValidationError;
import io.swagger.model.ToDoItemValidationErrorDetails;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * @author dgpats
 *
 */


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TasksApiControllerTest {
	
	
	@Autowired
	private TasksApi controller = null;	
	
	
	@InjectMocks
	MockHttpServletRequest request;		
	
	@InjectMocks
	MockHttpServletResponse response;
	

		
	@Test
	public void testIsBalanceFalse() {
		
		BalanceTestResult testResult = null;	
		UriComponentsBuilder builder = UriComponentsBuilder.newInstance();			
		String input = "[{)]";		
		ResponseEntity<?>  res = controller.tasksValidateBracketsGet(input, request, response, builder);
		if(res.hasBody()) {
			Object obj = res.getBody();
			if(obj instanceof BalanceTestResult) {
				assertTrue(true);
				testResult = (BalanceTestResult) obj;				
				assertEquals("[{)]", testResult.getInput());
				assertEquals(false, testResult.isIsBalanced());
			}			
		}
	}
	
	@Test
	public void testIsBalanceTrue() {
		UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
		String input = "({[]})";			
		ResponseEntity<?>  res = controller.tasksValidateBracketsGet(input, request, response, builder);
		if(res.hasBody()) {
			Object obj = res.getBody();
			if(obj instanceof BalanceTestResult) {
				assertTrue(true);
				BalanceTestResult testResult = (BalanceTestResult) obj;				
				assertEquals("({[]})", testResult.getInput());
				assertEquals(true, testResult.isIsBalanced());
			}			
		}
	} 
	
	@Test
	public void tasksValidateBracketsGet_Test3() {
		
		ToDoItemValidationError validationError = null;		
		UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
		String input = "";	
		ResponseEntity<?>  res = controller.tasksValidateBracketsGet(input, request, response, builder);
		if(res.hasBody()) {
			Object obj = res.getBody();
			if(obj instanceof ToDoItemValidationError) {
				assertTrue(true);
				validationError = (ToDoItemValidationError) obj;
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
}
