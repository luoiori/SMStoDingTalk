package februarybreeze.github.io.smstodingtalk;

import java.io.Serializable;

public class Email implements Serializable {

    private String title;

    private String body;

    private String time;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
