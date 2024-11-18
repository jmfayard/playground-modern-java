package playground;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * JEPS441 Pattern Matching for Switch in Java 21
 * https://openjdk.org/jeps/441
 */
public class PatternMatchingSwitchJava21 {
    static double getBalanceWithSwitchPattern(Account account) {
        return switch (account) {
            case SavingsAccount sa -> sa.getSavings();
            case TermAccount ta -> ta.getTermAccount();
            case CurrentAccount ca -> ca.getCurrentAccount();
            default -> account.getBalance();
        };
    }

    static class Account {
        double getBalance() {
            return 0;
        }
    }

    static class SavingsAccount extends Account {
        double getSavings() {
            return 100;
        }
    }

    static class TermAccount extends Account {
        double getTermAccount() {
            return 1000;
        }
    }

    static class CurrentAccount extends Account {
        double getCurrentAccount() {
            return 10000;
        }
    }

    @Test
    void testPatternMatching() {
        var accounts = List.of(
                new CurrentAccount(),
                new SavingsAccount(),
                new TermAccount(),
                new Account());
        var expected = List.of(10000, 100, 1000, 0);
        var actual = accounts.stream()
                .map(account -> ((int) getBalanceWithSwitchPattern(account)))
                .collect(Collectors.toList());
        assertThat(actual).isEqualTo(expected);
    }
}
