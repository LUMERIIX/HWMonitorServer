package hwmonitorserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;

import org.json.JSONException;

class OpenHardwareMonitorInterface {
  public static final int PCNAME = 1;
  public static final int MB = 1;
  public static final int CPUTop = 3;
  public static final int CPUCores = 4; //Includes Array with all Cores
  
  public static final int RAMTop = 22;
  public static final int UsedMemory = 26;
  public static final int FreeMemory = 27;

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

  public static void parseJson (JsonObject obj)
  {
    assert(obj.isJsonObject());

    JsonArray stage1 = obj.get("Children").getAsJsonArray();  //stage 1 // 7 children
    System.out.println("Children \n \n" + stage1 + "\n" + "\n" );  //Debug
    JsonObject stage2 = stage1.get(0).getAsJsonObject();
    JsonArray CompList = stage2.get("Children").getAsJsonArray();
    System.out.println("Children \n \n" + CompList + "\n" + "\n" );  //Debug
    for(int i = 0; i < CompList.size(); i++){
      switch(CompList.get(i).getAsJsonObject().get("id").getAsInt())
      {
        case PCNAME:
        break;
        


      }
    }
  }  
}

public class Interface {
    private Interface() {
        OpenHardwareMonitorInterface OHWMInterface;
    }

}