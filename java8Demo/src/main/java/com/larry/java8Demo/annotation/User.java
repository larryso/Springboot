package com.larry.java8Demo.annotation;

@Entity(value = "user", isLazyLoad = false)
public class User {
    @EntityProperty(value="100")
    private String id;
    @EntityProperty(value="Larry Song")
    private String name;
    @EntityProperty(value="Male")
    private String sex;
    @EntityProperty(value="95.32")
    private String wage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getWage() {
        return wage;
    }

    public void setWage(String wage) {
        this.wage = wage;
    }
}
