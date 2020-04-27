package hwmonitorserver;

//import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;

import org.json.JSONException;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    
     Interface interface1;
      public static void main(String[] args) throws IOException, JSONException {
        JsonObject json = interface1.readJsonFromUrl("http://192.168.1.20:8085/data.json");
        System.out.println(json + "\n" + "\n" );  //Debug
        interface1.parseJson(json);
      }
}
