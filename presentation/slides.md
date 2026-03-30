---
title: Introduction to TDD
colorSchema: light
background: /images/cc_title_2.jpg
---

<div style="position: absolute; top: 10%; left: 0; right: 0; padding: 0 2rem; text-align: center; z-index: 2; box-sizing: border-box;">
<h3 style="color: white !important; font-size: 4rem !important; line-height: 1.12 !important; margin: 0;">
  Introduction to <br/>Test Driven Development
</h3>
</div>

<p style="position: absolute; bottom: 60px; right: 40px; z-index: 2; color: white; text-transform: none; text-align: right">Ferdinand Ade &<br> Marco Emrich</p>


---
layout: two-cols
---

<img src="/images/accelerate.jpg" class="borderless" style="max-height: 420px">

::right::

## Accelerate

**Nicole Forsgren, Jez Humble, Gene Kim**
*(IT Revolution Press, 2018)*

- 4 years of research
- 23,000+ survey responses
- 2,000+ organizations worldwide
- Winner of the Shingo Publication Award

<br>

> "The Science of Lean Software and DevOps"

---

## Can software delivery drive **business outcomes**?

<v-clicks>

- Forsgren et al. asked: what separates high-performing teams?
- Key finding: **speed and stability are not a trade-off**
- High performers are **2× more likely** to exceed profitability & market share goals
- The 4 DORA metrics measure exactly this: **throughput** and **stability**

</v-clicks>

<v-click>

> "High performers win on both dimensions."

</v-click>

---

## The 4 DORA Key Metrics

<v-clicks>

- **Deployment Frequency** – How often do you deploy to production?
- **Lead Time for Changes** – Time from **commit** to running in production
- **Change Failure Rate** – % of deployments causing incidents
- **Time to Restore Service** – How fast do you recover from failures?

</v-clicks>

---
layout: two-cols
---

## Elite Performers

<v-clicks>

- Deploy **on demand** (multiple times/day)
- Lead time: **< 1 hour**
- Change failure rate: **0–15%**
- Recovery time: **< 1 hour**

</v-clicks>

::right::

## Low Performers

<v-clicks>

- Deploy **< once per 6 months**
- Lead time: **1–6 months**
- Change failure rate: **46–60%**
- Recovery time: **> 6 months**

</v-clicks>

*(DORA State of DevOps Report 2023)*

---
background: /images/blocks.jpg
class: bg-slide
---

## How do Elite Performers achieve **both**?

<v-click>

## Speed **and** Stability?

</v-click>

---

## The Safety Net for Speed

<v-clicks>

- Frequent deployments require **automated confidence**
- Manual testing does **not scale**
- TDD is the foundation for **sustainable CI/CD**

</v-clicks>

<v-click>

> TDD is about **designing for testability**.

</v-click>

---
background: /images/typing.jpg
class: bg-slide
---

## What is the most

## **time consuming activity**

## in programming?

---
background: /images/wheel_g.jpg
class: bg-slide
---

- **A** - New features
- **B** - Refactoring
- **C** - Hunt for bugs

---
background: /images/wheel_g.jpg
class: bg-slide
---

- **A** - New features
- **B** - Refactoring
- <span style="color: red">**C - Hunt for bugs**</span>

---

## Rule of Ten


<img src="/images/ruleOfTen.png">

*(Hans Mulder, Jim Johnson, 2014)*

---
background: /images/chest.png
backgroundSize: contain
class: bg-slide
---

# Secret
# of
# Test Driven Development


---
background: /images/watch.jpg
class: bg-slide
---

## Doesn't writing Tests take a lot of time?

<v-click>

# Yes, but...

</v-click>

---
background: /images/bughunt.jpg
class: bg-slide
---

<div style="margin-top: 25vh;">

# Bug Hunting

</div>

---
background: /images/apples.png
class: bg-slide
---

# Higher Quality
## No loss of Productivity

---
background: /images/lab.jpg
class: bg-slide
---

# Research?

---

> The results […] indicate that [...] the resulting quality was higher than teams that adopted a non-TDD
> approach by an order of at least **two times**

&mdash; _Evaluating the Efficacy of Test-Driven Development_

Microsoft Research (2006)

---

> Test-first [...] did increase productivity probably because tests allow for better task understanding, better task focus, faster learning, and lower rework efforts

&mdash; _On the Effectiveness of Test-first Approach to Programming_

Carnegie Mellon University (2005)

---
background: /images/metastudy.jpg
class: bg-slide
---

# Meta-Studies

---

> Studies in the high rigor and high relevance category ([...]9 studies) show positive results for external quality, and indicate that no difference or negative results are obtained for productivity.


&mdash; _Considering rigor and relevance when evaluating TDD[...]_

Munir, Moayyed, Petersen, Blekinge Institute of Technology, Sweden (2014)

---
background: /images/ampel_g.png
class: bg-slide
---

# TDD

## When?

---
background: /images/ampel_g.png
class: bg-slide
---

<h3 style="position: absolute; top: 230px; left: 20px">Invented</h3>

## 1957

<img src="/images/von_neumann.gif" width="300px"/>

### John von Neuman

---
background: /images/punch_card.jpg
---

---
background: /images/ampel_g.png
class: bg-slide
---

<h3 style="position: absolute; top: 190px; left: 670px">Rediscovered</h3>

## 1989

<img src="/images/kent.jpg" width="300px"/>

### Kent Beck

---
background: /images/ampel_g.png
class: bg-slide
---

# 30 Years of TDD

<!--
Dieses Jahr feiern wir also 30 Jahre TDD
-->

---
background: /images/ampel_g.png
class: bg-slide
---

# ...or 60 ?

---
background: /images/yuno.jpg
backgroundSize: contain
---

---
background: /images/hard1.jpg
---

---
background: /images/learn_tdd.png
backgroundSize: contain
class: bg-white
---

---
background: /images/fail.jpg
class: bg-slide
---

# Fail

---
background: /images/fail.jpg
class: bg-slide
---

# Slow

# Hard to Maintain

---

<img src="/images/capoo-why.gif" class="borderless" width="700" />

---

> TDD is not about Testing

&mdash; everywhere on the Internet

---
background: /images/nottesting.png
backgroundSize: contain
---

---

<img src="/images/kent.jpg" width="300px"/>


> Bullshit, of course it is about testing, but..

&mdash; Kent Beck, 2023

---

<img src="/images/kent.jpg" width="300px"/>


> It's not **only** about testing

---
background: /images/design.jpg
class: bg-slide
---

# TDD === Design

---
background: /images/dan.jpg
class: bg-slide
---

<span style="font-size: 2rem; position: absolute; top: 190px; left: 650px;">
Dan North, 2006<br> <a href="https://dannorth.net/introducing-bdd">Introducing BDD</a><br/>
</span>

---
background: /images/docs.jpg
class: bg-slide
---

# Tests === Specs

---
background: /images/docs.jpg
class: bg-slide
---

# Living Documentation

---

# Example

## Leap Year

```javascript
isLeapYear(2000); // => true
```

---

### a bad Test

```javascript
test("testIsLeapYearIsCorrect", () => {
      expect(isLeapYear(2016)).toBeTruthy()
      expect(isLeapYear(2000)).toBeTruthy()
      expect(isLeapYear(3)).toBeFalsy();
      expect(isLeapYear(100)).toBeFalsy();
      ...
}
```

<br/>
<small>from <a href="https://vimeo.com/289852238">Structure and Interpretation of Test Cases</a> by Kevlin Henney</small>

---

### a Spec Document
<ul class="small">
<li>A year is a leap year if it is divisible by 4 but not by 100</li>
<li>A year is a leap year if it is divisible by 400</li>
<li>A year is NOT a leap year if it is not divisible by 4</li>
<li>A year is NOT a leap year if it is divisible by 100 but not by 400</li>
</ul>
<br/><br/><br/>
<small>from <a href="https://vimeo.com/289852238">Structure and Interpretation of Test Cases</a> by Kevlin Henney</small>

---

```javascript
test("A year is a leap year if it is divisible by 4 but not by 100", {
  ...
});

test("A year is a leap year if it is divisible by 400", {
  ...
});

test("A year is NOT a leap year if it is not divisible by 4", {
  ...
});

test("A year is NOT a leap year if it is divisible by 100 but not by 400", {
  ...
});

```

---

```javascript
describe("A year is a leap year if", () => {
  it("is divisible by 4 but not by 100", () => {
    expect(isLeapYear(2016)).toBeTruthy();
  });
  it("is divisible by 400", () => {
    expect(isLeapYear(2000)).toBeTruthy();
  });
});
describe("A year is *NOT* a leap year if", () => {
  it("is not divisible by 4", () => {
    expect(isLeapYear(3)).toBeFalsy();
  });
  it("is divisible by 100 but not by 400", () => {
    expect(isLeapYear(100)).toBeFalsy();
  });
});
```

---
background: /images/ampel_g.png
class: bg-slide
---

## String Calculator

## Kata

<img src="/images/roy.png" width="200">

Roy Osherove

---
background: /images/ampel_g.png
class: bg-slide
---

## String Calculator

## Kata

# "1,2,3" => 6

---
background: /images/katas.jpg
class: bg-slide
---

# Katas & Code Retreats

- https://kata-log.rocks
- https://www.coderetreat.org
- https://www.softwerkskammer.org

---
background: /images/demo.jpg
class: bg-slide
---

## String Calculator

# => Demo

---
background: /images/ampel.png
backgroundSize: contain
class: bg-white
---

---
background: /images/ampel_g.png
class: bg-slide
---

# <span style="color: red;">A</span>rrange

# <span style="color: red;">A</span>ct

# <span style="color: red;">A</span>ssert

---
background: /images/docs.jpg
class: bg-slide
---

# Specs / Living Documentation

---
background: /images/universe.jpg
class: bg-slide
---

## Readable Test Names

```javascript
AYearIsALeapYearIfItIsDivisibleBy4ButNotBy100

A year is a leap year if it is divisible by 4 but not by 100

A_year_is_a_leap_year_if_it_is_divisible_by_4_but_not_by_100
```

---
background: /images/focus.jpg
class: bg-slide
---

# Focus

<!--
Damit ich den Fehler verstehe, darf der Test nur eine Sache tun, Fokus auf SUT
-->

---
background: /images/mouse.jpg
class: bg-slide
---

# SUT

### Subject under Test

---
background: /images/isolation.jpg
class: bg-slide
---

# Isolation

<!--
Jest garantiert keine Reihenfolge, Tests dürfen sich nicht beeinflussen
=> Flaky Tests
-->

---
background: /images/babysteps.jpg
class: bg-slide
---

# Baby Steps

---
background: /images/weights.jpg
class: bg-slide
---

# Exercise

## String Calculator

---
background: /images/1.png
---

---
background: /images/2.png
---

---
background: /images/weights.jpg
class: bg-slide
---

# GO !

