package hwmonitorserver;

public class ParseUtil {

    public static double CutSpecialChars(String str)
    {
        String temp = str.replaceAll("[^a-zA-Z0-9\\s+]", "");
        temp.trim();
        return Double.parseDouble(temp);
    } 

    public ParseUtil()
    {

    }
}
