package pojos.JsonplaceholderPojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class JsonplaceholderPojo {
    /*
    1) Map'te kullandigimiz tum key'ler icin private variables olusturuluyor
    2) Tum parametrelerle ve parametresiz constructor'larimizi olusturuyoruz
    3) Getters and Setters'larimizi olusturuyoruz
    4) Create toString() method
     */

    // 1) Map'te kullandigimiz tum key'ler icin private variables olusturuluyor

    private Integer userId;
    private String title;
    private Boolean completed;

    // 2) Tum parametrelerle ve parametresiz constructor'larimizi olusturuyoruz

    public JsonplaceholderPojo() {
    }

    public JsonplaceholderPojo(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    // 3) Getters and Setters'larimizi olusturuyoruz

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    // 4) Create toString() method

    @Override
    public String toString() {
        return  "userId=" + userId +
                ", title=" + title +
                ", completed=" + completed;
    }

    //farkli key-value ikililerin uyusmazligini @JsonIgnoreProperties(ignoreUnknown = true)
    //annotation'ini Pojo Class'imizin basina yazarak cozebiliriz.
}
