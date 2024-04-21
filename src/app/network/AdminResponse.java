package app.network;

public class AdminResponse {
    private boolean responseStatus;
    private String response;

    public AdminResponse(boolean status, String response) {
        this.responseStatus = status;
        this.response = response;
    }

    public AdminResponse() {

    }

    public String getResponse() {
        return response;
    }

    public boolean getResponseStatus() {
        return responseStatus;
    }
}
