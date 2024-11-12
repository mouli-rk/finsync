package in.syncuser.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreType
public class ModulePrivilege {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private RoleType roleType;
	
	@ManyToOne
	private SystemModule systemModule;

	public ModulePrivilege(RoleType roleType, SystemModule systemModule) {
		super();
		this.roleType = roleType;
		this.systemModule = systemModule;
	}
	
}
