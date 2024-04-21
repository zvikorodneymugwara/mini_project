package app.network;

import java.io.Serializable;

public class AdminResponse implements Serializable {
    private boolean responseStatus;
    private String response;
    private String docId;

    public AdminResponse() {

    }

    public AdminResponse(boolean status, String response) {
        this.responseStatus = status;
        this.response = response;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getResponse() {
        return response;
    }

    public boolean getResponseStatus() {
        return responseStatus;
    }
}
