# TDD-Starter (Java, Python, TypeScript)

Drei kleine Beispielprojekte für testgetriebene Entwicklung: jeweils eine minimale Domäne (`Calculator` / `add`) und **ein grüner Beispieltest**.

## Voraussetzungen

| Starter | Benötigt |
|--------|-----------|
| **Java** | JDK **25** (entspricht `maven.compiler.release` in `pom.xml`), **Apache Maven** |
| **Python** | **Python 3.10+**, `pip` |
| **TypeScript** | **Node.js** (LTS) und **npm** |

## 1. Java (`tdd-starter-java`)

Tests mit **JUnit 5** und **Maven**.

```bash
cd tdd-starter-java
mvn test
```

Erwartung: Build erfolgreich, Tests grün (`CalculatorTest`).

## 2. Python (`tdd-starter-python`)

Tests mit **pytest**, Paketlayout unter `src/`, Tests unter `tests/`.

Virtuelle Umgebung (empfohlen):

```bash
cd tdd-starter-python
python3 -m venv .venv
source .venv/bin/activate
```

**Windows (PowerShell):** `.\.venv\Scripts\Activate.ps1`

Abhängigkeiten und Projekt im Editable-Modus:

```bash
pip install -e ".[dev]"
```

Tests ausführen:

```bash
pytest
```

Erwartung: ein bestandener Test (`test_adds_two_numbers`).

## 3. TypeScript (`tdd-starter-typescript`)

Tests mit **Vitest**.

```bash
cd tdd-starter-typescript
npm install
npm test
```

Erwartung: ein bestandener Test (`calculator.test.ts`).

Optional **Watch-Modus** (Tests bei Änderungen neu ausführen):

```bash
npm run test:watch
```

---

Die drei Ordner können jeweils als eigenes Repository verwendet oder einzeln kopiert werden.
