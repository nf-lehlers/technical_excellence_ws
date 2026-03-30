# TDD-Starter (Python)

Minimales Beispiel für testgetriebene Entwicklung: eine kleine Domäne (`add` in `example.calculator`) und **ein grüner Beispieltest** (`test_adds_two_numbers`).

## Voraussetzungen

- **Python 3.10+**
- **`pip`**

## Stack

Tests mit **pytest**, Code unter `src/`, Tests unter `tests/`.

## Einrichtung

Virtuelle Umgebung (empfohlen):

```bash
python3 -m venv .venv
source .venv/bin/activate
```

**Windows (PowerShell):** `.\.venv\Scripts\Activate.ps1`

Abhängigkeiten und Projekt im Editable-Modus:

```bash
pip install -e ".[dev]"
```

## Tests ausführen

```bash
pytest
```

Erwartung: ein bestandener Test.

## Tests im Watch-Modus (`ptw`)

Mit **`pytest-watcher`** (CLI: **`ptw`**) werden Tests bei Änderungen an `*.py` automatisch neu ausgeführt (liegt in den Dev-Abhängigkeiten).

```bash
ptw .
```

Ersten Lauf sofort starten, danach weiter beobachten:

```bash
ptw . --now
```

Zusätzliche pytest-Optionen kannst du ans Ende setzen, z. B. `ptw . -q` (ruft `pytest` mit `-q` auf).
