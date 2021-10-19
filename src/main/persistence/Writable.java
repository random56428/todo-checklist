package persistence;

import org.json.JSONObject;

// This interface references code from this repository
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents an interface that allows data to be written and translated
public interface Writable {

    //EFFECTS: returns this as json object
    public JSONObject toJson();
}
