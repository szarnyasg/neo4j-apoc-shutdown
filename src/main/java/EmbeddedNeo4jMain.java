import org.neo4j.kernel.api.exceptions.KernelException;

import java.io.IOException;

public class EmbeddedNeo4jMain {

    public static void main(String[] args) throws IOException, KernelException {
        EmbeddedNeo4j n = new EmbeddedNeo4j();
        n.run();
    }

}
