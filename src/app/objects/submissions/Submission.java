package app.objects.submissions;

import acsse.csc03a3.Block;
import app.objects.Company;

public class Submission {
    Block<SubmissionDocument> docSubmissions;
    Block<Company> verifiedCompanies;

    public Submission() {
        //load saved companies and submissions
        loadSavedCompanies();
        loadSavedSubmissions();
    }

    public boolean isCompanyVerified(Company company) {
        return false;
    }

    public boolean addSubmissionDocument(SubmissionDocument document) {
        return false;
    }

    // load objects from txt file for testing
    private void loadSavedCompanies() {

    }

    // load objects from txt file for testing
    private void loadSavedSubmissions() {

    }

}
