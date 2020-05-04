import java.util.StringTokenizer;

public class Product {
    private int userId;
    private int city;
    private String time;
    private String search;

    public Product(){

    }

    public Product(int userId,int city,String timestamp,String search){
        this.userId = userId;
        this.city = city;
        this.time = timestamp;
        this.search = search;

    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }



    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
