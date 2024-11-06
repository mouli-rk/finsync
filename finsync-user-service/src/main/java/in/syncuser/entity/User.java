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
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name="users")
@NoArgsConstructor
@JsonIgnoreType
public class User extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String gender;
	private String fullName;
	private String email;
	private String phoneNo;
	private String alternativePhnNo;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	private String password;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Address address;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<GrantedAuthority> roles;
	
	

	public User(String firstName, String lastName, String gender, String fullName, String email, String phoneNo,
			String alternativePhnNo, String username, String password,  Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.fullName = fullName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.alternativePhnNo = alternativePhnNo;
		this.address = address;
		this.username = username;
		this.password = password;
	}
	
	public User(String username) {
		super();
		this.username = username;
	}
	
	@PrePersist
	private void generateFullName() {
		this.fullName = this.firstName +" "+this.lastName;
	}

	public User(Long id) {
		super();
		this.id = id;
	}

}
