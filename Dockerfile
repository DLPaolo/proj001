# Stage 1: immagine "builder" con Maven e JDK 21 per compilare il progetto.
FROM maven:3.9.9-eclipse-temurin-21 AS build

# Directory di lavoro all'interno dell'immagine.
WORKDIR /app
# Copiamo prima i file di configurazione Maven per sfruttare la cache dei layer.

COPY pom.xml .
COPY .mvn/ .mvn/
#  Copiamo lo script Maven Wrapper. Che si trova nella root del progetto e che serve per eseguire Maven senza averlo installato globalmente.
COPY mvnw .

# Rendiamo eseguibile lo script Maven Wrapper.
RUN chmod +x mvnw

# Scarica le dipendenze una sola volta (ottimizza rebuild successive). Per questo usiamo il goal "dependency:go-offline". Il flag -q riduce l'output.
# Questo comando prepara l'ambiente Maven scaricando tutte le dipendenze necessarie senza compilare il progetto. Va fatto prima di copiare il codice sorgente.
#DskipTests serve per saltare i test in questa fase, dato che stiamo solo scaricando le dipendenze.
RUN ./mvnw -q -DskipTests dependency:go-offline
# Copiamo il codice sorgente solo dopo le dipendenze.
COPY src/ src/
# Compiliamo il jar senza test per velocizzare la build dell'immagine. Per questo usiamo il flag -DskipTests. Il flag -q riduce l'output. 
#Viene generato nella cartella target/ del progetto e si chiama app.jar, perché è il nome configurato nel pom.xml, per cambiare il nome bisogna modificare il pom.xml
# nel seguente modo: <finalName>nome-desiderato</finalName>, all'interno del tag <build>.
RUN ./mvnw -q -DskipTests package

# Stage 2: immagine finale, leggera, con solo JRE per eseguire l'app.
FROM eclipse-temurin:21-jre
# Directory di lavoro per l'applicazione.
WORKDIR /app
# Copiamo il jar generato dallo stage di build, che si trova nella cartella target/ del progetto.
COPY --from=build /app/target/*.jar app.jar
# Documentiamo la porta su cui l'app espone HTTP (non pubblica automaticamente).
EXPOSE 8080
# Avvio dell'applicazione quando il container parte.
ENTRYPOINT ["java", "-jar", "app.jar"]
