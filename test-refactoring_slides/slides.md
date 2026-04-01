---
# You can also start simply with 'default'
theme: ./theme
# random image from a curated Unsplash collection by Anthony
# like them? see https://unsplash.com/collections/94734566/slidev
title: Refactoring Unit-Tests
info: |
  ## Tidy First
class: text-center
# https://sli.dev/custom/highlighters.html
highlighter: shiki
# https://sli.dev/guide/drawing
drawings:
  persist: false
# slide transition: https://sli.dev/guide/animations#slide-transitions
transition: none
# enable MDC Syntax: https://sli.dev/guide/syntax#mdc-syntax
mdc: true
layout: cover
background: title.png
---

# Refactoring Unit-Tests

<div text-10>
  Marco Emrich (codecentric)
</div>


---
zoom: 0.9
---

## Real Code from 2005

```ruby
def test_validate_password
    @user.password = ''
    @user.password_confirmation = ''
    assert !@user.save
    assert_equal ["is too short (minimum is 5 characters)",
        "has invalid characters", "can't be blank"],

    @user.errors.on(:password)
    @user.password = 'testest'
    @user.password_confirmation = 'testtest'
    assert !@user.save
    assert_equal "doesn't match confirmation" , @user.errors.on(:password)

    @user.password = ''
    @user.password_confirmation = 'testtest'
    assert !@user.save
    assert_equal ["is too short (minimum is 5 characters)",
        "has invalid characters", "doesn't match confirmation",
        "can't be blank"], @user.errors.on(:password)

    @user.password = 'testtest'
    @user.password_confirmation = ''
    assert !@user.save
    assert_equal "doesn't match confirmation", @user.errors.on(:password)

    @user.password = 'test'
    ...
end
```

---
layout: image
class: text-center
image: hope.jpg
---

# There is still hope

---
layout: image
class: text-center
image: refactoring_robot.jpg
---

<h1 position-relative top-90>Test Refactoring</h1>

---
layout: image
class: text-center
image: shop.jpg
---

# Example: Seminar-Shop

---
layout: image
class: text-center
image: shop_bugs2.jpg
---

# Example: Seminar-Shop


---
layout: image
class: text-center
image: specs.jpg
---

# Specs


---
layout: two-cols
---

## Seminar

 * has **title** and **netPrice**
 * calculates **grossPrice** (19% VAT)
 * can be tax free
 * There is a resale fee: 212.49€
 * under 500€ - processing fee: 29.00€

:: right::

## Discounts

 * Event: 5% 3-Letter-Discount



---
layout: image
image: testsuite1.png
---

---
layout: image
image: testsuite2.png
---

---
layout: image
class: text-center
image: solution.jpg
---

<h1 position-relative top-90>Solution</h1>


---
layout: two-cols
---

```ts
export class Seminar {
  constructor(
    public title: string,
    private _netPrice: number,
    public taxFree: boolean
  ) {}
  get grossPrice(): number {
    return this.netPrice * this.taxRate();
  }
  get originalNetPrice(): number {
    return this._netPrice;
  }
  get netPrice(): number {
    return (
      this._netPrice +
      (this._netPrice > RESALE_FEE_LIMIT ? UPPER_RESALE_FEE : LOWER_RESALE_FEE)
    );
  }

 set netPrice(value: number) {
    this._netPrice = value;
  }

  taxRate(): number {
    return this.taxFree ? 1 : TAX_RATE / 100;
  }
}
```

<arrow  x1="390" y1="440" x2="370" y2="480" color="#6A6" width="2" z-40 arrowSize="1" />

:: right ::

```ts

export class ThreeLetterDiscount {
  constructor(private seminar: Seminar) {}

  apply(): void {
    this.seminar.netPrice = this.seminar.originalNetPrice - this.discount();
  }

  isGranted(): boolean {
    this.seminar.title;
    return this.seminar.title.length <= 3;
  }

  discount(): number {
    return this.seminar.originalNetPrice * this.discountRate();
  }

  discountRate(): number {
    return !this.isGranted() ? 0 : THREE_LATER_DISCOUNT_RATE / 100;
  }
}

```

<arrow  x1="805" y1="190" x2="775" y2="230" color="#6A6" width="2" z-40 arrowSize="1" />

---
layout: image
image: suits.jpg
class: text-center
---

<h1 position-relative top-90>Comparing Suit{e}s</h1>

---
layout: image
image: apples.jpg
class: text-center
---

<div position-relative top-90>

# Quality Criteria
# for Test Cases

</div>

---
layout: image-right
image: khorikov.png
---

## Four Pillars
## of a good unit test
Vlad Khorikov

- Fast feedback

- Maintainability

- Protection against regressions

- Resistance to refactoring

<br/>

J.B. Rainsberger
- Defect Localization

---
layout: image
image: defect_localization.jpg
---

# Defect
# Localization


---
layout: image-right
image: jb2.jpg
zoom: 1.6
---

## J.B. Rainsberger

> a test method checks exactly
one interesting behavior


---
layout: image
image: apples.jpg
class: text-center
---

<div position-relative top-85>

# Test Quality Criteria

# Contradictions?

</div>

---
layout: image
image: apples.jpg
class: text-center
---

<div position-relative top-85>

# Test Quality Criteria

# Finding Good Trade-Offs

</div>


---
layout: image-right
image: tdd.png
---

# TDD

## Test Driven Development

---
layout: image
image: refactoring_robot.jpg
class: text-center
---

# Legacy Test Suites
<h1 position-relative top-90>Test Refactoring</h1>

---
layout: image-left
image: maintain.jpg
---

# Test Code needs maintenance

---
layout: image-left
image: xunit.jpg
---

# Refactoring Catalogue

---
layout: image-right
image: fields.png
---

# Refactoring Catalogue


---
layout: image-right
image: refactoring_robot.jpg
---

## Split to AAA
 - Arange Act Assert

## Fresh Fixture


---

````md magic-move {lines: true}

```ts
describe("Seminar", () => {
  it("should calculate correct grossPrices", () => {
    const seminar = new Seminar("OOP", 700, false);

    const discount = new ThreeLetterDiscount(seminar);
    discount.apply();
    expect(seminar.grossPrice).toEqual(1044.2131);

    seminar.netPrice = 300;
    discount.apply();
    expect(seminar.grossPrice).toEqual(373.65999999999997);

    seminar.taxFree = true;
    discount.apply();
    expect(seminar.grossPrice).toEqual(299.75);

    seminar.title = "Objekt-Orientierte Programmierung";
    discount.apply();
    expect(seminar.grossPrice).toEqual(299.75);
  });
});
```

```ts
describe("Seminar", () => {
  it("calculate the correct grossPrice for a Seminar over RESALE limit with Three Letter discount and tax", () => {
    const seminar = new Seminar("OOP", 700, false);
    const discount = new ThreeLetterDiscount(seminar);
    discount.apply();
    expect(seminar.grossPrice).toEqual(1044.2131);
  });

  it("calculates the correct grossPrice for a Seminar under RESALE limit with Three Letter discount and tax", () => {
    const seminar = new Seminar("OOP", 300, false);
    const discount = new ThreeLetterDiscount(seminar);
    discount.apply();
    expect(seminar.grossPrice).toEqual(373.65999999999997);
  });

  it("calculates the correct grossPrice for a tax-free Seminar under RESALE limit with Three Letter discount", () => {
    const seminar = new Seminar("OOP", 285, false);
    const discount = new ThreeLetterDiscount(seminar);
    seminar.taxFree = true;
    discount.apply();
    expect(seminar.grossPrice).toEqual(299.75);
  });

  it("should not apply ThreeLetterDiscount if the seminar title has more than 3 letters", () => {
    const seminar = new Seminar("Objekt-Orientierte Programmierung", 270.75, true);
    const discount = new ThreeLetterDiscount(seminar);
    discount.apply();
    expect(seminar.grossPrice).toEqual(299.75);
  });
});
```
````

<style>
.slidev-code-wrapper {
  margin-top: -50px !important;
  margin-left: -20px !important;
  width: 105% !important;
}
</style>

---
layout: image-right
image: refactoring_robot.jpg
---

## Increase Focus

## Canonical Fixture


---

````md magic-move  {lines: true}

```ts
describe("Seminar", () => {
  it("calculate the correct grossPrice for a Seminar over RESALE limit with Three Letter discount and tax", () => {
    const seminar = new Seminar("OOP", 700, false);
    const discount = new ThreeLetterDiscount(seminar);
    discount.apply();
    expect(seminar.grossPrice).toEqual(1044.2131);
  });

  it("calculates the correct grossPrice for a Seminar under RESALE limit with Three Letter discount and tax", () => {
    const seminar = new Seminar("OOP", 300, false);
    const discount = new ThreeLetterDiscount(seminar);
    discount.apply();
    expect(seminar.grossPrice).toEqual(373.65999999999997);
  });

  it("calculates the correct grossPrice for a tax-free Seminar under RESALE limit with Three Letter discount", () => {
    const seminar = new Seminar("OOP", 285, false);
    const discount = new ThreeLetterDiscount(seminar);
    seminar.taxFree = true;
    discount.apply();
    expect(seminar.grossPrice).toEqual(299.75);
  });

  it("should not apply ThreeLetterDiscount if the seminar title has more than 3 letters", () => {
    const seminar = new Seminar("Objekt-Orientierte Programmierung", 270.75, true);
    const discount = new ThreeLetterDiscount(seminar);
    discount.apply();
    expect(seminar.grossPrice).toEqual(299.75);
  });
});
```

```ts
describe("Seminar", () => {
  it("calculates the correct grossPrice for a tax-free Seminar over RESALE LIMIT", () => {
    const seminar = new Seminar("OOP", 700, true);
    expect(seminar.grossPrice).toEqual(700 + UPPER_RESALE_FEE);
  });

  it("calculates the correct grossPrice for a tax-free Seminar under RESALE LIMIT", () => {
    const seminar = new Seminar("OOP", 300, true);
    expect(seminar.grossPrice).toEqual(300 + LOWER_RESALE_FEE);
  });

  it("calculates the correct grossPrice for a Seminar under RESALE limit with TAX", () => {
    const seminar = new Seminar("OOP", 100 - LOWER_RESALE_FEE, false);
    expect(seminar.grossPrice).toEqual(119);
  });

  it("should not apply ThreeLetterDiscount if the seminar title has more than 3 letters", () => {
    const seminar = new Seminar("Objekt-Orientierte Programmierung", 100, true);

    const discount = new ThreeLetterDiscount(seminar);
    discount.apply();
    expect(seminar.grossPrice).toEqual(100 + LOWER_RESALE_FEE);
  });
});
```
````
<style>
.slidev-code-wrapper {
  margin-top: -50px !important;
  margin-left: -20px !important;
  width: 105% !important;
}
</style>

---

```ts
  it("calculates the correct grossPrice for a tax-free Seminar over RESALE LIMIT", () => {
    const seminar = new Seminar("OOP", 700, true);
    expect(seminar.grossPrice).toEqual(700 + UPPER_RESALE_FEE);
  });

  it("calculates the correct grossPrice for a tax-free Seminar under RESALE LIMIT", () => {
    const seminar = new Seminar("OOP", 300, true);
    expect(seminar.grossPrice).toEqual(300 + LOWER_RESALE_FEE);
  });
```

<arrow  x1="350" y1="210" x2="350" y2="300" color="#953" width="2" z-40 arrowSize="1" />

<div mt-20>

```typescript
  it("calculates the correct netPrice for a Seminar over RESALE LIMIT", () => {
    const seminar = new Seminar("OOP", 700, true);
    expect(seminar.netPrice).toEqual(700 + UPPER_RESALE_FEE);
  });

  it("calculates the correct netPrice for a Seminar under RESALE LIMIT", () => {
    const seminar = new Seminar("OOP", 300, true);
    expect(seminar.netPrice).toEqual(300 + LOWER_RESALE_FEE);
  });
```
</div>


---


````md magic-move  {lines: true}


```ts
describe("Seminar", () => {
  it("calculates the correct grossPrice for a tax-free Seminar over RESALE LIMIT", ...);

  it("calculates the correct grossPrice for a tax-free Seminar under RESALE LIMIT", ...);

  it("calculates the correct grossPrice for a Seminar under RESALE limit with TAX", ...);

  it("should not apply ThreeLetterDiscount if the seminar title has more than 3 letters", () => {
    const seminar = new Seminar("Objekt-Orientierte Programmierung", 100, true);

    const discount = new ThreeLetterDiscount(seminar);
    discount.apply();
    expect(seminar.grossPrice).toEqual(100 + LOWER_RESALE_FEE);
  });
});
```

```ts
describe("Seminar", () => {
  it("calculates the correct grossPrice for a tax-free Seminar over RESALE LIMIT", ...);

  it("calculates the correct grossPrice for a tax-free Seminar under RESALE LIMIT", ...);

  it("calculates the correct grossPrice for a Seminar under RESALE limit with TAX", ...);
});

describe("ThreeLetterDiscount", () => {
  it("should not apply ThreeLetterDiscount if the seminar title has more than 3 letters", () => {
    const seminar = new Seminar("Objekt-Orientierte Programmierung", 100, true);

    const discount = new ThreeLetterDiscount(seminar);
    discount.apply();
    expect(seminar.netPrice).toEqual(100 + LOWER_RESALE_FEE);
  });

  it("should apply ThreeLetterDiscount if the seminar title has 3 letters", () => {
    const seminar = new Seminar("OOP", 100, true);

    const discount = new ThreeLetterDiscount(seminar);
    discount.apply();
    expect(seminar.netPrice).toEqual(95 + LOWER_RESALE_FEE);
  });
});
```
````

---
layout: image-right
image: refactoring_robot.jpg
---

## Test Data

* Extract Setup Function
* Object Mother
* Test Data Factory


---

## Test Data Factory


```ts

const createSeminarFixture = ({
  title = "Introduction to Test-Driven Development",
  netPrice = 100,
  taxFree = false,
} = {}) => {
  return new Seminar(title, netPrice, taxFree);
};


```

---

````md magic-move  {lines: true}


```ts
describe("Seminar", () => {
  it("calculates the correct netPrice for a Seminar over RESALE LIMIT", () => {
    const seminar = new Seminar("OOP", 700, true);
    expect(seminar.netPrice).toEqual(700 + UPPER_RESALE_FEE);
  });

  it("calculates the correct netPrice for a Seminar under RESALE LIMIT", () => {
    const seminar = new Seminar("OOP", 300, true);
    expect(seminar.netPrice).toEqual(300 + LOWER_RESALE_FEE);
  });

  it("calculates correct grossPrice for a Seminar under RESALE limit with TAX", () => {
    const seminar = new Seminar("OOP", 100 - LOWER_RESALE_FEE, false);
    expect(seminar.grossPrice).toEqual(119);
  });
});

```

```ts
describe("Seminar", () => {
  it("calculates the correct netPrice for a Seminar over RESALE LIMIT", () => {
    const seminar = createSeminarFixture({ netPrice: 700 });
    expect(seminar.netPrice).toEqual(700 + UPPER_RESALE_FEE);
  });

  it("calculates the correct netPrice for a Seminar under RESALE LIMIT", () => {
    const seminar = createSeminarFixture({ netPrice: 300 });
    expect(seminar.netPrice).toEqual(300 + LOWER_RESALE_FEE);
  });

  it("calculates correct grossPrice for a Seminar under RESALE limit with TAX", () => {
    const seminar = createSeminarFixture({
      netPrice: 100 - LOWER_RESALE_FEE,
      taxFree: false,
    });
    expect(seminar.grossPrice).toEqual(119);
  });

  it("calculates correct grossPrice for a tax-free Seminar under RESALE limit", () => {
    const seminar = createSeminarFixture({
      netPrice: 100 - LOWER_RESALE_FEE,
      taxFree: true,
    });
    expect(seminar.grossPrice).toEqual(100);
  });
});

```


````
---

````md magic-move  {lines: true}


```ts
describe("ThreeLetterDiscount", () => {
  it("should not apply ThreeLetterDiscount if the seminar title has more than 3 letters", () => {
    const seminar = new Seminar("Objekt-Orientierte Programmierung", 100, true);

    const discount = new ThreeLetterDiscount(seminar);
    discount.apply();
    expect(seminar.netPrice).toEqual(100 + LOWER_RESALE_FEE);
  });
  it("should apply ThreeLetterDiscount if the seminar title has 3 letters", () => {
    const seminar = new Seminar("OOP", 100, true);

    const discount = new ThreeLetterDiscount(seminar);
    discount.apply();
    expect(seminar.netPrice).toEqual(95 + LOWER_RESALE_FEE);
  });
});
```

```ts
describe("ThreeLetterDiscount", () => {
  it("should not apply ThreeLetterDiscount if the seminar title has more than 3 letters", () => {
    const seminar = createSeminarFixture({
      netPrice: 100,
      title: "Objekt-Orientierte Programmierung",
    });

    const discount = new ThreeLetterDiscount(seminar);
    discount.apply();
    expect(seminar.netPrice).toEqual(100 + LOWER_RESALE_FEE);
  });

  it("should apply 5% ThreeLetterDiscount if the seminar title has 3 letters", () => {
    const seminar = createSeminarFixture({
      netPrice: 100,
      title: "OOP",
    });
    const discount = new ThreeLetterDiscount(seminar);
    discount.apply();
    expect(seminar.netPrice).toEqual(95 + LOWER_RESALE_FEE);
  });
});
```


````
---

````md magic-move {lines: true}


```ts
describe("ThreeLetterDiscount", () => {
  it("should not apply ThreeLetterDiscount if the seminar title has more than 3 letters", () => {
    const seminar = createSeminarFixture({
      netPrice: 100,
      title: "Objekt-Orientierte Programmierung",
    });

    const discount = new ThreeLetterDiscount(seminar);
    discount.apply();
    expect(seminar.netPrice).toEqual(100 + LOWER_RESALE_FEE);
  });

  it("should apply 5% ThreeLetterDiscount if the seminar title has 3 letters", () => {
    const seminar = createSeminarFixture({
      netPrice: 100,
      title: "OOP",
    });
    const discount = new ThreeLetterDiscount(seminar);
    discount.apply();
    expect(seminar.netPrice).toEqual(95 + LOWER_RESALE_FEE);
  });
});
```

```ts
describe("ThreeLetterDiscount", () => {
  it("should grant ThreeLetterDiscount if the seminar title has 3 letters", () => {
    const seminar = createSeminarFixture({ title: "OOP" });
    const discount = new ThreeLetterDiscount(seminar);
    expect(discount.isGranted()).toBeTruthy();
  });

  it("should NOT grant ThreeLetterDiscount if the seminar title has more than 3 letters", () => {
    const seminar = createSeminarFixture({
      title: "Objekt-Orientierte Programmierung",
    });
    const discount = new ThreeLetterDiscount(seminar);
    expect(discount.isGranted()).toBeFalsy();
  });

  it("should apply 5% ThreeLetterDiscount if the seminar title has 3 letters", () => {
    const seminar = createSeminarFixture({
      netPrice: 100,
      title: "OOP",
    });
    const discount = new ThreeLetterDiscount(seminar);
    discount.apply();
    expect(seminar.netPrice).toEqual(95 + LOWER_RESALE_FEE);
  });
});

```


````

---

# Sociable vs Solitary

## Jay Fields

<br />
<br />

<img src="/isolate.png" w-200 />

---
layout: image
image: mock.jpg
---

# Test Doubles
## Mocks & Stubs

---

<img src="/mock_stubs.png" w-200 />

---

```ts
const Seminar = vi.fn();
Seminar.prototype.title = vi.fn();
Seminar.prototype.originalNetPrice = vi.fn();
Seminar.prototype.netPrice = vi.fn();
```

---

````md magic-move {lines: true}

```ts
describe("ThreeLetterDiscount", () => {
  it("should grant ThreeLetterDiscount if the seminar title has 3 letters", () => {
    const seminar = createSeminarFixture({ title: "OOP" });
    const discount = new ThreeLetterDiscount(seminar);
    expect(discount.isGranted()).toBeTruthy();
  });

  it("should NOT grant ThreeLetterDiscount if the seminar title has more than 3 letters", () => {
    const seminar = createSeminarFixture({
      netPrice: 100,
      title: "Objekt-Orientierte Programmierung",
    });
    const discount = new ThreeLetterDiscount(seminar);
    expect(discount.isGranted()).toBeFalsy();
  });

  it("should apply 5% ThreeLetterDiscount if the seminar title has 3 letters", ...);
});
```

```ts
describe("ThreeLetterDiscount", () => {
  it("should grant ThreeLetterDiscount if the seminar title has 3 letters", () => {
    const seminar = new Seminar();
    vi.spyOn(seminar, "title", "get").mockReturnValue("OOP");
    const discount = new ThreeLetterDiscount(seminar);
    expect(discount.isGranted()).toBeTruthy();
  });

  it("should not grant ThreeLetterDiscount if the seminar title has more than 3 letters", () => {
    const seminar = new Seminar();
    vi.spyOn(seminar, "title", "get").mockReturnValue(
      "Objekt-Orientierte Programmierung"
    );
    const discount = new ThreeLetterDiscount(seminar);
    expect(discount.isGranted()).toEqual(false);
  });

  it("should apply 5% ThreeLetterDiscount if the seminar title has 3 letters", ...);
});
```

````

---

````md magic-move {lines: true}

```ts
describe("ThreeLetterDiscount", () => {
  it("should grant ThreeLetterDiscount if the seminar title has 3 letters", ...);

  it("should NOT grant ThreeLetterDiscount if the seminar title has more than 3 letters", ...);

  it("should apply 5% ThreeLetterDiscount if the seminar title has 3 letters", () => {
    const seminar = createSeminarFixture({
      netPrice: 100,
      title: "OOP",
    });
    const discount = new ThreeLetterDiscount(seminar);
    discount.apply();
    expect(seminar.netPrice).toEqual(95 + LOWER_RESALE_FEE);
  });
});
```

```ts
describe("ThreeLetterDiscount", () => {
  it("should grant ThreeLetterDiscount if the seminar title has 3 letters", ...);

  it("should NOT grant ThreeLetterDiscount if the seminar title has more than 3 letters", ...);

  it("should apply 5% ThreeLetterDiscount if granted", () => {
    const seminar = new Seminar();
    vi.spyOn(seminar, "originalNetPrice", "get").mockReturnValue(100);
    vi.spyOn(seminar, "isGranted").mockReturnValue(true);
    const netPriceSetter = vi.spyOn(seminar, "netPrice", "set");

    const discount = new ThreeLetterDiscount(seminar);
    discount.apply();
    expect(netPriceSetter).toBeCalledWith(95);
  });

  it("should not apply 5% ThreeLetterDiscount if NOT granted", () => {
    const seminar = new Seminar();
    vi.spyOn(seminar, "isGranted").mockReturnValue(false);
    const netPriceSetter = vi.spyOn(seminar, "netPrice", "set");

    const discount = new ThreeLetterDiscount(seminar);
    discount.apply();
    expect(netPriceSetter).not.toBeCalledWith();
  });
});
```

````

---

```ts
describe("Seminar", () => {
  it("calculates the correct netPrice for a Seminar over RESALE LIMIT", () => {
    const seminar = createSeminarFixture({ netPrice: 700 });
    expect(seminar.netPrice).toEqual(700 + UPPER_RESALE_FEE);
  });
```

<arrow  x1="350" y1="140" x2="350" y2="230" color="#953" width="2" z-40 arrowSize="1" />

<div mt-20>

```typescript
describe("Seminar", () => {
  it("calculates the correct netPrice for a Seminar over RESALE LIMIT", () => {
    const seminar = createSeminarFixture({ netPrice: 700 });
    expect(seminar.netPrice).toBeCloseTo(700 + UPPER_RESALE_FEE, 2);
  });
```
</div>

