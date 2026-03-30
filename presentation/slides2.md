---
title: Introduction to TDD (Part 2)
colorSchema: light
background: /images/cc_title_2.jpg
transition: none
---

<div style="position: absolute; top: 5%; left: 0; right: 0; padding: 0 2rem; text-align: center; z-index: 2; box-sizing: border-box;">
<h3 style="color: white !important; font-size: 4rem !important; line-height: 1.12 !important; margin: 0;">
  Introduction to <br/>Test Driven Development<br /> Part II
</h3>
</div>

<p style="position: absolute; bottom: 60px; right: 40px; z-index: 2; color: white; text-transform: none; text-align: right">Ferdinand Ade &<br> Marco Emrich</p>

---

## Kent Beck's Canon TDD

  1. List of the test scenarios
  1. Turn  one item into a test
  1. Make the test (& all previous tests) pass
  1. Refactor to improve (optional)
  1. Go to #2

---
background: /images/canontdd.jpg
backgroundSize: contain
---

<small style="position: absolute; bottom: 20px; right: 40px; color: #333;"><a href="https://substack.com/@vicwu">by Vic Wu</a></small>

---
background: /images/fire.jpg
class: bg-slide
---

# Test Doubles

---
background: /images/fire_dim.jpg
class: bg-slide
---

## Test Doubles

- Dummies
- Fakes
- Stubs
- Mocks
- Spies

---

## Sociable vs Solitary\*

<img src="/images/isolate.png">

<sub>(*) from M. Fowler/ J. Fields</sub>

---
background: /images/backdoor_g.jpg
class: bg-slide
---

<h2 style="position: absolute; top: 300px; left: 40px;">Frontdoor<br />vs<br />Backdoor Testing</h2>

---

### Result verification (Frontdoor)

```javascript
expect(add(3, 4)).toEqual(7);
```

### State verification (Frontdoor)

```javascript
deck = new DeckOfCards(31);
deck.addCardOnTop("♥9");
expect(deck.numberOfCards).toEqual(32);
```

### Behavior verification (Backdoor)

```javascript
pushSpy = jest.spyOn(Array.prototype.push);
deck.addCardOnTop("♥9");
expect(pushSpy).toHaveBeenCalledWith("♥9");
```

---
background: /images/weights.jpg
class: bg-slide
---

# Exercise

## Tire Pressure

---
background: /images/weights.jpg
class: bg-slide
---

# GO !
