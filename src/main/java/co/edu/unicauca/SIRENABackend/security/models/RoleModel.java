package co.edu.unicauca.SIRENABackend.security.models;

import java.util.List;
import java.util.Set;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import co.edu.unicauca.SIRENABackend.security.common.enums.RoleEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un modelo de rol en la aplicación.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class RoleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_int_id", unique = true, nullable = false)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol_name", unique = true, nullable = false)
    private RoleEnum name;

    @Enumerated(EnumType.STRING)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "roles_permissions", joinColumns = @JoinColumn(name = "rol_int_id"), inverseJoinColumns = @JoinColumn(name = "per_int_id"))
    private Set<PermisionModel> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {

        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName().name()))
                .toList();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name));

        return authorities;
    }
}
