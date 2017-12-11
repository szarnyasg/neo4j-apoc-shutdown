import apoc.export.graphml.ExportGraphML;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.io.fs.FileUtils;
import org.neo4j.kernel.api.exceptions.KernelException;
import org.neo4j.kernel.impl.proc.Procedures;
import org.neo4j.kernel.internal.GraphDatabaseAPI;

import java.io.File;
import java.io.IOException;

public class EmbeddedNeo4j {
	private static final File databaseDirectory = new File("/tmp/db");

	public void run() throws IOException, KernelException {
		FileUtils.deleteRecursively(databaseDirectory);
		GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabaseBuilder(databaseDirectory)
				.setConfig("apoc.import.file.enabled", "true")
				.newGraphDatabase();
		Procedures proceduresService = ((GraphDatabaseAPI) graphDb).getDependencyResolver()
				.resolveDependency(Procedures.class);
		proceduresService.registerProcedure(ExportGraphML.class);

		try (Transaction t = graphDb.beginTx()) {
			graphDb.execute("CALL apoc.import.graphml('my.graphml', {batchSize: 1000, useTypes: true})");
			t.success();
		}

		System.out.println("Shutting down database ...");
		graphDb.shutdown();
	}

}
