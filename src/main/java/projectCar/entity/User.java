package projectCar.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "projectcar")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "login", nullable = false, length = 100)
    @Size(min = 4, message = "4 and more characters")
    private String login;

    @Column(name = "password", nullable = false, length = 100)
    @Size(min = 4, message = "4 and more characters")
    private String password;

    @Transient
    private String confirmPassword;

    @Column(name = "email", nullable = false, length = 100)
    @Email(message = "uncorrect enter email")
    private String email;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "second_name", nullable = false, length = 100)
    private String secondName;

    @Column(name = "birthday")
    private Date birthday;

    @OneToMany(fetch = FetchType.EAGER, cascade =  CascadeType.ALL, mappedBy = "user")
    private List<Car> cars;

    @ManyToOne
    @JoinColumn(name = "FK_currency")
    private Currency currency;

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<GrantedAuthority>();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(confirmPassword, user.confirmPassword) &&
                Objects.equals(email, user.email) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(secondName, user.secondName) &&
                Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, confirmPassword, email, firstName, secondName, birthday);
    }
}
