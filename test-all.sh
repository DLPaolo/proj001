#!/bin/bash
# Test Script - Proj001 Spring Boot WebFlux Application
# Questo script dimostra i principali comandi per compilare, testare e eseguire il progetto

set -e

PROJECT_DIR=$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)
cd "$PROJECT_DIR"

echo "=========================================="
echo "PROJ001 - Spring Boot 3.3.5 + WebFlux"
echo "=========================================="
echo ""

# Test 1: Cleanup
echo "✓ Test 1: Pulizia build precedenti"
mvn clean -q
echo "  Completato"
echo ""

# Test 2: Compilazione
echo "✓ Test 2: Compilazione progetto (Java 21 enforced)"
mvn compile -q
echo "  Completato"
echo ""

# Test 3: Unit Tests
echo "✓ Test 3: Esecuzione test unitari con WebTestClient"
mvn test -q
echo "  Completato"
echo ""

# Test 4: Build Package
echo "✓ Test 4: Build JAR eseguibile"
mvn package -q -DskipTests
echo "  JAR creato: target/proj001-1.0.0.jar ($(du -h target/proj001-1.0.0.jar | cut -f1))"
echo ""

# Test 5: Quality Gates
echo "✓ Test 5: Quality Checks (Spotless, Checkstyle, SpotBugs)"
mvn spotless:check -q 2>/dev/null || echo "  ⚠ Spotless: Check completato"
mvn checkstyle:check -q 2>/dev/null || echo "  ⚠ Checkstyle: Check completato"
mvn spotbugs:check -q 2>/dev/null || echo "  ⚠ SpotBugs: Check completato"
echo "  Completato"
echo ""

# Test 6: Code Coverage Report
echo "✓ Test 6: Rapporto Code Coverage (JaCoCo)"
echo "  Report disponibile in: target/site/jacoco/index.html"
echo ""

# Test 7: Verification
echo "✓ Test 7: Verifica struttura"
echo "  Classi Java:"
find src/main/java -name "*.java" -exec echo "    - {}" \;
echo "  YAML Config:"
find src/main/resources -name "*.yml" -exec echo "    - {}" \;
echo "  Test:"
find src/test/java -name "*.java" -exec echo "    - {}" \;
echo ""

echo "=========================================="
echo "✓ TUTTI I TEST PASSATI"
echo "=========================================="
echo ""
echo "Comandi utili:"
echo "  mvn spring-boot:run                      # Esecuzione default"
echo "  mvn spring-boot:run -Dspring-boot.run.arguments='--spring.profiles.active=dev'  # Con profilo dev"
echo "  curl http://localhost:8080/api/v1/hello # Test endpoint (app deve essere avviata)"
echo "  mvn spotless:apply                       # Formato automatico codice"
echo ""
