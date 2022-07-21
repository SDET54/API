package pojos.GorestPojos;

public class GorestResponsePojo {

    private Object meta;
    private GorestDataPojo data;

    public GorestResponsePojo() {
    }

    public GorestResponsePojo(Object meta, GorestDataPojo data) {
        this.meta = meta;
        this.data = data;
    }

    public Object getMeta() {
        return meta;
    }

    public void setMeta(Object meta) {
        this.meta = meta;
    }

    public GorestDataPojo getData() {
        return data;
    }

    public void setData(GorestDataPojo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponsePojo{" +
                "meta=" + meta +
                ", data=" + data +
                '}';
    }
}
