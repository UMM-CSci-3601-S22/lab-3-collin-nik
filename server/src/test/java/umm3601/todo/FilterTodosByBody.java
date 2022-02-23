package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Tests umm3601.todo.Database filterUsersByAge and listUsers with _company_ query
 * parameters
 */
public class FilterTodosByBody {

  @Test
  public void filterTodosByBody() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] ohmnetTodos = db.filterTodosByBody(allTodos, "OHMNET");
    assertEquals(2, ohmnetTodos.length, "Incorrect number of todos with company OHMNET");

    Todo[] kineticutTodos = db.filterTodosByBody(allTodos, "KINETICUT");
    assertEquals(1, kineticutTodos.length, "Incorrect number of todos with company KINETICUT");
  }

  @Test
  public void listTodosWithBodyFilter() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("company", Arrays.asList(new String[] {"OHMNET"}));
    Todo[] ohmnetTodos = db.listTodos(queryParams);
    assertEquals(2, ohmnetTodos.length, "Incorrect number of todos with company KINETICUT");

    queryParams.put("company", Arrays.asList(new String[] {"KINETICUT"}));
    Todo[] kineticutTodos = db.listTodos(queryParams);
    assertEquals(1, kineticutTodos.length, "Incorrect number of todos with company KINETICUT");
  }
}
