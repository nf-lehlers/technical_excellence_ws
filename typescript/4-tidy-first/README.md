# Übung 4: Tidy First – EventTicketService (TypeScript)

## Setup

```bash
npm install
npm test
```

## Aufgabe

Der Code funktioniert korrekt – alle Tests sind grün.
Wende die Tidyings aus der Präsentation an, um den Code Schritt für Schritt lesbarer zu machen.

**Wichtig:** Die Tests müssen nach **jedem einzelnen Tidying** weiterhin grün sein!

## Tidying-Checkliste

- [ ] **Guard Clauses** – Tief verschachtelte if/else durch Early Returns ersetzen
- [ ] **Dead Code** – Unbenutzte Methoden und Felder entfernen
- [ ] **Normalize Symmetries** – Inkonsistente String-Erzeugung vereinheitlichen
- [ ] **New Interface, Old Implementation** – connect/send/disconnect hinter einer Methode verstecken
- [ ] **Reading Order** – Methoden in die richtige Reihenfolge bringen
- [ ] **Cohesion Order** – Zusammengehörige Methoden gruppieren
- [ ] **Explaining Variables** – Komplexe Ausdrücke in benannte Variablen aufteilen
- [ ] **Explaining Constants** – Magic Numbers durch benannte Konstanten ersetzen
- [ ] **Explicit Parameters** – Options-Objekt durch explizite Parameter ersetzen
