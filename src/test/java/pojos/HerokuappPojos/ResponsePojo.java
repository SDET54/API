package pojos.HerokuappPojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsePojo {

    private Integer bookingid;
    private BookingPojo booking;

    public ResponsePojo(Integer bookingid, BookingPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public ResponsePojo() {
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }


    public BookingPojo getBooking() {
        return booking;
    }

    public void setBooking(BookingPojo booking) {
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
