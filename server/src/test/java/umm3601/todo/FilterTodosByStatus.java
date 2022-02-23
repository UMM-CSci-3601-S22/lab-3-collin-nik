package umm3601.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Tests umm3601.todo.Database filterUsersByAge and listUsers with _age_ query
 * parameters
 */
// The tests here include a ton of "magic numbers" (numeric constants).
// It wasn't clear to me that giving all of them names would actually
// help things. The fact that it wasn't obvious what to call some
// of them says a lot. Maybe what this ultimately means is that
// these tests can/should be restructured so the constants (there are
// also a lot of "magic strings" that Checkstyle doesn't actually
// flag as a problem) make more sense.
@SuppressWarnings({ "MagicNumber" })
public class FilterTodosByStatus {

  @Test
  public void filterTodosByStatus() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());

    Todo[] age27Todos = db.filterTodosByStatus(allTodos, 27);
    assertEquals(3, age27Todos.length, "Incorrect number of todos with age 27");

    Todo[] age33Todos = db.filterTodosByStatus(allTodos, 33);
    assertEquals(1, age33Todos.length, "Incorrect number of todos with age 33");
  }

  @Test
  public void listTodosWithAgeFilter() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Map<String, List<String>> queryParams = new HashMap<>();

    queryParams.put("age", Arrays.asList(new String[] {"27"}));
    Todo[] age27Todos = db.listTodos(queryParams);
    assertEquals(3, age27Todos.length, "Incorrect number of todos with age 27");

    queryParams.put("age", Arrays.asList(new String[] {"33"}));
    Todo[] age33Todos = db.listTodos(queryParams);
    assertEquals(1, age33Todos.length, "Incorrect number of todos with age 33");
  }
}
