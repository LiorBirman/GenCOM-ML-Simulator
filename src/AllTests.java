import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ConfigTest.class, GenerateDFsTest.class, InvalidRelationsTest.class, RelationsTableTest.class, SimulatorOutputTest.class, TransmitterTest.class})
public class AllTests {

}

