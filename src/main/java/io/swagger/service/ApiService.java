/**
 * 
 */
package io.swagger.service;

import io.swagger.model.BalanceTestResult;
import io.swagger.model.ToDoItem;
import io.swagger.model.ToDoItemUpdateRequest;

/**
 * @author dgpats
 *  This is the abstract service for the controllers.
 */
public interface ApiService {
	
	BalanceTestResult isBalanced(String expr);
	
	ToDoItem todoPost(ToDoItem todo);

    ToDoItem todoIdGet(Integer id);   

    ToDoItem updateToDoItem(ToDoItemUpdateRequest toDoItemUpdateRequest, Integer id);

}
