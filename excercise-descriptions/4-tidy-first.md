# Übung 4: Tidy First – EventTicketService

## Domäne

Ein Event-Ticketing-System, das Tickets verkauft, Preise berechnet und Bestätigungen verschickt.

## Aufgabe

Der Code funktioniert korrekt – alle Tests sind grün.
Aber der Code ist unaufgeräumt. Wende die Tidyings aus der Präsentation an, um den Code Schritt für Schritt lesbarer zu machen.

**Wichtig:** Die Tests müssen nach **jedem einzelnen Tidying** weiterhin grün sein!

## Tidying-Checkliste

Finde und behebe die folgenden Probleme im Code:

- [ ] **Guard Clauses** – Tief verschachtelte if/else-Blöcke durch Early Returns ersetzen
- [ ] **Dead Code** – Unbenutzte Methoden und Felder entfernen
- [ ] **Normalize Symmetries** – Inkonsistente String-Erzeugung vereinheitlichen
- [ ] **New Interface, Old Implementation** – Separate connect/send/disconnect-Aufrufe hinter einer einfachen Methode verstecken
- [ ] **Reading Order** – Methoden in die richtige Reihenfolge bringen (Abhängigkeiten nach oben)
- [ ] **Cohesion Order** – Zusammengehörige Methoden gruppieren
- [ ] **Explaining Variables** – Komplexe Ausdrücke in benannte Zwischenvariablen aufteilen
- [ ] **Explaining Constants** – Magic Numbers durch benannte Konstanten ersetzen
- [ ] **Explicit Parameters** – Objekt/Map-Parameter durch explizite Parameter ersetzen

## Hinweise

- Mache **kleine Schritte** – ein Tidying nach dem anderen
- Führe nach **jedem Schritt** die Tests aus
- Die Reihenfolge der Tidyings ist frei wählbar
- Manche Tidyings lassen sich gut kombinieren (z.B. Explaining Constants + Explaining Variables)
