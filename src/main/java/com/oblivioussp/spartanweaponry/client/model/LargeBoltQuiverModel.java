package com.oblivioussp.spartanweaponry.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * ModelQuiver - ObliviousSpartan
 * Created using Tabula 8.0.0; Also edited manually by ObliviousSpartan
 * To allow arrows to be rendered if there is sufficient arrows in the quiver
 */
@OnlyIn(Dist.CLIENT)
public class LargeBoltQuiverModel extends QuiverModelBase
{
    public ModelRenderer quiver;
    public ModelRenderer strap_front;
    public ModelRenderer strap_top;
    public ModelRenderer strap_back;
    public ModelRenderer strap_bottom;
    public ModelRenderer arrow_1_1;
    public ModelRenderer arrow_1_2;
    public ModelRenderer arrow_2_1;
    public ModelRenderer arrow_2_2;
    public ModelRenderer arrow_3_1;
    public ModelRenderer arrow_3_2;
    public ModelRenderer arrow_4_1;
    public ModelRenderer arrow_4_2;
    public ModelRenderer arrow_5_1;
    public ModelRenderer arrow_5_2;

    public LargeBoltQuiverModel() 
    {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.quiver = new ModelRenderer(this, 0, 0);
        this.quiver.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.quiver.addBox(-3.0F, -3.0F, 3.0F, 6.0F, 5.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(quiver, 0.0F, 0.0F, -0.5235987755982988F);
        this.strap_front = new ModelRenderer(this, 0, 16);
        this.strap_front.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.strap_front.addBox(-6.0F, -1.0F, -3.5F, 12.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(strap_front, 0.0F, 0.0F, -0.8726646259971648F);
        this.strap_top = new ModelRenderer(this, 0, 18);
        this.strap_top.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.strap_top.addBox(-3.5F, -1.0F, 6.0F, 7.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(strap_top, 0.0F, 1.5707963267948966F, -0.8726646259971648F);
        this.strap_back = new ModelRenderer(this, 0, 14);
        this.strap_back.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.strap_back.addBox(-6.0F, -1.0F, 2.5F, 12.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(strap_back, 0.0F, 0.0F, -0.8726646259971648F);
        this.strap_bottom = new ModelRenderer(this, 0, 20);
        this.strap_bottom.setRotationPoint(0.0F, 5.0F, 0.0F);
        this.strap_bottom.addBox(-3.5F, -1.0F, -7.0F, 7.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(strap_bottom, 0.0F, 1.5707963267948966F, -0.8726646259971648F);
        this.arrow_1_1 = new ModelRenderer(this, 26, 0);
        this.arrow_1_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_1_1.addBox(-8.6F, -3.6F, 1.1F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(arrow_1_1, 0.0F, 0.7853981633974483F, -0.5235987755982988F);
        this.arrow_1_2 = new ModelRenderer(this, 26, 0);
        this.arrow_1_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_1_2.addBox(-0.4F, -3.6F, 7.1F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(arrow_1_2, 0.0F, -0.7853981633974483F, -0.5235987755982988F);
        this.arrow_2_1 = new ModelRenderer(this, 26, 0);
        this.arrow_2_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_2_1.addBox(-7.4F, -3.6F, 2.4F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(arrow_2_1, 0.0F, 0.7853981633974483F, -0.5235987755982988F);
        this.arrow_2_2 = new ModelRenderer(this, 26, 0);
        this.arrow_2_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_2_2.addBox(0.9F, -3.6F, 5.9F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(arrow_2_2, 0.0F, -0.7853981633974483F, -0.5235987755982988F);
        this.arrow_3_1 = new ModelRenderer(this, 26, 0);
        this.arrow_3_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_3_1.addBox(-6.8F, -3.6F, 0.6F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(arrow_3_1, 0.0F, 0.7853981633974483F, -0.5235987755982988F);
        this.arrow_3_2 = new ModelRenderer(this, 26, 0);
        this.arrow_3_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_3_2.addBox(-0.9F, -3.6F, 5.3F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(arrow_3_2, 0.0F, -0.7853981633974483F, -0.5235987755982988F);
        this.arrow_4_1 = new ModelRenderer(this, 26, 0);
        this.arrow_4_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_4_1.addBox(-5.5F, -3.6F, 1.8F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(arrow_4_1, 0.0F, 0.7853981633974483F, -0.5235987755982988F);
        this.arrow_4_2 = new ModelRenderer(this, 26, 0);
        this.arrow_4_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_4_2.addBox(0.3F, -3.6F, 4.0F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(arrow_4_2, 0.0F, -0.7853981633974483F, -0.5235987755982988F);
        this.arrow_5_1 = new ModelRenderer(this, 26, 0);
        this.arrow_5_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_5_1.addBox(-6.1F, -3.6F, 3.6F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(arrow_5_1, 0.0F, 0.7853981633974483F, -0.5235987755982988F);
        this.arrow_5_2 = new ModelRenderer(this, 26, 0);
        this.arrow_5_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.arrow_5_2.addBox(2.1F, -3.6F, 4.6F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(arrow_5_2, 0.0F, -0.7853981633974483F, -0.5235987755982988F);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) 
    { 
        ImmutableList.of(this.quiver, this.strap_front, this.strap_back, this.strap_bottom, this.strap_top).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
        renderArrows(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

	@Override
	protected void renderArrows(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn,
			int packedOverlayIn, float red, float green, float blue, float alpha) 
	{
		//RenderHelper.disableStandardItemLighting();
		if(this.arrowsToRender >= 1)
		{
			ImmutableList.of(this.arrow_1_1, this.arrow_1_2).forEach((modelRenderer) -> {
				modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			});
		}
		if(this.arrowsToRender >= 2)
		{
			ImmutableList.of(this.arrow_2_1, this.arrow_2_2).forEach((modelRenderer) -> {
				modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			});
		}
		if(this.arrowsToRender >= 3)
		{
			ImmutableList.of(this.arrow_3_1, this.arrow_3_2).forEach((modelRenderer) -> {
				modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			});
		}
		if(this.arrowsToRender >= 4)
		{
			ImmutableList.of(this.arrow_4_1, this.arrow_4_2).forEach((modelRenderer) -> {
				modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			});
		}
		if(this.arrowsToRender >= 5)
		{
			ImmutableList.of(this.arrow_5_1, this.arrow_5_2).forEach((modelRenderer) -> {
				modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			});
		}
		//RenderHelper.enableStandardItemLighting();
	}
	
    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

	@Override
	public void rotate(BipedModel<LivingEntity> model) 
	{
//		quiver.setRotationPoint(quiver.rotationPointX + model.bipedBody.rotateAngleX, quiver.rotationPointY + model.bipedBody.rotateAngleY, quiver.rotationPointZ + model.bipedBody.rotateAngleZ);
	}
}
