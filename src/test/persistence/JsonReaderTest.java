package persistence;

import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// This class references code from this repository
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a test class for JsonReader
public class JsonReaderTest {

    @Test
    public void testReaderFileNotFound() {
        JsonReader reader = new JsonReader("./data/notTheFile.json");
        try {
            reader.read();
            fail("Expected an exception to be thrown");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    public void testReaderEmptyToDoList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyToDoList.json");
        try {
            ToDoList td = reader.read();
            assertEquals(0, td.getIncompleteTasks());
            assertEquals(0, td.getCompletedTasks());
            assertEquals("", td.viewToDoList(false));
            assertEquals("", td.viewToDoList(true));
        } catch (IOException e) {
            fail("Caught IOException");
        }
    }

    @Test
    public void testReaderNotEmptyToDoList() {
        JsonReader reader = new JsonReader("./data/testReaderNotEmptyToDoList.json");
        try {
            ToDoList td = reader.read();
            assertEquals(2, td.getIncompleteTasks());
            assertEquals(2, td.getCompletedTasks());
            assertEquals("buns, tomato", td.viewToDoList(false));
            assertEquals("cheese, patty", td.viewToDoList(true));
        } catch (IOException e) {
            fail("Caught IOException");
        }
    }
}
