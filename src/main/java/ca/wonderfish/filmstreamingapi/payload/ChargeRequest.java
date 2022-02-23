package ca.wonderfish.filmstreamingapi.payload;

public class ChargeRequest {
    private String token;
    private Long amount;

    public ChargeRequest(String token, Long amount) {
        this.token = token;
        this.amount = amount;
    }

    public ChargeRequest() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ChargeRequest{" +
                "token='" + token + '\'' +
                ", amount=" + amount +
                '}';
    }
}
