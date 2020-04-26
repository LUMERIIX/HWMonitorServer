package hwmonitorserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

//import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
//import org.json.JSONObject;

enum Index{
  PCNAME(1),
  CPUNAME(3),
  CPUTOTALLOAD(5);
  private int index; 
  
  // getter method 
  public int getIndex() 
  { 
      return this.index; 
  } 

  // enum constructor - cannot be public or protected 
  private Index(int index) 
  { 
      this.index = index; 
  } 
}

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

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
          sb.append((char) cp);
        }
        return sb.toString();
      }
    
      public static JsonObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
          BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
          String jsonText = readAll(rd);
          JsonObject json = new JsonParser().parse(jsonText).getAsJsonObject();
          return json;
        } finally {
          is.close();
        }
      }
    
      public static void main(String[] args) throws IOException, JSONException {
        JsonObject json = readJsonFromUrl("http://192.168.1.20:8085/data.json");
        assert(json.isJsonObject());
        System.out.println(json + "\n" + "\n" );
      }
}
