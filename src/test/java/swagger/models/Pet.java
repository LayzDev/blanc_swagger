package swagger.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pet{

	@JsonProperty("photoUrls")
	private List<String> photoUrls;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("category")
	private Category category;

	@JsonProperty("tags")
	private List<Tags> tags;

	@JsonProperty("status")
	private String status;

}