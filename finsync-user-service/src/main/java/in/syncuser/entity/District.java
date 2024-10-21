package in.syncuser.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class District extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(nullable = false, unique = true)
	private String districtName;
	
	@Column(name="districtCode", unique = true)
	private String code;
	
	@ManyToOne
	private State state;

	public District(String districtName, String code, State state) {
		super();
		this.districtName = districtName;
		this.code = code;
		this.state = state;
	}
	
}
