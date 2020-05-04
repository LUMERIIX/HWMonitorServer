package hwmonitorserver;

import java.util.*; 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import com.google.gson.JsonArray;

import org.json.JSONException;

class OpenHardwareMonitorInterface {
  public static final int TextPos = 0;
  public static final int ValuePos = 1;
  public static final String PCNAME = "1";
  public static final String MB = "1";
  public static final String CPUTop = "3";
  public static final String CPUEnd = "22";
  public static final String CPUCores = "4"; //Includes Array with all Cores
  
  public static final String RAMTop = "22";
  public static final String UsedMemory = "26";
  public static final String FreeMemory = "27";

  public OpenHardwareMonitorInterface()
  {

  }

  public static void getMapArray(JsonArray array, Multimap <String,String> Linkermap)
  {
    for(int i = 0; i < array.size();i++)
      {
          Object value = array.get(i);
          if(value instanceof JsonObject)
          {
            JsonObject obj = (JsonObject) value;
            getMapObject(obj, Linkermap);
          }
          else if(value instanceof JsonArray)
          {
            JsonArray arr = (JsonArray) value;
            getMapArray(arr, Linkermap);
          }
      }
  }

  public static void getMapObject(JsonObject object, Multimap <String,String> Linkermap)
  {
      Set<String> keymap = object.keySet();
      Iterator<String> iterator = keymap.iterator();

      Linkermap.put(object.get("id").getAsString(), object.get("Text").getAsString());
      Linkermap.put(object.get("id").getAsString(), object.get("Value").getAsString());
    
      while(iterator.hasNext())
      {
          Object value = object.get(iterator.next());

          if(value instanceof JsonObject)
          {
            JsonObject obj = (JsonObject) value;
            getMapObject(obj, Linkermap);
          }
          else if(value instanceof JsonArray)
          {
            JsonArray arr = (JsonArray) value;
            getMapArray(arr, Linkermap);
          }
      }
  }

  public String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public JsonObject readJsonFromUrl(String url) throws IOException, JSONException {
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

  public void parseJson (JsonObject obj, Hardware hw) throws IOException, JSONException {
    assert(obj.isJsonObject());

    Multimap<String, String> Linkermap = ArrayListMultimap.create();
    getMapObject(obj, Linkermap);

    System.out.println(Linkermap);

    try
    {
      hw.cpu.Name =  Iterables.get(Linkermap.get(CPUTop), TextPos); // 0 = Text; 1 = Value;
      for(int i = Integer.parseInt(CPUTop); i < Integer.parseInt(CPUEnd); i++)
      {
        if(Iterables.get(Linkermap.get(Integer.toString(i)), TextPos).contains("CPU"))
        {
          double val = ParseUtil.CutSpecialChars(Iterables.get(Linkermap.get(Integer.toString(i)), ValuePos));
          Arrays.set(hw.cpu.Load,hw.cpu.Load.length+1,val);
        }
      }
    }
    catch (NullPointerException ex){}
  }  
}

public class Interface {
  
  public OpenHardwareMonitorInterface OHWMInterface  = new OpenHardwareMonitorInterface();
  private int test;
  public Interface() {
    //OHWMInterface = new OpenHardwareMonitorInterface();      
    test = 0;
  }
}