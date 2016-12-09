package fit5192.utility;
import org.apache.commons.codec.binary.Base64;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SearchImpl{

    public String search(String content, int length, SearchMethod method) {
        String searchContent = content;
        searchContent = searchContent.replaceAll(" ", "%20");
        String accountKey = "tZV9cZdEHd9Ww5gru7tAh1vM2wqAqdzblS3pHEjqgMw";
        HttpURLConnection conn = null;
        byte[] accountKeyBytes = Base64.encodeBase64((accountKey + ":" + accountKey).getBytes());
        String accountKeyEnc = new String(accountKeyBytes);
        String parameters = "%27&$top=" + length + "&$format=JSON&Market=%27en-us%27";
        String path = "https://api.datamarket.azure.com/Bing/Search/";
        switch (method) {
            case WEB:
                path += "Web";
                break;
            case IMAGE:
                path += "Image";
                break;
        }
        path += "?Query=%27" + searchContent + parameters;
        try {
            System.out.println("path:" + path);
            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Basic " + accountKeyEnc);
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            System.out.println("Output from Server .... \n");
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            System.out.println(sb.toString());
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return null;
    }

}
