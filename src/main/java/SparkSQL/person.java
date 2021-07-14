package SparkSQL;

public class person {
    private String name;
    private String city;
    private String dateOfBirth;
    private Long age;

    public person(){

    }
    public person(String name, String city, String dateOfBirth, Long age) {
        this.name = name;
        this.city = city;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}

