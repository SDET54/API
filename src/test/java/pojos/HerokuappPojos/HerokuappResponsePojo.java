package pojos.HerokuappPojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HerokuappResponsePojo {

    private Integer bookingid;
    private HerokuappBookingPojo booking;

    public HerokuappResponsePojo(Integer bookingid, HerokuappBookingPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public HerokuappResponsePojo() {
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }


    public HerokuappBookingPojo getBooking() {
        return booking;
    }

    public void setBooking(HerokuappBookingPojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "ResponsePojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
