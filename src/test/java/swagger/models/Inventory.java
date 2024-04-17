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
public class Inventory{

	@JsonProperty("sold")
	private Integer sold;

	@JsonProperty("approved")
	private Integer approved;

	@JsonProperty("placed")
	private Integer placed;

	@JsonProperty("booked")
	private Integer booked;

	@JsonProperty("string")
	private Integer string;

	@JsonProperty("in stock")
	private Integer inStock;

	@JsonProperty("pending")
	private Integer pending;

	@JsonProperty("available")
	private Integer available;

	@JsonProperty("delivered")
	private Integer delivered;

	@JsonProperty("peric")
	private Integer peric;
}