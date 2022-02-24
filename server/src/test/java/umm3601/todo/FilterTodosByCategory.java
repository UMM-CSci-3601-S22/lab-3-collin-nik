package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Tests umm3601.todo.Database filterTodosByAge and listTodos with _company_ query
 * parameters
 */
public class FilterTodosByCategory {

  @Test
  public void filterTodosByCategory() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] groceryTodos = db.filterTodosByCategory(allTodos, "groceries");
    assertEquals(76, groceryTodos.length, "Incorrect number of todos with category groceries");

    Todo[] homeworkTodos = db.filterTodosByCategory(allTodos, "homework");
    assertEquals(79, homeworkTodos.length, "Incorrect number of todos with category homework");
  }

  @Test
  public void listTodosWithCategoryFilter() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("category", Arrays.asList(new String[] {"groceries"}));
    Todo[] groceryTodos = db.listTodos(queryParams);
    assertEquals(76, groceryTodos.length, "Incorrect number of todos with category groceries");

    queryParams.put("category", Arrays.asList(new String[] {"homework"}));
    Todo[] homeworkTodos = db.listTodos(queryParams);
    assertEquals(79, homeworkTodos.length, "Incorrect number of todos with category homework");
  }
}
