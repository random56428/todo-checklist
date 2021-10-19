package persistence;

import model.ToDoList;
import org.json.JSONObject;

import java.io.IOException;

// This class references code from this repository
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads json representation of to-do list from a file
public class JsonReader {

    //EFFECTS: constructs a reader that reads file from specified source
    public JsonReader(String source) {
        //stub
    }

    //EFFECTS: reads to-do list from source file and returns it,
    //throws IOException if an error occurs when trying to read from the file
    public ToDoList read() throws IOException {
        return new ToDoList(); //stub
    }

    //EFFECTS: reads source file as string and returns it
    //throws IOException if an error occurs while opening file
    private String readFile(String source) throws IOException {
        return ""; //stub
    }

    //EFFECTS: parses to-do list from json object and returns it
    private ToDoList parseToDoList(JSONObject jsonObject) {
        return new ToDoList();
    }

    //MODIFIES: td
    //EFFECTS: parses to-do list from json object and adds them to todolist
    private void addToDoList(ToDoList td, JSONObject jsonObject) {
        //stub
    }

    //MODIFIES: td
    //EFFECTS: parses individual tasks from json object and adds it to todolist
    private void addToDoListTask(ToDoList td, JSONObject jsonObject) {
        //stub
    }
}
