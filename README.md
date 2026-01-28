# Proj001 - Spring Boot 4 WebFlux Application

## Descrizione

Questo è un progetto Spring Boot 4 con stack reattivo (WebFlux). Implementa un semplice endpoint REST che ritorna "Hello World" in JSON, con configurazione completa per un ambiente enterprise-grade.

### Caratteristiche Principali

- **Spring Boot 3.3.5**: Framework modern con support reactive
- **Spring WebFlux**: Stack reattivo asincrono
- **Java 21**: LTS version con virtual threads
- **Spring Cloud Integration**: OpenFeign, Function, Stream
- **Lombok**: Riduzione del boilerplate code
- **Quality Gates**: Enforcer, Spotless, Checkstyle, SpotBugs, JaCoCo

## Requisiti

- **Java 21** (JDK 21 o superiore)
- **Maven 3.8.0** (o superiore)
- Linux/Mac/Windows

## Struttura Progetto

```
proj001/
├── src/
│   ├── main/
│   │   ├── java/it/pdl/prog001/
│   │   │   ├── Proj001Application.java
│   │   │   ├── controller/
│   │   │   │   └── HelloController.java
│   │   │   └── dto/
│   │   │       └── HelloResponse.java
│   │   └── resources/
│   │       ├── application.yml
│   │       └── application-dev.yml
│   └── test/
│       └── java/it/pdl/prog001/
│           └── Proj001ApplicationTest.java
├── config/
│   └── checkstyle.xml
└── pom.xml
```

## Comandi Principali

### Compilazione e Test

```bash
# Pulire, compilare e eseguire test
mvn clean test

# Build completo
mvn clean package

# Installazione nel repository locale
mvn clean install
```

### Esecuzione Applicazione

```bash
# Esecuzione con profilo default
mvn spring-boot:run

# Esecuzione con profilo dev 
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
```

### Quality Checks

```bash
# Code formatting con Spotless
mvn spotless:apply

# Checkstyle verification
mvn checkstyle:check

# SpotBugs analysis
mvn spotbugs:check

# JaCoCo code coverage report (generato in target/site/jacoco/index.html)
mvn test jacoco:report
```

## Endpoint API

### Hello Endpoint

```http
GET /api/v1/hello
```

**Response (200 OK):**
```json
{
  "message": "Hello World"
}
```

**Esempio con curl:**
```bash
curl http://localhost:8080/api/v1/hello
```

## Actuator Endpoints

L'applicazione espone tutti gli actuator endpoints per monitoring:

```bash
curl http://localhost:8080/actuator
curl http://localhost:8080/actuator/health
curl http://localhost:8080/actuator/metrics
```

## Configurazione Profili

L'applicazione supporta profili Spring per diverse configurazioni:

### Profilo Default (application.yml)
- Port: 8080 (non configurato esplicitamente, usa default)
- Log level: INFO (root), DEBUG (it.pdl.prog001)
- OpenFeign timeouts: connect=5000ms, read=10000ms

### Profilo dev (application-dev.yml)
- Port: 8080 (uguale al default)
- Log level: INFO (root), DEBUG (it.pdl.prog001)
- OpenFeign timeouts: connect=3000ms, read=8000ms
- Log aggiuntivi per Spring Cloud

### Attivazione Profilo da CLI

**Dev profile:**
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
```

**Custom profile (es. "svil"):**
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=svil"
```

**Nota:** I profili NON sono attivati automaticamente in application.yml. Usa sempre l'opzione `--spring.profiles.active` da CLI se desideri un profilo specifico.

## Configurazione YAML

### application.yml

Contiene configurazione base:
- Application name: `proj001`
- Spring Cloud OpenFeign default client config
- Placeholder per Stream e Function
- Logging configuration
- Actuator management endpoints

### application-dev.yml

Override per profilo development:
- OpenFeign timeouts ridotti
- Enhanced logging per Spring Cloud

## Dipendenze Principali

| Dipendenza | Versione | Scopo |
|-----------|----------|-------|
| spring-boot-starter-webflux | 3.3.5 | Stack reattivo |
| spring-boot-starter-actuator | 3.3.5 | Monitoring e health checks |
| spring-cloud-starter-openfeign | 2023.0.3 | HTTP client reattivo |
| spring-cloud-starter-function-web | 2023.0.3 | Funzioni serverless |
| spring-cloud-starter-stream-rabbit | 2023.0.3 | Message streaming |
| lombok | Latest | Riduzione boilerplate |
| vavr | 0.10.4 | Functional Java library |

## Configurazione Quality Gates

### Maven Enforcer
- **Java version**: Enforced 21+
- **Maven version**: Enforced 3.8.0+

### Spotless
- Formatter: Google Java Format
- Applica formatting automaticamente

### Checkstyle
- File: `config/checkstyle.xml`
- Severity: Warning (non bloccante)
- Linea max: 120 caratteri

### SpotBugs
- Scansione statica dei bug
- Output in `target/site/spotbugs/`

### JaCoCo
- Code coverage report
- Output in `target/site/jacoco/index.html`
- Integrato nella fase `test`

## Note Importanti

### Spring Cloud BOM
- Versione `2023.0.3` è compatibile con Spring Boot 3.3.5
2. **YAML Configuration**: Solo YAML, niente `application.properties`
3. **No Kafka Binder**: Configurato solo RabbitMQ per Stream
4. **Lombok Processing**: Configurato via annotation processor nel pom.xml
5. **Java 21 Required**: Progetto non è retrocompatibile con versioni minori

## Troubleshooting

### Build fallisce su Java version
```bash
# Verificare versione Java installata
java -version

# Impostare JAVA_HOME se necessario
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
```

### Lombok non genera il getter/setter
```bash
# Assicurarsi che l'IDE sia configurato correttamente
# Per Maven build da CLI, funziona automaticamente via annotation processor
mvn clean compile
```

### Port 8080 già in uso
```bash
# Usare una porta diversa via CLI
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
```

## Author

Created for Spring Boot 4 learning purposes
