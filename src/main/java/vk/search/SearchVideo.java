package vk.search;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class SearchVideo {
    private String accessTocen;
    private List<String[]> animeList = new ArrayList<String[]>();

    public List<String[]> getAnimeList() {
        return animeList;
    }

    public SearchVideo(String accessTocen) {
        this.accessTocen = accessTocen;
    }

    public void makeAnimeList (String startRequest) throws ParseException, IOException, URISyntaxException, InterruptedException {

        animeList.clear();
        String copyRequest = startRequest;

        while (true){
            URL tempURL = search(copyRequest);
            if (tempURL == null) {
                break;
            }

            URL directLink = getDirectLink(search(copyRequest));

            //Добавить в лист поисковое название
            addNewAnnimeInList(copyRequest, directLink.toString());

            copyRequest = incrementRewuest(copyRequest);

            Thread.sleep(500);
        }
    }

    private URL getDirectLink(URL link) throws URISyntaxException, IOException {

        StringBuilder sb = new StringBuilder();
        URL pageURL = link;
        URLConnection uc = pageURL.openConnection();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        uc.getInputStream()));
        try {
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
        } finally {
            br.close();
        }

        return parseSourceCode(sb.toString());
    }

    private URL parseSourceCode (String sourceCode) throws MalformedURLException, UnsupportedEncodingException {

        String animeURL = sourceCode.substring(
                sourceCode.indexOf("&amp;url720=") + 12,
                sourceCode.indexOf(".720.mp4?extra=") + 8
        );

        String animeName = sourceCode.substring(
                sourceCode.indexOf("var video_title = '") + 19,
                sourceCode.indexOf("var video_author =") - 2
        );

        //addNewAnnimeInList(animeName, animeURL);

        return new URL(animeURL);
    }

    private void addNewAnnimeInList(String animeName, String animeURL) throws UnsupportedEncodingException {

        /*String decodingAnimeName = java.net.URLDecoder.decode(animeName, "UTF-8");
        decodingAnimeName = decodingAnimeName.replace('+', ' ');

        animeList.add(new String[] {decodingAnimeName, animeURL});*/

        animeList.add(new String[] {animeName, animeURL});
    }

    private URL search (String request) throws URISyntaxException, IOException, ParseException {

        StringBuilder urlBuilder = new StringBuilder();

        urlBuilder.append("https://api.vk.com/method/video.search?");
        urlBuilder.append("q=").append(URLEncoder.encode(request)).append("&");
        urlBuilder.append("sort=").append("2").append("&");
        urlBuilder.append("hd=").append("5").append("&");
        urlBuilder.append("adult=").append("0").append("&");
        urlBuilder.append("filters=").append("long").append("&");
        urlBuilder.append("count=").append("5").append("&");
        urlBuilder.append("v=").append("5.24").append("&");
        urlBuilder.append("access_token=").append(accessTocen);


        URL url2 = new URL(urlBuilder.toString());

        BufferedReader reader = new BufferedReader(new InputStreamReader(url2.openStream()));

        String json = reader.readLine();
        reader.close();

        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(json);

        JSONObject temp_json = (JSONObject) jsonObj.get("response");

        if ((temp_json.get("count").toString().equals("0"))) {
            return null;
        }

        JSONArray ja = (JSONArray) temp_json.get("items");
        JSONObject temp_json1 = (JSONObject) ja.get(0);

        return new URL(temp_json1.get("player").toString());
    }

    private String incrementRewuest(String request) {

        String result = request;

        for (int index = 0; index < result.length(); index++)
            if (Character.isDigit(result.charAt(index)) && result.charAt(index - 1) == ' ') {
                int startIndex = index;

                while (index + 1 < result.length() && Character.isDigit(result.charAt(index + 1))) {
                    index++;
                }

                int endIndex = index;

                if (Integer.parseInt(result.substring(startIndex, endIndex + 1)) == 9) {
                    index++;
                }

                result = String.format("%s%d%s",
                        result.substring(0, startIndex),
                        Integer.parseInt(result.substring(startIndex, endIndex + 1)) + 1,
                        result.substring(endIndex + 1));
            }

        return result;
    }
}
