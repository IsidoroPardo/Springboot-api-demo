SPRINGBOOT API DEMO

Mi primer programa usando Spring Boot para conectar con la API de Spotify y obtener datos de discos y artistas.

Características:
- Autenticación con Spotify usando PKCE
- API REST con Spring Boot
- Manejo de Tokens y refresh tokens
- Endpoints documentados

Tecnologías usadas:
- Java 17
- Spring Boot
- Spring Security
- Soptify Web API
- Maven
- Postman

Cómo usar la aplicación desde el terminal (necesaria versión de Java 17 o superior):
Es necesario añadir un id de usuario de spotify en el archivo src/main/java/com.example.apiSpotify/servicio/SpotifyServiceImp en la línea 28.

- Arrancar con: mvn spring-boot
- Disponible en: http://localhost:8080
- Ejemplo de petición: curl http://localhost:8080/api/2ye2Wgw4gimLv2eAKyk1NB?si=b3be439ee65542a4 (id de Metalica)
