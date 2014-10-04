package saveData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveXML {

    List<String[]> animeList = new ArrayList<String[]>();

    public SaveXML(List<String[]> animeList) {
        this.animeList = animeList;
    }


    public void createFileXML(String path) throws Exception {

        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(path));
            out.write(makeBodyXML());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    private String makeBodyXML () throws Exception {

        int ID = 0;

        StringBuilder bodyXML = new StringBuilder();
        bodyXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        bodyXML.append("<DownloadList  Version=\"6\"\n" +
                "   NextID=\"1000\">\n");

        for (String[] anime : animeList) {
            bodyXML.append(addAnimeInXML(anime[0], anime[1], ID));
            ID++;
        }

        bodyXML.append("</DownloadList>");

        return bodyXML.toString();
    }

    private String addAnimeInXML (String animeName, String animeLink, int ID) throws Exception {

        StringBuilder animeProperties = new StringBuilder();
        animeProperties.append(" <DownloadFile>\n");
        animeProperties.append("    <ID>" + ID + "</ID>\n");
        animeProperties.append("    <URL>" + animeLink + "</URL>\n");
        animeProperties.append("    <FileName>D:\\Dowload master\\Видео\\" + animeName + ".mp4</FileName>\n");
        animeProperties.append("    <State>3</State>\n");
        animeProperties.append("    <Size>0</Size>\n");
        animeProperties.append("    <SaveDir>D:\\Dowload master\\Видео\\</SaveDir>\n");
        animeProperties.append("    <SaveAs>" + animeName + ".mp4</SaveAs>\n");
        animeProperties.append("    <Date>09/24/2014 16:51:12</Date>\n");
        animeProperties.append("    <DownloadTime>0</DownloadTime>\n");
        animeProperties.append("    <NodeID>24</NodeID>\n");
        animeProperties.append(" </DownloadFile>\n");

        ID++;
        return animeProperties.toString();
    }
}
