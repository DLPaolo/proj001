
#!/bin/sh
#
# ============================================================================
# MAVEN WRAPPER SCRIPT - Guida Didattica per Sviluppatori
# ============================================================================
#
# SCOPO DEL FILE:
# Questo script consente di eseguire Maven senza necessità di installarlo
# manualmente sul sistema. Il wrapper scarica automaticamente la versione
# corretta di Maven se non è già presente.
#
# QUANDO UTILIZZARLO:
# - Quando cloni un progetto Maven che contiene questo script
# - Per assicurare che tutti gli sviluppatori usino la stessa versione Maven
# - Per automatizzare il build in ambienti CI/CD
# - Per ridurre le dipendenze di sistema nei nuovi progetti
#
# COME UTILIZZARLO:
# Invece di usare il comando "mvn", utilizza "./mvnw" (su Linux/Mac)
# o "mvnw.cmd" (su Windows) dalla root del progetto.
#
# ESEMPI DI COMANDI:
#   ./mvnw clean              # Pulisce i file generati dal build precedente
#   ./mvnw compile            # Compila il codice sorgente
#   ./mvnw test               # Esegue i test
#   ./mvnw package            # Crea il pacchetto (JAR/WAR)
#   ./mvnw install            # Installa il pacchetto nel repository locale
#   ./mvnw clean install      # Pulisce e installa in uno step
#
# ============================================================================
#

# ESTRAZIONE DELLA DIRECTORY BASE:
# Determina il percorso della directory in cui si trova questo script
BASEDIR=$(dirname "$0")

# CONFIGURAZIONE DELLE VARIABILI DI AMBIENTE:
# Imposta le directory principali per Maven
# - MAVEN_PROJECTBASEDIR: Directory del progetto (default: BASEDIR)
# - MAVEN_HOME: Directory di Maven wrapper (default: BASEDIR/.mvn)
MAVEN_PROJECTBASEDIR="${MAVEN_BASEDIR:-$BASEDIR}"
MAVEN_HOME="${MAVEN_HOME:-$BASEDIR/.mvn}"

# CONTROLLO DI VALIDAZIONE:
# Verifica se il file maven-wrapper.jar esiste nella directory .mvn/wrapper
# Se non esiste, lo script esce con errore e chiede di configurare il wrapper
if [ ! -f "$BASEDIR/.mvn/wrapper/maven-wrapper.jar" ]; then
    echo "Maven wrapper not configured. Please run: mvn wrapper:wrapper"
    exit 1
fi

# ESECUZIONE DI MAVEN:
# Esegue Java per lanciare il Maven Wrapper
# - "-classpath": specifica il percorso del JAR da eseguire
# - "org.apache.maven.wrapper.MavenWrapperMain": la classe principale da eseguire
# - "$@": passa tutti gli argomenti ricevuti al comando Maven
exec java \
  -classpath "$MAVEN_HOME/wrapper/maven-wrapper.jar" \
  org.apache.maven.wrapper.MavenWrapperMain "$@"
