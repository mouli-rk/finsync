package in.syncuser.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import in.syncuser.constants.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreType
public class RoleType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(unique = true)
	private Role role;
	
	@OneToMany(mappedBy = "roleType")
	private List<GrantedAuthority> roles;
	
	@OneToMany(mappedBy = "roleType", fetch = FetchType.LAZY)
	private List<ModulePrivilege> modulePrivilege;

	public RoleType(Role role) {
		super();
		this.role = role;
	}

	public RoleType(Integer id) {
		super();
		this.id = id;
	}
	
	
}
