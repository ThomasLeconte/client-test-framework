package tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.FileNotFoundException;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.List;

public class DataReader extends JsonReader {

    public DataReader(String filePath) throws FileNotFoundException {
        super(filePath);
    }

    public JsonElement getDonnees(JsonElement jsonFile, String jsonPath){
        List<String> pathExploded = Arrays.asList(jsonPath.split("/"));
        JsonElement result = jsonFile;
        for(int i = 0; i<pathExploded.size(); i++){
            if(result.isJsonArray()){
                if(Integer.parseInt(pathExploded.get(0)) > 0){
                    if(result.getAsJsonArray().get(0).isJsonPrimitive()){
                        result = ((JsonArray) result).get(0).getAsJsonPrimitive();
                    }else{
                        result = result.getAsJsonArray().get(Integer.parseInt(pathExploded.get(0))).getAsJsonObject();
                    }
                }else{
                    result = result.getAsJsonObject().getAsJsonPrimitive(pathExploded.get(0));
                }
            }else{
                if(result.getAsJsonObject().get(pathExploded.get(0)).isJsonPrimitive()){
                    result = result.getAsJsonObject().getAsJsonPrimitive(pathExploded.get(0));
                }else{
                    result = result.getAsJsonObject().get(pathExploded.get(0));
                }
            }
            jsonPath = jsonPath.replace(jsonPath.contains(pathExploded.get(0) + "/") ? pathExploded.get(0) + "/" : pathExploded.get(0), "");
            if(!jsonPath.equals("") && Character.toString(jsonPath.charAt(0)).equals("/")){
                jsonPath = jsonPath.replaceFirst("/", "");
            }
            pathExploded = Arrays.asList(jsonPath.split("/"));
            if(result != null && (i+1) >= pathExploded.size() && !jsonPath.equals("")){
                result = getDonnees(result, jsonPath);
                break;
            }
        }
        return result;
    }
}
