package ua.nure.kopaniev.bean;

/**
 * Created by Vladyslav_Kopaniev on 11/10/2015.
 */
public class User {
    private long id;
    private String name;
    private String surname;
    private String email;
    private String pass;
    private Role role;
    private boolean toysInfo;
    private boolean discountsInfo;

    public User(String name, String surname, String email, String pass, boolean toysInfo, boolean discountsInfo) {
        this(0, name, surname, email, pass, Role.USER, toysInfo, discountsInfo);
    }

    public User(String name, String surname, String email, String pass, Role role, boolean toysInfo, boolean discountsInfo) {
        this(0, name, surname, email, pass, role, toysInfo, discountsInfo);
    }

    public User(long id, String name, String surname, String email, String pass, Role role, boolean toysInfo, boolean discountsInfo) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.pass = pass;
        this.toysInfo = toysInfo;
        this.discountsInfo = discountsInfo;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }

    public boolean isToysInfo() {
        return toysInfo;
    }

    public boolean isDiscountsInfo() {
        return discountsInfo;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", pass='" + pass + '\'' +
                ", toysInfo=" + toysInfo +
                ", discountsInfo=" + discountsInfo +
                '}';
    }
}
