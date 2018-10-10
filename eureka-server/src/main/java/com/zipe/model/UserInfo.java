package com.zipe.model;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user_info")
public class UserInfo {
    private int id;
    private String userId;
    private String loginId;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String birthday;
    private String image;
    private String phone;
    private boolean activated;
    private String registerTime;
    private Set<Authority> authorities;
    private Set<PersonalTitle> personalTitle;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = false, length = 6, unique=true)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "login_id", updatable = false, nullable = false, length = 50)
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @Basic
    @Column(name = "password", updatable = false, nullable = false, length = 500)
    @NotNull
    @Size(max = 80)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", length = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email", updatable = false, nullable = false, length = 50)
    @Email
    @Size(max = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "address", length = 40)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "image", length = 20)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "phone", length = 10)
    @Pattern(regexp="(^$|[0-9]{10})")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "activated")
    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Basic
    @Column(name = "registerTime", length = 19)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authority",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_title",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "title_id")
    )
    public Set<PersonalTitle> getPersonalTitle() {
        return personalTitle;
    }

    public void setPersonalTitle(Set<PersonalTitle> personalTitle) {
        this.personalTitle = personalTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo that = (UserInfo) o;
        return activated == that.activated &&
                Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(loginId, that.loginId) &&
                Objects.equals(password, that.password) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(address, that.address) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(image, that.image) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(registerTime, that.registerTime) &&
                Objects.equals(authorities, that.authorities) &&
                Objects.equals(personalTitle, that.personalTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, loginId, password, firstName, lastName, email, address, birthday, image, phone, activated, registerTime, authorities, personalTitle);
    }
}
