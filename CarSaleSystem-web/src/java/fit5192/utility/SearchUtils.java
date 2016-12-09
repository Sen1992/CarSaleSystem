/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fit5192.utility;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author weiyu
 */
public class SearchUtils {

    public static List<Bing> search(String content, int length, SearchMethod method) {
        List<Bing> bings = new ArrayList<>();
        String result = new SearchImpl().search(content, length, method);
        JSONObject jsonObject = JSONObject.fromObject(result);
        JSONObject searches = jsonObject.getJSONObject("d");

        JSONArray results = searches.getJSONArray("results");
        for(int i = 0; i < results.size(); i++) {

            JSONObject object = results.getJSONObject(i);
            Bing bing = new Bing();
            switch (method) {
                case WEB:
                    String title = object.getString("Title");
                    String description = object.getString("Description");
                    String displayUrl = object.getString("DisplayUrl");
                    bing.setTitle(title);
                    System.out.println("search"+displayUrl);
                    bing.setDescription(description);
                    bing.setDisplayUrl(displayUrl);
                    break;

                case IMAGE:
                    JSONObject thumbnail = object.getJSONObject("Thumbnail");
                    String mediaUrl = thumbnail.getString("MediaUrl");
                    System.out.println("search"+thumbnail);
                    System.out.println("search"+mediaUrl);
                    bing.setMediaUrl(mediaUrl);
                    break;
            }
            bings.add(bing);
        }
        return bings;
    }
}
