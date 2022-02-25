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
@SuppressWarnings({ "MagicNumber" })
public class FilterTodosByOwner {

  @Test
  public void filterTodosByOwner() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] blancheTodos = db.filterTodosByOwner(allTodos, "Blanche");
    assertEquals(43, blancheTodos.length, "Incorrect number of todos with owner Blanche");

    Todo[] fryTodos = db.filterTodosByOwner(allTodos, "Fry");
    assertEquals(61, fryTodos.length, "Incorrect number of todos with company Fry");
  }

  @Test
  public void listTodosWithOwnerFilter() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("owner", Arrays.asList(new String[] {"Blanche"}));
    Todo[] blancheTodos = db.listTodos(queryParams);
    assertEquals(43, blancheTodos.length, "Incorrect number of todos with owner Blanche");

    queryParams.put("owner", Arrays.asList(new String[] {"Fry"}));
    Todo[] fryTodos = db.listTodos(queryParams);
    assertEquals(61, fryTodos.length, "Incorrect number of todos with owner Fry");
  }
}
