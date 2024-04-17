package swagger.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order{

	@JsonProperty("petId")
	private Long petId;

	@JsonProperty("quantity")
	private Integer quantity;

	@JsonProperty("id")
	private Long id;

	@JsonProperty("shipDate")
	private String shipDate;

	@JsonProperty("complete")
	private Boolean complete;

	@JsonProperty("status")
	private String status;
}