import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.stream;
import static org.assertj.core.api.Assertions.assertThat;

public class Calculator_ {

    private Calculator calculator;
    @Before
    public void setUp() throws Exception {
        calculator = new Calculator();
    }

    @Test
    public void given_empty_string_should_return_0(){
        assertThat(calculator.add("")).isEqualTo(0);
    }

    @Test
    public void given_a_number_should__return_it(){
        assertThat(calculator.add("4")).isEqualTo(4);
    }

    @Test
    public void given_a_set_of_numbers_separated_by_comas_should_return_the_sum_them(){
        assertThat(calculator.add("3,4")).isEqualTo(7);
    }

    @Test
    public void given_a_set_of_numbers_separated_by_comas_or_newlines_should_return_the_sum_them(){
        assertThat(calculator.add("1\n2,3")).isEqualTo(6);
    }

    @Test
    public void given_a_bad_usage_of_separators_should_return_the_sum_of_numbers(){
        assertThat(calculator.add("\n1")).isEqualTo(1);
    }

    private class Calculator{

        private static final String Separators = "[,|\n]";

        public int add(String numbers){
            return stream(tokenise(numbers))
                    .filter(s->!s.isEmpty())
                    .mapToInt(Integer::parseInt).sum();
        }

        private String[] tokenise(String numbers){
            return numbers.split(Separators);
        }
    }
}