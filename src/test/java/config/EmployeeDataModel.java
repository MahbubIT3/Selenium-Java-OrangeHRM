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
    public void setEmployeeId(){
        this.employeeId = employeeId;
    }
    public String getFirstname(){
        return firstname;
    }
    public void setFirstname(){
        this.firstname = firstname;
    }
    public String getMiddlename(){
        return middlename;
    }
    public void setMiddlename(){
        this.middlename = middlename;
    }
    public String getLastname(){
        return  lastname;
    }
    public void setLastname(){
        this.lastname = lastname;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(){
        this.username = username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(){
        this.password = password;
    }
    public String getJobTitle(){
        return jobTitle;
    }
    public void setJobTitle(){
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

