/**
 * 
 */
package io.swagger.repositories;

import io.swagger.model.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dgpats
 * This class is used as a persistance object in H2 DB.
 */
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Integer> {

}
