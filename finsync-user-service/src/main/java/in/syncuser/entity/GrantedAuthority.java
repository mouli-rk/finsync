package in.syncuser.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreType
@Entity
public class GrantedAuthority {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	private RoleType roleType;

	public GrantedAuthority(User user, RoleType roleType) {
		super();
		this.user = user;
		this.roleType = roleType;
	}

	public GrantedAuthority(RoleType roleType) {
		super();
		this.roleType = roleType;
	}
	
}
