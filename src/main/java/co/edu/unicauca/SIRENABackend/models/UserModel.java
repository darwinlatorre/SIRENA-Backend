package co.edu.unicauca.SIRENABackend.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class UserModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true)
    private Integer userID;

    @Column(name = "user_name", nullable = false, length = 20)
    private String userName;

    @Column(name = "user_firstname", nullable = false, length = 20)
    private String userFirstName;

    @Column(name = "user_lastname", nullable = false, length = 20)
    private String userLastName;

    @Column(name = "user_email", nullable = false, length = 70, unique = true)
    private String userEmail;

    @Column(name = "user_password", nullable = false, length = 45)
    private String userPassword;

    @Column(name = "user_role")
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private RoleModel userRole;

    // @ManyToMany
    // @JoinTable(
    //     name = "users_roles",
    //     joinColumns = @JoinColumn(name = "user_id"),
    //     inverseJoinColumns = @JoinColumn(name = "role_id")
    // )
    // private Set<RoleModel> userRoles = new HashSet<>();

    // public void addRole(RoleModel prmRole){
    //     this.userRoles.add(prmRole);
    // }
}
