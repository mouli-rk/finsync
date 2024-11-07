package in.syncuser.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@JsonIgnoreType
public class Address extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String drNo;
	private String street;
	private String Landmark;
	private Integer pincode;

	@Transient
	private String code;
	
	@ManyToOne
	private City city;

	public Address(String drNo, String street, String landmark, Integer pincode, City city) {
		super();
		this.drNo = drNo;
		this.street = street;
		Landmark = landmark;
		this.pincode = pincode;
		this.city = city;
	}
	
	
	

}
