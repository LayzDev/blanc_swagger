package swagger.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("lastName")
	private String lastName;

	@JsonProperty("password")
	private String password;

	@JsonProperty("userStatus")
	private Integer userStatus;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("email")
	private String email;

	@JsonProperty("username")
	private String username;
}