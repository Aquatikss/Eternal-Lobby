package com.aquamobs.world;

import net.minestom.server.instance.generator.GenerationUnit;
import net.minestom.server.instance.generator.Generator;
import net.minestom.server.instance.block.Block;

public class CustomGenerator implements Generator {
    @Override
    public void generate(GenerationUnit unit) {
 
        unit.modifier().fillHeight(0, 40, Block.GRASS_BLOCK);
    }
}