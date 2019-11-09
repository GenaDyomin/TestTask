package test.task.task.entity;
public class Appear {

    private Long id;
    private String date;
    private String type;
    private String text;
    private String userName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Override
    public String toString(){
        return  "Appears [id = "+ id+", date = " + date + ", type = " + type + ", text = " + text + ", userName = " + userName + "]";
    }
}
