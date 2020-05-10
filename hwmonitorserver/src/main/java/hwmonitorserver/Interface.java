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
  public static final String MB = "2";
  public static final String CPUTop = "3";
  public static final String CPUEnd = "22";

  public static final String GPUTop = "28";
  public static final String GPUClock ="30";
  public static final String GPUMemoryClock ="31";
  public static final String GPULoad ="36";
  public static final String GPUTemp ="34";
  public static final String GPUMemoryUsed ="46"; 
  public static final String GPUMemoryFree ="45"; 
  public static final String GPUFan ="41"; 
  
  public static final String RamUsedMemory = "26";
  public static final String RamFreeMemory = "27";

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

    try
    {
      //CPU
      hw.cpu.Name =  Iterables.get(Linkermap.get(CPUTop), TextPos); // 0 = Text; 1 = Value;
      for(int i = Integer.parseInt(CPUTop); i < Integer.parseInt(CPUEnd); i++)
      {
        if(Iterables.get(Linkermap.get(Integer.toString(i)), TextPos).contains("CPU"))
        {
          hw.cpu.Load.add(ParseUtil.CutSpecialSymbols(Iterables.get(Linkermap.get(Integer.toString(i)), ValuePos)));
        }
      }
      hw.cpu.Cores = hw.cpu.Load.size()/2;

      //GPU
      hw.gpu.Name =  Iterables.get(Linkermap.get(GPUTop), TextPos); // 0 = Text; 1 = Value;
      hw.gpu.CoreClock = (int) ParseUtil.CutCharacters(Iterables.get(Linkermap.get(GPUClock), ValuePos));  //MHz
      hw.gpu.MemoryClock = (int) ParseUtil.CutCharacters(Iterables.get(Linkermap.get(GPUMemoryClock), ValuePos));  //MHz
      hw.gpu.CoreLoad = ParseUtil.CutSpecialSymbols(Iterables.get(Linkermap.get(GPULoad), ValuePos));
      hw.gpu.Temperature = ParseUtil.CutSpecialSymbols(Iterables.get(Linkermap.get(GPUTemp), ValuePos));
      hw.gpu.UsedMemory = ParseUtil.CutCharacters(Iterables.get(Linkermap.get(GPUMemoryUsed), ValuePos)); //MB
      hw.gpu.AvailableMemory = ParseUtil.CutCharacters(Iterables.get(Linkermap.get(GPUMemoryFree), ValuePos)); //MB
      hw.gpu.FanSpeed = (int) ParseUtil.CutCharacters(Iterables.get(Linkermap.get(GPUFan), ValuePos));

      //RAM
      hw.ram.AvailableMemory = (int) (ParseUtil.CutCharacters(Iterables.get(Linkermap.get(RamFreeMemory), ValuePos))*1000); //MB
      hw.ram.UsedMemory = (int) (ParseUtil.CutCharacters(Iterables.get(Linkermap.get(RamUsedMemory), ValuePos))*1000); //MB

      //MB
      hw.mb.Name = Iterables.get(Linkermap.get(MB), TextPos);
    }
    catch (NullPointerException ex){}
  }  
}

public class Interface {
  
  public OpenHardwareMonitorInterface OHWMInterface  = new OpenHardwareMonitorInterface();
  public Interface() {
    //OHWMInterface = new OpenHardwareMonitorInterface();      
  }
}