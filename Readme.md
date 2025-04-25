## Crud para gestionar transacciones bancarias entre cuentas.
- java: 17

## NOTA DE TEST
### mock
- Cuando estés escribiendo pruebas unitarias y no necesites un contexto de Spring completo
### @MockBean 
- Es una anotación específica de Spring Boot y se usa principalmente en pruebas de integración
- Se utiliza en pruebas de integración con Spring Boot, donde necesitas realizar pruebas que involucren el contexto 
  completo de Spring (como controladores, servicios, repositorios, etc.).es útil para sustituir un bean de Spring por 
  uno simulado durante las pruebas, evitando la necesidad de interactuar con bases de datos reales u otros servicios externos
- @MockBean permite que el contexto de Spring gestione la inyección de dependencias

### @Bean
-  Inyectar beans manualmente en el mini-contexto web creado por @WebMvcTest
- Si no agregas los @Bean: Spring no sabrá cómo inyectar las dependencias y el test fallará.
### @WebMvcTest
- Spring Boot crea un mini-contexto limitado al stack web
### @SpringBootTest
- Levanta todo el conexto, donde sería necesario acceder a base datos, servicios reales, etc

