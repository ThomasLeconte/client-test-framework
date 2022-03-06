package tools;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ReferenceReader {

    private final JsonArray pagesReferences;

    public ReferenceReader(String referenceFilePath) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonObject object = gson.fromJson(
                new JsonReader(new FileReader(referenceFilePath)),
                JsonObject.class
        );
        this.pagesReferences = object.get("pages").getAsJsonArray();
    }

    public Map<String, String> getReferencesOfpage(String pageName) {
        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < this.pagesReferences.size(); i++) {
            JsonObject page = this.pagesReferences.get(i).getAsJsonObject();
            if (page.get("nom").getAsString().equals(pageName)) {
                result = page.get("references").getAsJsonObject().entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getAsString()));
                break;
            }
        }
        return result;
    }
}
