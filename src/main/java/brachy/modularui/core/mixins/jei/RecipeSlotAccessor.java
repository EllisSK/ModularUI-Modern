package brachy.modularui.core.mixins.jei;

import mezz.jei.api.gui.ingredient.IRecipeSlotRichTooltipCallback;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.library.gui.ingredients.ICycler;
import mezz.jei.library.gui.ingredients.RecipeSlot;
import mezz.jei.library.gui.ingredients.RecipeSlotIngredients;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@Mixin(value = RecipeSlot.class, remap = false)
public interface RecipeSlotAccessor {

    @Accessor("role")
    @Mutable
    void modularui$setRole(RecipeIngredientRole role);

    @Accessor("cycler")
    @Mutable
    void modularui$setCycler(ICycler cycler);

    @Accessor("tooltipCallbacks")
    List<IRecipeSlotRichTooltipCallback> modularui$getTooltipCallbacks();

    // JEI 19.64 keeps a slot's all and displayed ingredients together in one RecipeSlotIngredients
    @Accessor("ingredients")
    @Mutable
    void modularui$setIngredients(RecipeSlotIngredients ingredients);

    // clears the slot's caches of its ingredients (visible candidates, tooltip, tag)
    @Invoker("onDisplayOverridesChanged")
    void modularui$onIngredientsChanged();
}
