package actionUtils;

import io.restassured.path.json.JsonPath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilActions {

    Properties properties;
    public UtilActions(){
        this.properties = new Properties();
        try{
            this.properties.load(Files.newInputStream(Paths.get("src/test/resources/config/Dev.properties")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String replaceParametrizedValuesInString(String value){
        Pattern pattern = Pattern.compile("\\{\\{.*?}}");
        Matcher matcher = pattern.matcher(value);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            sb.delete(0, sb.length());
            String keyword = matcher.group().replaceAll("\\{\\{", "").replaceAll("\\}\\}","");
            matcher.appendReplacement(sb, getPropertyKey(keyword));
            matcher.appendTail(sb);
            matcher = pattern.matcher(sb.toString());
        }
        return sb.toString();
    }

    public String getPropertyKey(String key){
        return this.properties.getProperty(key);
    }

    public List<Object> getJsonPath(String apiResponse, String key)	{
        JsonPath jsonPath = new JsonPath(apiResponse);
        return jsonPath.getList(key);
    }


}
