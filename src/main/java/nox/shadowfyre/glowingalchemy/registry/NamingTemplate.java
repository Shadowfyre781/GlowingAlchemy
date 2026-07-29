package nox.shadowfyre.glowingalchemy.registry;

import nox.shadowfyre.glowingalchemy.registry.BlockShape;
import nox.shadowfyre.glowingalchemy.glowing_things.color.GlowColor;

public class NamingTemplate {

    public String format(
            BlockDefinition definition,
            GlowColor color,
            BlockShape shape) {

        definition.familyId();
        color.name();
        shape.resourceSuffix();

        StringBuilder name = new StringBuilder();

        if (color != null) {
            name.append(color.name()).append("_");
        }

        name.append(definition.familyId());

        if (!shape.isBaseBlock()) {
            name.append(shape.nameSuffix());
        }

        return name.toString();
    }

    }
