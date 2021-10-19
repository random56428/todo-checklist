package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a task with a note description and whether it's checked as completed or not
public class Task implements Writable {
    private String note;
    private boolean checked;

    //EFFECTS: constructs a task with given note description, set checked to false
    public Task(String note) {
        this.note = note;
        this.checked = false;
    }

    //EFFECTS: get the note description
    public String getNote() {
        return this.note;
    }

    //EFFECTS: set the note description
    public void setNote(String n) {
        this.note = n;
    }

    //MODIFIES: this
    //EFFECTS: check the task as completed, checked becomes true
    public void check() {
        this.checked = true;
    }

    //MODIFIES: this
    //EFFECTS: uncheck the task as incomplete, checked becomes false
    public void uncheck() {
        this.checked = false;
    }

    //EFFECTS: returns true if note is checked (complete), otherwise return false (incomplete)
    public boolean isChecked() {
        return this.checked;
    }

    // This method references code from this repository
    // Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    //EFFECTS: puts task note and checked status in a json object and returns it
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("task", note);
        json.put("check", checked);
        return json;
    }
}