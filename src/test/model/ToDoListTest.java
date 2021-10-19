package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// A test class for ToDoList class
class ToDoListTest {
    private ToDoList testToDoList;

    @BeforeEach
    public void setup() {
        this.testToDoList = new ToDoList();
    }

    @Test
    public void constructorTest() {
        assertEquals("", testToDoList.viewToDoList());
        assertEquals("", testToDoList.viewCompletedList());
        assertEquals(0, testToDoList.getIncompleteTasks());
        assertEquals(0, testToDoList.getCompletedTasks());
    }

    @Test
    public void addTaskTest() {
        Task testTask = new Task("1");
        testToDoList.addTask(testTask);

        assertEquals("1", testToDoList.viewToDoList());
        assertEquals("", testToDoList.viewCompletedList());
        assertEquals(1, testToDoList.getIncompleteTasks());
        assertEquals(0, testToDoList.getCompletedTasks());
    }

    @Test
    public void addTaskTestTwoTasks() {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            taskList.add(new Task("" + i));
        }

        for (Task t : taskList) {
            testToDoList.addTask(t);
        }

        assertEquals("0, 1", testToDoList.viewToDoList());
        assertEquals("", testToDoList.viewCompletedList());
        assertEquals(2, testToDoList.getIncompleteTasks());
        assertEquals(0, testToDoList.getCompletedTasks());
    }

    @Test
    public void deleteTaskTest() {
        Task testTask = new Task("1");
        testToDoList.addTask(testTask);
        boolean result = testToDoList.deleteTask("1");

        assertTrue(result);
        assertEquals("", testToDoList.viewToDoList());
        assertEquals("", testToDoList.viewCompletedList());
        assertEquals(0, testToDoList.getIncompleteTasks());
        assertEquals(0, testToDoList.getCompletedTasks());
    }

    @Test
    public void deleteTaskTestSameTask() {
        Task testTask = new Task("1");
        testToDoList.addTask(testTask);

        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            taskList.add(new Task("same note"));
        }

        for (Task t : taskList) {
            testToDoList.addTask(t);
        }

        boolean result = testToDoList.deleteTask("same note");

        assertTrue(result);
        assertEquals("1, same note", testToDoList.viewToDoList());
        assertEquals("", testToDoList.viewCompletedList());
        assertEquals(2, testToDoList.getIncompleteTasks());
        assertEquals(0, testToDoList.getCompletedTasks());
    }

    @Test
    public void deleteTaskTestNotFound() {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            taskList.add(new Task("" + i));
        }

        for (Task t : taskList) {
            testToDoList.addTask(t);
        }

        boolean result = testToDoList.deleteTask("task that doesn't exist");

        assertFalse(result);
        assertEquals("0, 1, 2", testToDoList.viewToDoList());
        assertEquals("", testToDoList.viewCompletedList());
        assertEquals(3, testToDoList.getIncompleteTasks());
        assertEquals(0, testToDoList.getCompletedTasks());
    }

    @Test
    public void editTaskTest() {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            taskList.add(new Task("" + i));
        }

        for (Task t : taskList) {
            testToDoList.addTask(t);
        }

        boolean result = testToDoList.editTask("3", "test");

        assertTrue(result);
        assertEquals("0, 1, 2, test, 4, 5", testToDoList.viewToDoList());
        assertEquals("", testToDoList.viewCompletedList());
        assertEquals(6, testToDoList.getIncompleteTasks());
        assertEquals(0, testToDoList.getCompletedTasks());
    }

    @Test
    public void editTaskTestNotFound() {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            taskList.add(new Task("" + i));
        }

        for (Task t : taskList) {
            testToDoList.addTask(t);
        }

        boolean result = testToDoList.editTask("task that doesn't exist", "test");

        assertFalse(result);
        assertEquals("0, 1, 2", testToDoList.viewToDoList());
        assertEquals("", testToDoList.viewCompletedList());
        assertEquals(3, testToDoList.getIncompleteTasks());
        assertEquals(0, testToDoList.getCompletedTasks());
    }

    @Test
    public void checkTaskTest() {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            taskList.add(new Task("" + i));
        }

        for (Task t : taskList) {
            testToDoList.addTask(t);
        }

        boolean result = testToDoList.checkTask("4");

        assertTrue(result);
        assertEquals("0, 1, 2, 3, 5", testToDoList.viewToDoList());
        assertEquals("4", testToDoList.viewCompletedList());
        assertEquals(5, testToDoList.getIncompleteTasks());
        assertEquals(1, testToDoList.getCompletedTasks());

        result = testToDoList.checkTask("2");

        assertTrue(result);
        assertEquals("0, 1, 3, 5", testToDoList.viewToDoList());
        assertEquals("4, 2", testToDoList.viewCompletedList());
        assertEquals(4, testToDoList.getIncompleteTasks());
        assertEquals(2, testToDoList.getCompletedTasks());
    }

    @Test
    public void checkTaskTestNotFound() {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            taskList.add(new Task("" + i));
        }

        for (Task t : taskList) {
            testToDoList.addTask(t);
        }

        boolean result = testToDoList.checkTask("task that doesn't exist");

        assertFalse(result);
        assertEquals("0, 1, 2", testToDoList.viewToDoList());
        assertEquals("", testToDoList.viewCompletedList());
        assertEquals(3, testToDoList.getIncompleteTasks());
        assertEquals(0, testToDoList.getCompletedTasks());
    }

    @Test
    public void uncheckTaskTest() {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            taskList.add(new Task("" + i));
        }

        for (Task t : taskList) {
            testToDoList.addTask(t);
        }

        testToDoList.checkTask("1");
        testToDoList.checkTask("2");
        testToDoList.checkTask("3");

        boolean result = testToDoList.uncheckTask("2");

        assertTrue(result);
        assertEquals("0, 4, 5, 2", testToDoList.viewToDoList());
        assertEquals("1, 3", testToDoList.viewCompletedList());
        assertEquals(4, testToDoList.getIncompleteTasks());
        assertEquals(2, testToDoList.getCompletedTasks());

        result = testToDoList.uncheckTask("1");

        assertTrue(result);
        assertEquals("0, 4, 5, 2, 1", testToDoList.viewToDoList());
        assertEquals("3", testToDoList.viewCompletedList());
        assertEquals(5, testToDoList.getIncompleteTasks());
        assertEquals(1, testToDoList.getCompletedTasks());
    }

    @Test
    public void uncheckTaskTestNotFound() {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            taskList.add(new Task("" + i));
        }

        for (Task t : taskList) {
            testToDoList.addTask(t);
        }

        testToDoList.checkTask("1");
        testToDoList.checkTask("2");
        testToDoList.checkTask("3");

        boolean result = testToDoList.uncheckTask("task that doesn't exist");

        assertFalse(result);
        assertEquals("0", testToDoList.viewToDoList());
        assertEquals("1, 2, 3", testToDoList.viewCompletedList());
        assertEquals(1, testToDoList.getIncompleteTasks());
        assertEquals(3, testToDoList.getCompletedTasks());
    }

    @Test
    public void deleteAllCompleteTaskTest() {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            taskList.add(new Task("" + i));
        }

        for (Task t : taskList) {
            testToDoList.addTask(t);
        }

        testToDoList.checkTask("0");
        testToDoList.checkTask("2");
        testToDoList.checkTask("4");

        testToDoList.deleteAllCompleteTask();

        assertEquals("1, 3, 5", testToDoList.viewToDoList());
        assertEquals(3, testToDoList.getIncompleteTasks());
        assertEquals(0, testToDoList.getCompletedTasks());
    }

    @Test
    public void testToJson() {
        List<Task> taskList = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            taskList.add(new Task("" + i));
        }

        for (Task t : taskList) {
            testToDoList.addTask(t);
        }

        for (int i = 2; i < 4; i++) {
            testToDoList.checkTask("" + i);
        }

        JSONObject testJson = testToDoList.toJson();
        JSONArray testJsonArray = testJson.getJSONArray("todolist");

        for (int i = 0; i < 2; i++) {
            assertEquals("" + i, testJsonArray.getJSONObject(i).getString("task"));
            assertFalse(testJsonArray.getJSONObject(i).getBoolean("check"));
        }

        for (int i = 2; i < 4; i++) {
            assertEquals("" + i, testJsonArray.getJSONObject(i).getString("task"));
            assertTrue(testJsonArray.getJSONObject(i).getBoolean("check"));
        }
    }
}