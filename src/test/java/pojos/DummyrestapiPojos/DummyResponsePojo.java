package pojos.DummyrestapiPojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyResponsePojo {
    private DummyDataPojo data;
    private String message;
    private String status;

    public DummyResponsePojo() {
    }

    public DummyResponsePojo(DummyDataPojo data, String message, String status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public DummyDataPojo getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return
                "{" +
                        "data = '" + data + '\'' +
                        ",message = '" + message + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}
