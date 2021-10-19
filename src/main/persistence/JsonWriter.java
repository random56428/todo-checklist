package persistence;

import model.ToDoList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// This class references code from this repository
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a writer that writes json representation of to-do list onto a file
public class JsonWriter {
    private PrintWriter writer;
    private String destination;

    //EFFECTS: construct a writer that writes to the destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    //MODIFIES: this
    //EFFECTS: opens writer at destination,
    //throws FileNotFoundException if file at destination does not exist or cannot be opened
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    //MODIFIES: this
    //EFFECTS: writes to-do list as json representation onto a file
    public void write(ToDoList td) {
        JSONObject json = td.toJson();
        writer.print(json.toString(4));
    }

    //MODIFIES: this
    //EFFECTS: closes writer
    public void close() {
        writer.close();
    }
}
