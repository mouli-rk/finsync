package in.syncaccount.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Account{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String code;
	
	@Column(nullable = false)
	private String accountNumber;
	
	@Column(nullable = false)
	private BigDecimal accountBalance;
	
	@Column(nullable = false)
	private String accountType;
	
	@Column(nullable = false)
	private Boolean status;
	
	@Column(name="lastModifiedBy", nullable = false)
	private Long lastModifiedBy;
	
	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime lastModified;	
	
	@Column(name="userId", nullable = false)
	private Long userId;
		
}
