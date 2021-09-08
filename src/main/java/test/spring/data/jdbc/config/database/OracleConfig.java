package test.spring.data.jdbc.config.database;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@EnableJdbcRepositories(basePackages = "test.spring.data.jdbc.datasource.oracle.repo", jdbcOperationsRef="OraJdbcOperations")
public class OracleConfig {
	
	@Primary
	@Bean(name="OraDataSource")
	@ConfigurationProperties("datasource.oracle")
	public DataSource primaryDataSource() {
		return DataSourceBuilder.create().build();
	}	
    
	@Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager primaryTransactionManager(@Qualifier("OraDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    	
    @Bean(name = "OraJdbcOperations")
    @Primary
    public NamedParameterJdbcOperations jdbcOperations(@Qualifier("OraDataSource") DataSource dataSource) {
      return new NamedParameterJdbcTemplate(dataSource);
    }    
    
    @Bean(name = "oracle")
    public JdbcTemplate jdbcTemplate(@Qualifier("OraDataSource") DataSource ds){
      return new JdbcTemplate(ds);
    }
    
    @Bean(name = "OraDasDbInitializer")
    public DataSourceInitializer dataSourceInitializer1(@Qualifier("OraDataSource") DataSource datasource) throws IOException{
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(this.getClass().getClassLoader());
//        resourceDatabasePopulator.addScripts(resolver.getResources("classpath:sql/schema-*.sql"));
//        resourceDatabasePopulator.addScripts(resolver.getResources("classpath:sql/data-*.sql"));
        resourceDatabasePopulator.setContinueOnError(true);
        
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(datasource);
        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        return dataSourceInitializer;
    }
    

    @Bean
    NamingStrategy namingStrategy() {
        return new NamingStrategy() {
            @Override
            public String getSchema() {
                return "MYSCHEMA";
            }
        };
    }    
        
}