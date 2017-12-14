package pkg;

import org.junit.Test;
import org.neo4j.kernel.api.exceptions.KernelException;
import pkg.EmbeddedNeo4j;

import java.io.IOException;

public class EmbeddedNeo4jTest {

    @Test
    public void test() throws IOException, KernelException {
        EmbeddedNeo4j n = new EmbeddedNeo4j();
        n.run();
    }

}
