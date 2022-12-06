package com.groupi.groupi2;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int locationId;
    //private String userType;
    //private int phoneNumber;
    //private String dateOfBirth;
    //private boolean userStatus;


    public User()
    {
        id = 0;
        firstName = "DEFAULT";
        lastName = "DEFAULT";
        email = "DEFAULT";
        password = "DEFAULT";
        //phoneNumber = "DEFAULT";
        //dateOfBirth = "DEFAULT";
        // userStatus = "DEFAULT";

    }

    public User(int id, String firstName, String lastName, String email, String password)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User(int id, String firstName, String lastName, String email, String password, int locationId)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.locationId = locationId;
    }

    public int getId()
    {
        return id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword() {
        return password;
    }
    /*
        public int getPhoneNumber()
        {
            return phoneNumber;
        }

        public String getDateOfBirth()
        {
            return dateOfBirth;
        }

        public boolean getUserStatus()
        {
            return userStatus;
        }
    */
    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLocationId() {
        return this.locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

/*
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDateofBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

 */
}

