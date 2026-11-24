package brachy.modularui.core.mixins.jei;

import brachy.modularui.integration.jei.recipe.ModularUIJeiCategory;

import com.llamalad7.mixinextras.sugar.Local;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.common.util.ImmutableSize2i;
import mezz.jei.gui.recipes.RecipeGuiLogic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = RecipeGuiLogic.class, remap = false)
public class RecipeGuiLogicMixin {

    // JEI 19.64 sizes the grid from recipeSizeWithButtons (JEI 19.25 had an int recipeHeight)
    @ModifyVariable(method = "getVisibleRecipeLayoutsWithButtons", at = @At(value = "STORE"), name = "recipeSizeWithButtons")
    private ImmutableSize2i modularui$useCategoryMaxHeightForLayout(ImmutableSize2i recipeSize,
                                                                    @Local(name = "recipeCategory") IRecipeCategory<?> recipeCategory) {
        // if this is a MUI category, force the gui to use the category's max height as the recipe height
        //  (instead of the height of the first recipe it finds, which may be shorter than others)
        if (recipeCategory instanceof ModularUIJeiCategory<?> muiCategory) {
            return new ImmutableSize2i(recipeSize.width(), muiCategory.getMaxHeight());
        }
        return recipeSize;
    }
}
