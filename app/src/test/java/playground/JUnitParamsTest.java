package playground;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.runner.RunWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


/**
 * https://github.com/Pragmatists/JUnitParams
 * https://www.baeldung.com/junit-params
 *
 */
@RunWith(JUnitParamsRunner.class)
public class JUnitParamsTest {
    // Function under test
    public int safeAdd(int a, int b) {
        long result = ((long) a) + b;
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) result;
    }

    @Test
    @Parameters({
            "1, 2, 3",
            "-10, 30, 20",
            "15, -5, 100",
            "-5, -10, -15" })
    public void whenWithAnnotationProvidedParams_thenSafeAdd(
            int a, int b, int expectedValue) {
        assertThat(safeAdd(a, b)).isEqualTo(expectedValue);
    }

    @Test
    @Parameters({"17, false",
            "22, true" })
    public void personIsAdult(int age, boolean valid) throws Exception {
        assertThat(age % 2 == 0).isEqualTo(valid);
    }

}
