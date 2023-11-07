import me.biabani.n2ac.constants.Language;
import me.biabani.n2ac.converter.Converter;
import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    void simpleTest() {
        System.out.println(Converter.of(Language.PERSIAN).convert("123456789"));
    }
}
