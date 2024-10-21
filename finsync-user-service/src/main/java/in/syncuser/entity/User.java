package in.syncuser.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
	private String userName;
	
	private String password;
	
	@ManyToOne
	private Address address;

	public User(String firstName, String lastName, String gender, String fullName, String email, String phoneNo,
			String alternativePhnNo, String userName, String password,  Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.fullName = fullName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.alternativePhnNo = alternativePhnNo;
		this.address = address;
		this.userName = userName;
		this.password = password;
	}
	
	@PrePersist
	private void generateFullName() {
		this.fullName = this.firstName +" "+this.lastName;
	}
}
