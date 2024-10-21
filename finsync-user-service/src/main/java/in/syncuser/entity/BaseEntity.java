package in.syncuser.entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseEntity {
	
	private String code;
	private Long lastModifiedBy;
	
	@UpdateTimestamp
	private LocalDateTime lastModified;

}
