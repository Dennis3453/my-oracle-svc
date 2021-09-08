package test.spring.data.jdbc.datasource.oracle.model;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SpringTest {

	@Id
	private int id;
	private String text;
	
}
