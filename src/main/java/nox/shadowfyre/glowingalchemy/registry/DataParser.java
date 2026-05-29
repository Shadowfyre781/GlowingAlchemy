package nox.shadowfyre.glowingalchemy.registry;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mojang.serialization.JsonOps;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataParser {
    public static List<MaterialMetadata> loadMaterialFiles(Path path) {
        List<MaterialMetadata> materials = new ArrayList<>();
        try (FileReader reader = new FileReader(path.toFile())) {
            JsonElement element = JsonParser.parseReader(reader);
            // This uses the Codec we defined in the record to parse the JSON
            MaterialMetadata.CODEC.parse(JsonOps.INSTANCE, element)
                    .resultOrPartial(System.err::println)
                    .ifPresent(materials::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return materials;
    }
}