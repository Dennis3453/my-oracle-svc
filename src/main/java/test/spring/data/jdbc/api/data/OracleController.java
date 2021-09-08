package test.spring.data.jdbc.api.data;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import test.spring.data.jdbc.datasource.oracle.model.SpringTest;
import test.spring.data.jdbc.datasource.oracle.repo.SpringTestRepo;

@RestController
@RequiredArgsConstructor
public class OracleController {
	
	private final SpringTestRepo springTestRepo;
	
	/**
	 * Fail, returns java.sql.SQLSyntaxErrorException: ORA-04043: object "MYSCHEMA" does not exist
	 * @return
	 */
	
	@PostMapping("/test")
	public ResponseEntity<Integer> test(){
		
		springTestRepo.save(SpringTest.builder().text("test").build());
		
		return ResponseEntity.ok(1);
	}			
	
	/**
	 * Success
	 * @return
	 */
	@PostMapping("/test2")
	public ResponseEntity<Integer> test2(){
		
		springTestRepo.insert("test2");
		
		return ResponseEntity.ok(1);
	}		
	
}