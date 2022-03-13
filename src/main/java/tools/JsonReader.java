package tools;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.FileReader;

public abstract class JsonReader {

    protected String filePath;
    protected JsonObject fileContent;
    protected Gson gson;

    public JsonReader(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        this.gson = new Gson();
        this.fileContent = gson.fromJson(
                new com.google.gson.stream.JsonReader(new FileReader(filePath)),
                JsonObject.class
        );
    }

    public JsonObject getFileContent(){
        return this.fileContent;
    }
}
