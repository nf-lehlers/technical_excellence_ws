package workshop.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ThingTest {

    @Test
    void addsTwoNumbers() {
        assertThat(new Thing().add(2, 3)).isEqualTo(5);
    }
}
