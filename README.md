# Gestor-Reserva-Ambientes
Cómo ejecutar el proyecto

Requisitos previos
Java 17 o superior instalado.
Maven instalado (o usar el mvnw incluido en el proyecto).
MySQL (o puedes cambiar la configuración en application.properties para usar H2 si prefieres una base de datos en memoria).

Pasos de instalación y ejecución
Clonar el repositorio:

Bash
git clone <URL-DE-TU-REPOSITORIO>
cd GestionAmbientes
Configurar la base de datos:
Abre src/main/resources/application.properties y ajusta las credenciales de tu base de datos:

Properties
spring.datasource.url=jdbc:mysql://localhost:3306/GestionAmbientes
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
Ejecutar el proyecto:
Abre una terminal en la carpeta raíz y ejecuta:

Bash
./mvnw spring-boot:run
Acceder a la API:
La API estará disponible en http://localhost:8080.