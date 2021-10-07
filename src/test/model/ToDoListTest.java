package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {
    private ToDoList testToDoList;

    @BeforeEach
    public void setup() {
        this.testToDoList = new ToDoList();
    }

    @Test
    public void constructorTest() {
        assertEquals("", testToDoList.viewToDoList());
        assertEquals(0, testToDoList.getIncompleteTasks());
        assertEquals(0, testToDoList.getCompletedTasks());
    }

    @Test
    public void addTaskTest() {
        Task testTask = new Task("1");
        testToDoList.addTask(testTask);

        assertEquals("1", testToDoList.viewToDoList());
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
        assertEquals(2, testToDoList.getIncompleteTasks());
        assertEquals(0, testToDoList.getCompletedTasks());
    }

    @Test
    public void deleteTaskTest() {
        Task testTask = new Task("1");
        testToDoList.addTask(testTask);
        testToDoList.deleteTask("1");

        assertEquals("", testToDoList.viewToDoList());
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

        testToDoList.deleteTask("same note");

        assertEquals("1, same note", testToDoList.viewToDoList());
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

        testToDoList.deleteTask("task that doesn't exist");

        assertEquals("0, 1, 2", testToDoList.viewToDoList());
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

        testToDoList.editTask("3", "test");

        assertEquals("0, 1, 2, test, 4, 5", testToDoList.viewToDoList());
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

        testToDoList.editTask("task that doesn't exist", "test");

        assertEquals("0, 1, 2", testToDoList.viewToDoList());
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

        testToDoList.checkTask("4");

        assertEquals("0, 1, 2, 3, 5", testToDoList.viewToDoList());
        assertEquals(5, testToDoList.getIncompleteTasks());
        assertEquals(1, testToDoList.getCompletedTasks());

        testToDoList.checkTask("2");

        assertEquals("0, 1, 3, 5", testToDoList.viewToDoList());
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

        testToDoList.checkTask("task that doesn't exist");

        assertEquals("0, 1, 2", testToDoList.viewToDoList());
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

        testToDoList.uncheckTask("2");

        assertEquals("0, 4, 5, 2", testToDoList.viewToDoList());
        assertEquals(4, testToDoList.getIncompleteTasks());
        assertEquals(2, testToDoList.getCompletedTasks());

        testToDoList.uncheckTask("1");

        assertEquals("0, 4, 5, 2, 1", testToDoList.viewToDoList());
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

        testToDoList.uncheckTask("task that doesn't exist");

        assertEquals("0", testToDoList.viewToDoList());
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
}