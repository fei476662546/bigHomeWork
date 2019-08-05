package mybean.data.javabean;

/**
 * Create by mysteriousTime
 * time on 2019/7/20  18:27
 */
public class UploadFile {
   private String fileName;
   private String savedFileName;
   private String backNews="";

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSavedFileName() {
        return savedFileName;
    }

    public void setSavedFileName(String savedFileName) {
        this.savedFileName = savedFileName;
    }

    public String getBackNews() {
        return backNews;
    }

    public void setBackNews(String backNews) {
        this.backNews = backNews;
    }
}
