package in.syncuser.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonModel extends LoginModel{

	private Long id;
	private String code;
	private String firstName;
	private String lastName;
	private String gender;
	private String fullName;
	private String alternativePhnNo;
	private String prefix;
	private String suffix;
	private Long accountId;
	private String accountNumber;
	private BigDecimal accountBalance;
	private String accountType;
	private Boolean status;
	private Long addressId;
	private Long userId;
	private String drNo;
	private String street;
	private String Landmark;
	private Long cityId;
	private String cityName;
	private Long districtId;
	private String districtName;
	private Long stateId;
	private String stateName;
	private Long countryId;
	private String countryName;
	private Integer pincode;
	private Long lastModifiedBy;
	private LocalDateTime lastModified;
}
