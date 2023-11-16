package co.edu.unicauca.SIRENABackend.security.models;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.hibernate.type.NumericBooleanConverter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import co.edu.unicauca.SIRENABackend.models.ClassroomModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un modelo de usuario en la aplicación.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserModel implements UserDetails {

    /**
     * Identificador único del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_int_id", unique = true)
    private Integer id;

    /**
     * Nombre de usuario del usuario.
     */
    @Column(name = "usr_name", nullable = false, length = 20, unique = true)
    private String username;

    /**
     * Primer nombre del usuario.
     */
    @Column(name = "usr_firstname", nullable = false, length = 20)
    private String firstName;

    /**
     * Apellido del usuario.
     */
    @Column(name = "usr_lastname", nullable = false, length = 20)
    private String lastName;

    /**
     * Correo electrónico del usuario.
     */
    @Column(name = "usr_email", nullable = false, length = 50, unique = true)
    @Email
    private String email;

    /**
     * Contraseña del usuario.
     */
    @Column(name = "usr_password", nullable = false, length = 512)
    private String password;

    /**
     * Estado del usuario (activo o inactivo).
     */
    @Default
    @Convert(converter = NumericBooleanConverter.class)
    @Column(name = "usr_status", nullable = false)
    protected Boolean status = true;

    /**
     * Rol del usuario representado por la clase `RoleModel`.
     */
    @ManyToOne
    @JoinColumn(name = "usr_role", nullable = false)
    private RoleModel role;

    /**
     * Conjunto de salones asignados al usuario.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "users_classrooms", joinColumns = @JoinColumn(name = "usr_int_id"), inverseJoinColumns = @JoinColumn(name = "cls_int_id"))
    private Set<ClassroomModel> classroom_assigned;

    /**
     * Lista de tokens asociados al usuario.
     */
    @OneToMany(mappedBy = "user")
    @Column(name = "usr_tokens")
    private List<TokenModel> tokens;

    /**
     * Establece el estado del usuario.
     *
     * @param prmStatus Estado del usuario.
     */

    public void setStatus(Boolean prmStatus) {
        this.status = prmStatus;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.getName().name()));
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

}
