package test.spring.data.jdbc.datasource.oracle.repo;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import test.spring.data.jdbc.datasource.oracle.model.SpringTest;

public interface SpringTestRepo extends CrudRepository<SpringTest, Integer>
{

	/**
	 * Executing prepared SQL statement [INSERT INTO "MYSCHEMA"."SPRING_TEST" ("TEXT") VALUES (?)]
	 * @param id
	 * @param text
	 * @return
	 */
	
	@Modifying
	@Query("INSERT INTO \"MYSCHEMA\".\"SPRING_TEST\" (\"TEXT\") VALUES (:text)")
	boolean insert(String text);
	
}