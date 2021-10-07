package model;

// Represents a task with a note description and whether it's checked as completed or not
public class Task {
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

}
