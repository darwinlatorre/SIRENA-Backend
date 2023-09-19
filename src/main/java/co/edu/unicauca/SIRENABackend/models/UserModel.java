package co.edu.unicauca.SIRENABackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id", unique = true)
    private Integer ID;

    @Column(name = "usr_name", nullable = false, length = 20)
    private String name;

    @Column(name = "usr_firstname", nullable = false, length = 20)
    private String firstName;

    @Column(name = "usr_lastname", nullable = false, length = 20)
    private String lastName;

    @Column(name = "usr_email", nullable = false, length = 70, unique = true)
    private String email;

    @Column(name = "usr_password", nullable = false, length = 45)
    private String password;

    // @ManyToOne
    // @JoinColumn(name = "usr_role")
    // private RoleModel userRole;

    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    //     return List.of(new SimpleGrantedAuthority(userRole.getRoleName()));
    // }

    // @Override
    // public String getUsername() {
    //     return email;
    // }

    // @Override
    // public boolean isAccountNonExpired() {
    //     return true;
    // }

    // @Override
    // public boolean isAccountNonLocked() {
    //     return true;
    // }

    // @Override
    // public boolean isCredentialsNonExpired() {
    //     return true;
    // }

    // @Override
    // public boolean isEnabled() {
    //     return true;
    // }
}
