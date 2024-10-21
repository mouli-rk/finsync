package in.syncaccount.entity;

import java.time.LocalDateTime;
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
	private LocalDateTime lastModified;

}
