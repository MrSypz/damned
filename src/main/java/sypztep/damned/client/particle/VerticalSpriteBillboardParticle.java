package sypztep.damned.client.particle;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Vec3d;

public abstract class VerticalSpriteBillboardParticle extends SpriteBillboardParticle {
    protected final SpriteProvider spriteProvider;
    protected final Vec3d initialDirection;
    private float height, width;

    protected VerticalSpriteBillboardParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider, double velocityX, double velocityY, double velocityZ) {
        super(world, x, y, z, 0.0, 0.0, 0.0);
        this.spriteProvider = spriteProvider;
        this.maxAge = 10;
        this.scale = 4.5f;
        this.gravityStrength = 0.008F;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
        this.setSpriteForAge(this.spriteProvider);
        float cameraYaw = MinecraftClient.getInstance().player.getYaw();
        double yawRad = Math.toRadians(cameraYaw + 90); //PS: + 90 to fact to player camera
        this.initialDirection = new Vec3d(-Math.sin(yawRad), 0, Math.cos(yawRad));
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    @Override
    public void buildGeometry(VertexConsumer buffer, Camera camera, float ticks) {
        float size = Math.max(height, width) / 2.0f;
        Vec3d particlePos = new Vec3d(this.x, this.y, this.z).subtract(camera.getPos());

        float offsetX = size * (float) initialDirection.x;
        float minX = (float) (particlePos.x - offsetX);
        float maxX = (float) (particlePos.x + offsetX);

        // Adjust minY and maxY to prevent stretching
        float minY = (float) (particlePos.y - height / 2.0f); // Adjust based on particle height
        float maxY = (float) (particlePos.y + height / 2.0f); // Adjust based on particle height

        float offsetZ = size * (float) initialDirection.z;
        float minZ = (float) (particlePos.z - offsetZ);
        float maxZ = (float) (particlePos.z + offsetZ);

        // Pre-calculate texture coordinates and lighting for efficiency
        float minU = getMinU();
        float maxU = getMaxU();
        float minV = getMinV();
        float maxV = getMaxV();
        int light = getBrightness(ticks);

        // Render particle geometry
        buffer.vertex(minX, maxY, minZ).texture(maxU, maxV).color(red, green, blue, alpha).light(light);
        buffer.vertex(minX, minY, minZ).texture(maxU, minV).color(red, green, blue, alpha).light(light);
        buffer.vertex(maxX, minY, maxZ).texture(minU, minV).color(red, green, blue, alpha).light(light);
        buffer.vertex(maxX, maxY, maxZ).texture(minU, maxV).color(red, green, blue, alpha).light(light);

        buffer.vertex(maxX, maxY, maxZ).texture(minU, maxV).color(red, green, blue, alpha).light(light);
        buffer.vertex(maxX, minY, maxZ).texture(minU, minV).color(red, green, blue, alpha).light(light);
        buffer.vertex(minX, minY, minZ).texture(maxU, minV).color(red, green, blue, alpha).light(light);
        buffer.vertex(minX, maxY, minZ).texture(maxU, maxV).color(red, green, blue, alpha).light(light);
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteForAge(this.spriteProvider);
    }
}
