package brachy.modularui.drawable.schema;

import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkSource;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.lighting.LevelLightEngine;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BooleanSupplier;

public class DummyChunkSource extends ChunkSource {

    @Getter
    private final SchemaLevel level;
    private final ConcurrentMap<ChunkPos, DummyChunk> chunks = new ConcurrentHashMap<>();
    @Getter
    private final LevelLightEngine lightEngine;

    public DummyChunkSource(SchemaLevel level) {
        this.level = level;
        this.lightEngine = new LevelLightEngine(this, true, true);
    }

    @Override
    public @Nullable ChunkAccess getChunk(int chunkX, int chunkZ, @NotNull ChunkStatus requiredStatus, boolean load) {
        ChunkPos pos = new ChunkPos(chunkX, chunkZ);
        return this.chunks.computeIfAbsent(pos, posLong1 -> {
            DummyChunk newChunk = new DummyChunk(level, pos);
            newChunk.setLoaded(true);
            return newChunk;
        });
    }

    public @Nullable DummyChunk getChunkIfPresent(int chunkX, int chunkZ) {
        return this.chunks.get(new ChunkPos(chunkX, chunkZ));
    }

    @Override
    public boolean hasChunk(int chunkX, int chunkZ) {
        return true;
    }

    @Override
    public void tick(@NotNull BooleanSupplier hasTimeLeft, boolean tickChunks) {}

    @Override
    public @NotNull String gatherStats() {
        return "Dummy, " + this.getLoadedChunksCount();
    }

    @Override
    public int getLoadedChunksCount() {
        return this.chunks.size();
    }
}
