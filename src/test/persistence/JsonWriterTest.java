package persistence;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// This class references code from this repository
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a test class for JsonWriter
public class JsonWriterTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            ToDoList td = new ToDoList();
            JsonWriter writer = new JsonWriter("./data/invalid?;:@~!@#$%^&*()(.json");
            writer.open();
            fail("Expected an exception to be thrown");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    public void testWriterEmptyToDoList() {
        try {
            ToDoList td = new ToDoList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyToDoList.json");
            writer.open();
            writer.write(td);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyToDoList.json");
            td = reader.read();
            assertEquals(0, td.getIncompleteTasks());
            assertEquals(0, td.getCompletedTasks());
            assertEquals("", td.viewToDoList(false));
            assertEquals("", td.viewToDoList(true));
        } catch (IOException e) {
            fail("Caught IOException");
        }
    }

    @Test
    public void testWriterNotEmptyToDoList() {
        try {
            ToDoList td = new ToDoList();
            td.addTask(new Task("lab 4pm"));
            td.addTask(new Task("lecture 1pm"));
            td.addTask(new Task("dinner 7pm"));
            td.checkTask("lecture 1pm");
            JsonWriter writer = new JsonWriter("./data/testWriterNotEmptyToDoList.json");
            writer.open();
            writer.write(td);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterNotEmptyToDoList.json");
            td = reader.read();
            assertEquals(2, td.getIncompleteTasks());
            assertEquals(1, td.getCompletedTasks());
            assertEquals("lab 4pm, dinner 7pm", td.viewToDoList(false));
            assertEquals("lecture 1pm", td.viewToDoList(true));
        } catch (IOException e) {
            fail("Caught IOException");
        }
    }
}