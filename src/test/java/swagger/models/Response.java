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
public class Response {

	@JsonProperty("code")
	private Integer code;

	@JsonProperty("type")
	private String type;

	@JsonProperty("message")
	private String message;
}