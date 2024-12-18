package in.syncuser.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreType
public class SystemModule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private String module;
	
	@OneToMany(mappedBy = "systemModule", fetch = FetchType.LAZY)
	private List<ModulePrivilege> modulePrivilege;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private SystemModule parent;
	
	public SystemModule(Integer id) {
		super();
		this.id = id;
	}
	
	public SystemModule(String module) {
		super();
		this.module = module;
	}

	public SystemModule(String module, SystemModule parent) {
		super();
		this.module = module;
		this.parent = parent;
	}
	
	
	
}
