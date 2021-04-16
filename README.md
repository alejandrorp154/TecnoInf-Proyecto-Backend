# backend
-----------

## Consultar endpoint con swgger:
-----------

### Listar todos los endpoints:
-----------

`http://localhost:8080/pryectoBack-web/rest/api-docs`

### Consultar un endpoint en particular:
-----------

`http://localhost:8080/pryectoBack-web/rest/api-docs/usuario`

-----------
## Agregado en wildfly:
-----------
### Standalone.xml
-----------
```
<datasource jndi-name="java:jboss/datasources/PostgresDS" pool-name="PostgresDS" enabled="true" use-java-context="true">
	<connection-url>jdbc:postgresql://localhost:5433/proyecto</connection-url>
	<driver>postgresql</driver>
	<security>
		<user-name>postgres</user-name>
		<password>root</password>
	</security>
</datasource>
<driver name="postgresql" module="org.postgresql">
	<driver-class>org.postgresql.Driver</driver-class>
	<xa-datasource-class>org.postgresql.xa.PGXADataSource</xa-datasource-class>
</driver>
```
en la seccion: `datasources`

### En la carpeta modules\system\layers\base\org cree la carpeta postgresql/main y el archivo module.xml con el siguiente contenido
-----------
```
<?xml version="1.0" encoding="UTF-8"?>
<module xmlns="urn:jboss:module:1.1" name="org.postgresql">
    <resources>
        <resource-root path="postgresql-42.2.19.jar" />
    </resources>
    <dependencies>
        <module name="javax.api"/>
        <module name="javax.transaction.api"/>
        <module name="javax.servlet.api" optional="true"/>
    </dependencies>
</module>
```
- Tambien agregue el jar `postgresql-42.2.19.jar` en la carpeta nueva `postgresql/main`
