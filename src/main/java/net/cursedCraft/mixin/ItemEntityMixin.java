package net.cursedCraft.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;
import java.util.List;

/**
 * @ Mixin (targetclass) just like any other mixin
 * extend it's superclass to use it's methods when needed
 */
@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {


/**
 * constructor to deal with superclass
 */
    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }
/**
 * @ Shadow every field and method from the target class you intend to use.
 * The methods and fields decorated with @ Shadow are dummies and will automatically be replaced with their respective values in the target method.
 * They pretty much exist just to let the class build.
 */
    @Shadow
    public ItemStack getStack(){return null;};

    @Shadow
    private int pickupDelay;

    @Shadow
    private void applyBuoyancy(){};

    //I think this method is what causes items to fling out of lava, but I'm not sure enough to put that into Yarn.
    @Shadow
    private void method_24348(){};

    @Shadow
    private boolean canMerge(){return true;};

    @Shadow
    private void tryMerge(ItemEntity other){};

    @Shadow
    private void tryMerge(){};

    /**
     * This annotation with an @ author is required by the @ Overwrite. Essentially asking you to explain yourself as to why you're overwriting rather than injecting.
     * @author awwshoot, dev of CursedCraft. Why the hell are you using this mod with other mods? Of course this is gonna have conflict errors.
     */
    @Overwrite
    public void tick(){

        if (this.getStack().isEmpty()) {
            this.remove();
        } else {
            super.tick();
            if (this.pickupDelay > 0 && this.pickupDelay != 32767) {
                --this.pickupDelay;
            }

            this.prevX = this.getX();
            this.prevY = this.getY();
            this.prevZ = this.getZ();
            Vec3d vec3d = this.getVelocity();
            if (this.isSubmergedIn(FluidTags.WATER)) {
                this.applyBuoyancy();
            } else if (this.isSubmergedIn(FluidTags.LAVA)) {
                this.method_24348();
            } else if (!this.hasNoGravity()) {
                // changed velocity from 0.0, -0.04 0.0
                this.setVelocity(this.getVelocity().add(0.01D, 0.04D, 0.01D));
            }

            if (this.world.isClient) {
                this.noClip = false;
            } else {
                this.noClip = !this.world.doesNotCollide(this);
                if (this.noClip) {
                    this.pushOutOfBlocks(this.getX(), (this.getBoundingBox().minY + this.getBoundingBox().maxY) / 2.0D, this.getZ());
                }
            }

            if (!this.onGround || squaredHorizontalLength(this.getVelocity()) > 9.999999747378752E-6D || (this.age + this.getEntityId()) % 4 == 0) {
                this.move(MovementType.SELF, this.getVelocity());
                float f = 0.98F;
                if (this.onGround) {
                    f = this.world.getBlockState(new BlockPos(this.getX(), this.getY() - 1.0D, this.getZ())).getBlock().getSlipperiness() * 0.98F;
                }

                this.setVelocity(this.getVelocity().multiply((double)f, 0.98D, (double)f));
                if (this.onGround) {
                    this.setVelocity(this.getVelocity().multiply(1.0D, -0.5D, 1.0D));
                }
            }

            boolean bl = MathHelper.floor(this.prevX) != MathHelper.floor(this.getX()) || MathHelper.floor(this.prevY) != MathHelper.floor(this.getY()) || MathHelper.floor(this.prevZ) != MathHelper.floor(this.getZ());
            int i = bl ? 2 : 40;
            if (this.age % i == 0) {
                if (this.world.getFluidState(this.getBlockPos()).isIn(FluidTags.LAVA) && !this.isFireImmune()) {
                    this.playSound(SoundEvents.ENTITY_GENERIC_BURN, 0.4F, 2.0F + this.random.nextFloat() * 0.4F);
                }

                if (!this.world.isClient && this.canMerge()) {
                    this.tryMerge();
                }
            }

            if (this.age != -32768) {
                ++this.age;
            }

            this.velocityDirty |= this.updateWaterState();
            if (!this.world.isClient) {
                double d = this.getVelocity().subtract(vec3d).lengthSquared();
                if (d > 0.01D) {
                    this.velocityDirty = true;
                }
            }

            if ((!this.world.isClient && this.age >= 6000)|| this.getY()>255) {
                this.remove();
            }

        }

    }







}
