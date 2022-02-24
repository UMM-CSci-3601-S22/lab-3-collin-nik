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
@SuppressWarnings({ "MagicNumber" })
public class FilterTodosByBody {
String first = "Incididunt enim ea sit qui esse magna eu. Nisi sunt exercitation est Lorem";
String last = " consectetur incididunt cupidatat laboris commodo veniam do ut sint.";
  @Test
  public void filterTodosByBody() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] incididuntTodos = db.filterTodosByBody(allTodos, "Incididunt");
    assertEquals(84, incididuntTodos.length, "Incorrect number of todos with body phrase Incididunt");

    Todo[] incididunt2Todos = db.filterTodosByBody(allTodos, first + last);
    assertEquals(1, incididunt2Todos.length, "Incorrect number of todos with the specified body phrase");
  }

  @Test
  public void listTodosWithBodyFilter() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("body", Arrays.asList(new String[] {"Incididunt"}));
    Todo[] incididuntTodos = db.listTodos(queryParams);
    assertEquals(300, incididuntTodos.length, "Incorrect number of todos with body phrase Incididunt");

    queryParams.put("body", Arrays.asList(new String[] {first + last}));
    Todo[] incididunt2Todos = db.listTodos(queryParams);
    assertEquals(300, incididunt2Todos.length, "Incorrect number of todos with the specified body phrase");
  }
}
