package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// A test class for Task class
public class TaskTest {
    private Task testTask;

    @BeforeEach
    public void setup() {
        this.testTask = new Task("dinner at 6pm!");
    }

    @Test
    public void constructorTest() {
        assertEquals("dinner at 6pm!", testTask.getNote());
        assertFalse(testTask.isChecked());
    }

    @Test
    public void getNoteTest() {
        assertEquals("dinner at 6pm!", testTask.getNote());
    }

    @Test
    public void setNoteTest() {
        testTask.setNote("dinner postponed to 7pm!");
        assertEquals("dinner postponed to 7pm!", testTask.getNote());
    }

    @Test
    public void checkTest() {
        testTask.check();
        assertTrue(testTask.isChecked());
    }

    @Test
    public void uncheckTest() {
        testTask.check();
        testTask.uncheck();
        assertFalse(testTask.isChecked());
    }

    @Test
    public void isCheckedTest() {
        assertFalse(testTask.isChecked());
        testTask.check();
        assertTrue(testTask.isChecked());
    }

}
