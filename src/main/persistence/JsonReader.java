package persistence;

import model.Task;
import model.ToDoList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// This class references code from this repository
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads json representation of to-do list from a file
public class JsonReader {
    private String source;

    //EFFECTS: constructs a reader that reads file from specified source
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads to-do list from source file and returns it,
    //throws IOException if an error occurs when trying to read from the file
    public ToDoList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseToDoList(jsonObject);
    }

    //EFFECTS: reads source file as string and returns it
    //throws IOException if an error occurs while opening file
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses to-do list from json object and returns it
    private ToDoList parseToDoList(JSONObject jsonObject) {
        ToDoList td = new ToDoList();
        addToDoList(td, jsonObject);
        return td;
    }

    //MODIFIES: td
    //EFFECTS: parses to-do list from json object and adds them to todolist
    private void addToDoList(ToDoList td, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("todolist");
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addToDoListTask(td, nextTask);
        }
    }

    //MODIFIES: td
    //EFFECTS: parses individual tasks from json object and adds it to todolist
    private void addToDoListTask(ToDoList td, JSONObject jsonObject) {
        String note = jsonObject.getString("task");
        Boolean checked = jsonObject.getBoolean("check");
        td.addTask(new Task(note));
        if (checked) {
            td.checkTask(note);
        }
    }
}
