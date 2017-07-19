package entiry;

/**
 * Created by EVA-08 on 2017/7/13.
 */
public class Market {
    private String name;
    private Integer marketID;
    private String tel;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMarketID() {
        return marketID;
    }

    public void setMarketID(Integer marketID) {
        this.marketID = marketID;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
