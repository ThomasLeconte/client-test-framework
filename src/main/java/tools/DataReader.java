package tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import exceptions.DataNotFoundException;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class DataReader extends JsonReader {

    private String SEPARATOR = "/";

    public DataReader(String filePath) throws FileNotFoundException {
        super(filePath);
        this.SEPARATOR = "/";
    }

    public void setSeparator(String separator) {
        this.SEPARATOR = separator;
    }

    public JsonElement getDonnee(JsonElement jsonFile, String jsonPath) throws DataNotFoundException {
        List<String> pathExploded = Arrays.asList(jsonPath.split(SEPARATOR));
        JsonElement result = jsonFile;
        for (int i = 0; i < pathExploded.size(); i++) {
            if (result.isJsonArray()) {
                if (Integer.parseInt(pathExploded.get(0)) > 0) {
                    if (result.getAsJsonArray().get(0).isJsonPrimitive()) {
                        result = ((JsonArray) result).get(0).getAsJsonPrimitive();
                    } else {
                        try{
                            result = result.getAsJsonArray().get(Integer.parseInt(pathExploded.get(0))).getAsJsonObject();
                        }catch (IndexOutOfBoundsException e){
                            throw new DataNotFoundException(jsonPath, "OUT_OF_RANGE");
                        }
                    }
                } else {
                    result = result.getAsJsonObject().getAsJsonPrimitive(pathExploded.get(0));
                }
            } else {
                if (result.getAsJsonObject().get(pathExploded.get(0)).isJsonPrimitive()) {
                    result = result.getAsJsonObject().getAsJsonPrimitive(pathExploded.get(0));
                } else {
                    result = result.getAsJsonObject().get(pathExploded.get(0));
                }
            }
            jsonPath = jsonPath.replace(jsonPath.contains(pathExploded.get(0) + SEPARATOR)
                    ? pathExploded.get(0) + SEPARATOR
                    : pathExploded.get(0), "");
            if (!jsonPath.equals("") && Character.toString(jsonPath.charAt(0)).equals(SEPARATOR)) {
                jsonPath = jsonPath.replaceFirst(SEPARATOR, "");
            }
            pathExploded = Arrays.asList(jsonPath.split(SEPARATOR));
            if (result != null && (i + 1) >= pathExploded.size() && !jsonPath.equals("")) {
                result = getDonnee(result, jsonPath);
                break;
            }
        }
        return result;
    }
}
