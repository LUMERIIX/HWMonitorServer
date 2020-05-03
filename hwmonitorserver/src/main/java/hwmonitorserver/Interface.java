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
import com.google.common.collect.Multimap;
import com.google.gson.JsonArray;

import org.json.JSONException;

class OpenHardwareMonitorInterface {
  public static final int PCNAME = 1;
  public static final int MB = 1;
  public static int CPUTop = 3;
  public static final int CPUCores = 4; //Includes Array with all Cores
  
  public static final int RAMTop = 22;
  public static final int UsedMemory = 26;
  public static final int FreeMemory = 27;

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

    //hw.cpu.Name = Linkermap.get(CPUTop);

    /*try{
      JsonArray stage1 = obj.get("Children").getAsJsonArray();  //stage 1 // 7 children
      System.out.println("Children \n \n" + stage1 + "\n" + "\n" );  //Debug
      JsonObject stage2 = stage1.get(0).getAsJsonObject();
      JsonArray CompList = stage2.get("Children").getAsJsonArray();
      System.out.println("Children \n \n" + CompList + "\n" + "\n" );  //Debug
  
      for(int i = 0; i < CompList.size(); i++){
        int temp = CompList.get(i).getAsJsonObject().get("id").getAsInt();
        switch(temp){
          case 1:
            System.out.println("case1");
          break;
  
          case 2:
            JsonArray MB = CompList.get(i).getAsJsonObject().get("Children").getAsJsonArray();
            //JsonArray Load = CompList.get(i).getAsJsonObject().get("Children").getAsJsonArray().get(0).getAsJsonArray();
            System.out.println("case2");
          break;
  
          case 3:
            JsonArray LoadArray = CompList.get(i).getAsJsonObject().get("Children").getAsJsonArray().get(0).getAsJsonObject().get("Children").getAsJsonArray();
            String Load = LoadArray.get(0).getAsJsonObject().get("Value").getAsString();
            System.out.println(Load);
          break;
        };
      }
    } catch (JSONException ex) {
      ex.printStackTrace();
    }*/
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