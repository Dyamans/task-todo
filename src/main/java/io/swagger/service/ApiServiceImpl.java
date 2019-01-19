/**
 * 
 */
package io.swagger.service;

import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.datatype.threetenbp.function.BiFunction;

import io.swagger.model.BalanceTestResult;
import io.swagger.model.ToDoItem;
import io.swagger.model.ToDoItemUpdateRequest;
import io.swagger.repositories.ToDoItemRepository;

/**
 * @author dgpats
 * This is a service class for the task and todo operations.
 */
@Component
public class ApiServiceImpl implements ApiService{

	private static final Logger log = LoggerFactory.getLogger(ApiServiceImpl.class);
	
	@Autowired
    private ToDoItemRepository toDoItemRepository;	

	
	public BalanceTestResult isBalanced(final String s) {
		log.info("Service isBalanced operation");
	      return validateBrackets(s);
	}
	
	public ToDoItem todoPost(final ToDoItem todo) {
		log.info("Service todoPost operation");
        return saveAndFlush(todo);
    }
	
	
	public ToDoItem todoIdGet(final Integer id) {
		log.info("Service todoIdGet operation");
        return toDoItemRepository.findOne(id);
    }
	
	
	public ToDoItem updateToDoItem(final ToDoItemUpdateRequest toDoItemUpdateRequest, final Integer id) {
		log.info("Service updateToDoItem operation");
        ToDoItem toDoItem = toDoItemRepository.findOne(id);
        toDoItem.setText(toDoItemUpdateRequest.getText());
        toDoItem.isCompleted(toDoItemUpdateRequest.isIsCompleted());
        return toDoItemRepository.save(toDoItem);
    }	
	
	
	private BalanceTestResult validateBrackets(final String s) {
		
		if (s.isEmpty())
            return createBalanceTestResult.apply(s,true);
		
		Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++)
        {
            char current = s.charAt(i);
            if (current == '{' || current == '(' || current == '[')
            {
                stack.push(current);
            }
            if (current == '}' || current == ')' || current == ']')
            {
                if (stack.isEmpty())
                    return createBalanceTestResult.apply(s,false);
                char last = stack.peek();
                if (current == '}' && last == '{' || current == ')' && last == '(' || current == ']' && last == '[')
                    stack.pop();
                else
                    return createBalanceTestResult.apply(s,false);
            }
        }
        return stack.isEmpty()? createBalanceTestResult.apply(s,true):createBalanceTestResult.apply(s,false);
	}
	
	
	
	private BiFunction<String, Boolean, BalanceTestResult> createBalanceTestResult = ApiServiceImpl::balance;
	
	private static BalanceTestResult balance(String s, Boolean b) {
        return new BalanceTestResult(s, b);
    }
	
	private ToDoItem saveAndFlush(final ToDoItem toDoItem) {
        return toDoItemRepository.saveAndFlush(toDoItem);
    }
	
}
