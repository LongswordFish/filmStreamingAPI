package ca.wonderfish.filmstreamingapi.exceptions;

public class OrderIdExceptionResponse {
    private String orderId;

    public OrderIdExceptionResponse(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
