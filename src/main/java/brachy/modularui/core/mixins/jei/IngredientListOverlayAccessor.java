package brachy.modularui.core.mixins.jei;

import mezz.jei.gui.input.GuiTextFieldFilter;
import mezz.jei.gui.overlay.IngredientListOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

// JEI 19.64 has no SearchInputLayer#focus (added in JEI 19.70), so JeiScreenHandler focuses the search field itself
@Mixin(value = IngredientListOverlay.class, remap = false)
public interface IngredientListOverlayAccessor {

    @Accessor("searchField")
    GuiTextFieldFilter getSearchField();
}
