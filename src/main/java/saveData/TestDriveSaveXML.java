package saveData;

import java.util.*;

public class TestDriveSaveXML {
    public static void main(String[] args) throws Exception {

        List<String[]> animeList = new ArrayList<String[]>();
        animeList.add(new String[] {"test 01", "http://cs540304.vk.com/u104430199/videos/15c49f3711.720.mp4"});
        animeList.add(new String[] {"test 02", "http://cs540304.vk.com/u104430199/videos/15c49f3711.720.mp4"});
        animeList.add(new String[] {"test 03", "http://cs540304.vk.com/u104430199/videos/15c49f3711.720.mp4"});
        animeList.add(new String[] {"test 04", "http://cs540304.vk.com/u104430199/videos/15c49f3711.720.mp4"});
        animeList.add(new String[] {"test 05", "http://cs540304.vk.com/u104430199/videos/15c49f3711.720.mp4"});
        animeList.add(new String[] {"test 06", "http://cs540304.vk.com/u104430199/videos/15c49f3711.720.mp4"});


        SaveXML saveXML = new SaveXML(animeList);
        saveXML.createFileXML("D:\\default.xml");
    }
}
