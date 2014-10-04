package gui;

import configuration.PropertiesFile;
import saveData.SaveXML;
import vk.search.SearchVideo;

import java.util.List;

public class LogicProgram {

    PropertiesFile configure;
    SearchVideo searchVideo;
    List<String[]> animeList;
    SaveXML saveXML;
    MainFrame mainFrame;

    public LogicProgram() {

        configure = new PropertiesFile();
        startLogic();
    }

    private void startLogic() {

        String accessToken = configure.getAccessToken();
        if (accessToken == "") {

        }
        else {
            searchVideo = new SearchVideo(accessToken);
            mainFrame = new  MainFrame(this);
        }
    }

    public void eventButtonClick (String request) throws Exception {
        if (!request.equals("")) {
            searchVideo.makeAnimeList(request);
            animeList = searchVideo.getAnimeList();

            mainFrame.printAnimeList(animeList);

            if (animeList.size() != 0) {
                saveXML = new SaveXML(animeList);
                saveXML.createFileXML(configure.getPatchToXML());
            }
        }
    }
}
