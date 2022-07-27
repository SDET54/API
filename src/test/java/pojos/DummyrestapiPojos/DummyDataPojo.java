package pojos.DummyrestapiPojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DummyDataPojo {
    private String profile_image;
    private String employee_name;
    private int employee_salary;
    private int employee_age;

    public DummyDataPojo() {
    }

    public DummyDataPojo(String profile_image, String employee_name, int employee_salary, int employee_age) {
        this.profile_image = profile_image;
        this.employee_name = employee_name;
        this.employee_salary = employee_salary;
        this.employee_age = employee_age;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public int getEmployee_salary() {
        return employee_salary;
    }

    public int getEmployee_age() {
        return employee_age;
    }

    @Override
    public String toString() {
        return
                "{" +
                        "profile_image = '" + profile_image + '\'' +
                        ",employee_name = '" + employee_name + '\'' +
                        ",employee_salary = '" + employee_salary + '\'' +
                        ",employee_age = '" + employee_age + '\'' +
                        "}";
    }
}
