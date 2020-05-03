package hwmonitorserver;

import com.google.gson.JsonObject;

import org.json.JSONException;

import java.io.IOException;


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
    
      public static void main(String[] args) throws IOException, JSONException {
        Interface interface1 = new Interface();
        JsonObject json = interface1.OHWMInterface.readJsonFromUrl("http://192.168.1.6:8085/data.json");
        Hardware hw = new Hardware();
        interface1.OHWMInterface.parseJson(json,hw);


      }
}
