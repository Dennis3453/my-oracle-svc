## A sample project trying to write Oracle schema "MYSCHEMA" with user "MYUSER"

### My Setup
OpenJDK Runtime Environment (AdoptOpenJDK)(build 1.8.0_292-b10)  
Oracle database 19c

### Oracle User
MYSCHEMA  
MYUSER

### Table Schema
src\main\resources\sql\schema-oracle-myschema.sql

### Controllers (All Post)
    http://localhost:8080/api/test  
Run as MYUSER & use save() method, fail & returns java.sql.SQLSyntaxErrorException: ORA-04043: object "MYSCHEMA" does not exist.  
Switch user to MYSCHEMA and remove NamingStrategy can run successfully.  

______
    http://localhost:8080/api/test2  
Use @Query, run successfully.