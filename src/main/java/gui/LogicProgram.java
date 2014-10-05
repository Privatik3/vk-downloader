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

        //String accessToken = configure.getAccessToken();
        String accessToken = "7268b42c52683105eda2d7e08197f522dc971bfa9dad642b967c0fc4eff89d722a633fbc20f95b0244d3d";
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
                //saveXML.createFileXML(configure.getPatchToXML());
                saveXML.createFileXML("D:\\\\default.xml");
            }
        }
    }
}
