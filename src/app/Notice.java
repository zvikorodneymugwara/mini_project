package app;

import java.util.Date;

public class Notice {
    private String noticeOutcome;
    private String noticeDescription;
    private String noticeTitle;
    private Date noticeDate;

    public Notice(String noticeOutcome, String noticeDescription, String noticeTitle, Date noticeDate) {
        this.noticeDate = noticeDate;
        this.noticeDescription = noticeDescription;
        this.noticeOutcome = noticeOutcome;
        this.noticeTitle = noticeTitle;
    }

    public Date getNoticeDate() {
        return noticeDate;
    }
    public String getNoticeDescription() {
        return noticeDescription;
    }
    public String getNoticeOutcome() {
        return noticeOutcome;
    }
    public String getNoticeTitle() {
        return noticeTitle;
    }
    public void setNoticeDate(Date noticeDate) {
        this.noticeDate = noticeDate;
    }
    public void setNoticeDescription(String noticeDescription) {
        this.noticeDescription = noticeDescription;
    }
    public void setNoticeOutcome(String noticeOutcome) {
        this.noticeOutcome = noticeOutcome;
    }
    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }
}

