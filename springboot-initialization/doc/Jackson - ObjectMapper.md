# Intro to the Jackson ObjectMapper

## Setup
Jackson is a JAVA  json library, need below dependecny:
* jackson-core
* jackson-annotation
* jackson-databind

```
implementation group: 'com.fasterxml.jackson.core', name: 'jackson-databind', version: '2.8.8'
implementation 'com.fasterxml.jackson.core:jackson-core:2.8.8'
 implementation 'com.fasterxml.jackson.core:jackson-annotation:2.8.8'
```

## Customize Serialization and Deserialization


## Reference 

[Jackson JSON Tutorial](https://www.baeldung.com/jackson)

[Jackson github](https://github.com/FasterXML/jackson)

## Jackson Annotation
![https://www.tutorialspoint.com/jackson_annotations/index.htm](https://www.tutorialspoint.com/jackson_annotations/index.htm)
@JsonValue allows to serialize an entire object using its single method.
```
public enum Gender {
    MALE("1"), FEMALE("0");

    private String sex;
    Gender(String sex){
        this.sex = sex;
    }
    public String sex(){
        return this.sex;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    private String name;
    private int rollNo;
    private Gender gender;
    private String schoolName;
}

public class AnnotationTest {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Student student = Student.builder().
                gender(Gender.FEMALE).
                name("Larry").
                rollNo(1).schoolName("Middle School").build();
        String studentJson = mapper.writeValueAsString(student);
        System.out.println(studentJson);
    }
}

```
if we do not make change to enum, the output will be like: {"name":"Larry","rollNo":1,"gender":"FEMALE","schoolName":"Middle School"}

we can use @JsonValue to customize what value we want it return

```
public enum Gender {
    MALE("1"), FEMALE("0");

    private String sex;
    Gender(String sex){
        this.sex = sex;
    }
    @JsonValue
    public String sex(){
        return this.sex;
    }
}
```
the output will be like: {"name":"Larry","rollNo":1,"gender":"0","schoolName":"Middle School"}

@JsonIgnore is used at field level to mark a property or list of properties to be ignored.

@JsonCreator is used to fine tune the constructor or factory method used in deserialization.
Note: @JsonCreator 中能用在static 方法 或者 Constructor 上
```

public enum Gender {
    MALE("1"), FEMALE("0");

    private String sex;
    Gender(String sex){
        this.sex = sex;
    }
    public static Gender getBySex(String sex){
        Gender[] values = values();
        for(Gender gender: values){
            if(gender.sex().equals(sex)){
                return gender;
            }
        }
        return null;
    }
    @JsonCreator
    public static Gender fromObject(String sex){
        return Gender.getBySex(sex);
    }
    @JsonValue
    public String sex(){
        return this.sex;
    }
}
```