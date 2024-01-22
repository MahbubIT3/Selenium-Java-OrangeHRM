package config;

public class EmployeeDataModel {
    String employeeId;
    String firstname;
    String middlename;
    String lastname;
    String username;
    String password;
    String jobTitle;

    public String getEmployeeId(){
        return employeeId;
    }
    public void setEmployeeId(String employeeId){
        this.employeeId = employeeId;
    }
    public String getFirstname(){
        return firstname;
    }
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }
    public String getMiddlename(){
        return middlename;
    }
    public void setMiddlename(String middlename){
        this.middlename = middlename;
    }
    public String getLastname(){
        return  lastname;
    }
    public void setLastname(String lastname){
        this.lastname = lastname;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getJobTitle(){
        return jobTitle;
    }
    public void setJobTitle(String jobTitle){
        this.jobTitle = jobTitle;
    }

    public EmployeeDataModel(String employeeId, String firstname, String middlename, String lastname, String username, String password, String jobTitle){
        this.employeeId = employeeId;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.jobTitle = jobTitle;
    }
}

