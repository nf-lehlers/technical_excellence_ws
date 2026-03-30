# Birthday Greetings

You would like to send a birthday email to all your friends on their birth date. You have a lot of friends and you're a bit lazy, so you want a program to do it for you.

The program should send an email like this:

```
Subject: Happy birthday!

Happy birthday, dear <first_name>!
```

## How to approach this

Use **outside-in TDD** with **test doubles**.

Start with a test for the behavior you want: *"Given its John's birthday, send him a greeting email."*

**Important constraints:**
- Your tests must **not** read from the filesystem or send real emails.
- Don't start by building a database repository or an email client — start by writing a test that describes what should happen.
- Let the test guide you: what collaborators does your code need? What should their interfaces look like?
- Use test doubles for those collaborators.

The interfaces aren't designed up front — they **emerge** from your tests.

## Additional Features

Once the core greeting works, pick from these (in any order):

- **Leap year birthdays**: Friends born on February 29th should be greeted on February 28th in non-leap years.

- **Birthday reminder**: Send a reminder to all *other* friends when someone has a birthday:

    ```
    Subject: Birthday Reminder

    Dear <first_name>,

    Today is <someone_else_first_name> <someone_else_last_name>'s birthday.
    Don't forget to send them a message!
    ```

- **Consolidated reminder**: Instead of one reminder per birthday, send a single message listing all birthdays today:

    ```
    Subject: Birthday Reminder

    Dear <first_name>,

    Today is <full_name_1>, <full_name_2> and <full_name_3>'s birthday.
    Don't forget to send them a message!
    ```

- **Adapter integration test**: Write a test for a *real* CSV-based repository that reads from the filesystem. This is the only test that touches real I/O. The file looks like this:

    ```
    last_name, first_name, date_of_birth, email
    Doe, John, 1982/10/08, john.doe@foobar.com
    Ann, Mary, 1975/09/11, mary.ann@foobar.com
    ```

---

*inspired by: https://codingdojo.org/kata/birthday-greetings/*
