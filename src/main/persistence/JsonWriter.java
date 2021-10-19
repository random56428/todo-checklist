package persistence;

import model.ToDoList;

import java.io.FileNotFoundException;

// This class references code from this repository
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a writer that writes json representation of to-do list onto a file
public class JsonWriter {

    //EFFECTS: construct a writer that writes to the destination file
    public JsonWriter(String destination) {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: opens writer at destination,
    //throws FileNotFoundException if file at destination does not exist or cannot be opened
    public void open() throws FileNotFoundException {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: writes to-do list data as json representation onto a file
    public void write(ToDoList td) {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: closes writer
    public void close() {
        //stub
    }

    //MODIFIES: this
    //EFFECTS: writes string onto file
    private void saveToFile(String json) {
        //stub
    }
}
