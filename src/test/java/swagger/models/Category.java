package swagger.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category{

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private Long id;

}