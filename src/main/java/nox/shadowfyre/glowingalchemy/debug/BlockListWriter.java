        package nox.shadowfyre.glowingalchemy.debug;

import nox.shadowfyre.glowingalchemy.registry.BlockDefinitions;
import nox.shadowfyre.glowingalchemy.registry.ExpansionEngine;
import nox.shadowfyre.glowingalchemy.registry.GeneratedBlock;
import nox.shadowfyre.glowingalchemy.registry.RegistrationEngine;
import nox.shadowfyre.glowingalchemy.glowing_things.registry.GT_BlockDefinitions;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BlockListWriter {

    // Call this from either hook — pass the output folder root
    public static void write(Path outputDir) {

        BlockDefinitions.registerAll();
        GT_BlockDefinitions.registerAll();

        List<GeneratedBlock> blocks = ExpansionEngine.generateAll();

        int blockCount = 0;
        int blockItemCount = 0;

        Path file = Path.of("reports", "BlockList.txt");
        System.out.println("[BlockListWriter] writing to = " + file.toAbsolutePath());

        try {
            Files.createDirectories(file.getParent());

            StringBuilder content = new StringBuilder();

            for (GeneratedBlock block : blocks) {
                String namespace = block.namespace();
                String name = block.name();

                boolean blockRegistered =
                        RegistrationEngine.get(namespace, name) != null;

                boolean blockItemRegistered =
                        RegistrationEngine.getBlockItem(namespace, name) != null;

                if (blockRegistered) {
                    blockCount++;
                }

                if (blockItemRegistered) {
                    blockItemCount++;
                }

                content.append(namespace)
                        .append(":")
                        .append(name)
                        .append("\n")
                        .append("    block: ")
                        .append(blockRegistered ? "registered" : "MISSING")
                        .append("\n")
                        .append("    item: ")
                        .append(blockItemRegistered ? "registered" : "MISSING")
                        .append("\n\n");
            }

            content.insert(
                    0,
                    "Generated count = " + blocks.size() + "\n" +
                            "Block count = " + blockCount + "\n" +
                            "BlockItem count = " + blockItemCount + "\n\n"
            );

            Files.writeString(file, content.toString());

            System.out.println("[BlockListWriter] generated count = " + blocks.size());
            System.out.println("[BlockListWriter] block count = " + blockCount);
            System.out.println("[BlockListWriter] blockItem count = " + blockItemCount);

        } catch (IOException e) {
            System.err.println(
                    "[GlowingAlchemy] Failed to write BlockList.txt: "
                            + e.getMessage()
            );
        }

    }

}

