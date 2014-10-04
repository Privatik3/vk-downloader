package vk.search;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class TestDriveSearchVideo {

    public static void main(String[] args) throws ParseException, IOException, URISyntaxException, InterruptedException {
        SearchVideo searchVideo = new SearchVideo("7268b42c52683105eda2d7e08197f522dc971bfa9dad642b967c0fc4eff89d722a633fbc20f95b0244d3d");

        searchVideo.makeAnimeList("[AniDub] Rail Wars | Железнодорожные Войны [12] [FruKt, Гамлетка Цезаревна]");

        List<String[]> animeList = searchVideo.getAnimeList();

        for (String[] anime : animeList) {
            System.out.println("-----------------------------------------------------------------");
            System.out.println("Name: " + anime[0]);
            System.out.println("URL:  " + anime[1]);
            System.out.println("-----------------------------------------------------------------\n");
        }
    }
}
