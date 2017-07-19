package entiry;

/**
 * Created by EVA-08 on 2017/7/6.
 */
public class UserInfo {
    private String username;

    public UserInfo(String username, String password, String authority,
                    String name, String gender, String tel, Integer age,
                    Integer marketID) {
        this.username = username;
        this.password = password;
        this.authority = authority;
        this.name = name;
        this.gender = gender;
        this.tel = tel;
        this.age = age;
        this.marketID = marketID;
    }
    public UserInfo() {}

    public String getUsername() {
        return username;

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getMarketID() {
        return marketID;
    }

    public void setMarketID(Integer marketID) {
        this.marketID = marketID;
    }

    private String password;
    private String authority;
    private String name;
    private String gender;
    private String tel;
    private Integer age;
    private Integer marketID;
}
