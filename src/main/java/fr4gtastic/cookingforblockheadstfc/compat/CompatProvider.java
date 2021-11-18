package fr4gtastic.cookingforblockheadstfc.compat;

import net.blay09.mods.cookingforblockheads.api.CookingForBlockheadsAPI;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.objects.entity.animal.EntityCowTFC;
import net.dries007.tfc.objects.items.ItemsTFC;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class CompatProvider {

    public void loadCompat() {
        addCowEntity();
        addKnivesAsTools();
        addHeatRecipesToOven();
        addWaterItems();
        addMilkItems();
    }

    private void addCowEntity() {
        CookingForBlockheadsAPI.addCowClass(EntityCowTFC.class);
    }

    private void addKnivesAsTools() {
        OreDictionary.getOres("knife").forEach(CookingForBlockheadsAPI::addToolItem);
    }

    private void addHeatRecipesToOven() {
        TFCRegistries.HEAT
            .getValuesCollection()
            .stream()
            .filter(recipe -> recipe.getOutputs()
                .stream()
                .anyMatch(item -> item.getItem() instanceof ItemFood))
            .forEach(recipe -> {
                List<ItemStack> inputs = recipe.getIngredients().get(0).getValidIngredients();
                ItemStack output = recipe.getOutputs().get(0);
                inputs.forEach(input -> CookingForBlockheadsAPI.addOvenRecipe(input, output));
            });
    }

    private void addWaterItems() {
        String freshWater = "fresh_water";
        CookingForBlockheadsAPI.addWaterItem(getFluidContainer(ItemsTFC.WOODEN_BUCKET, freshWater));
        CookingForBlockheadsAPI.addWaterItem(getFluidContainer(ItemsTFC.FIRED_JUG, freshWater));
        CookingForBlockheadsAPI.addWaterItem(getFluidContainer(ItemsTFC.BLUE_STEEL_BUCKET, freshWater));
        CookingForBlockheadsAPI.addWaterItem(getFluidContainer(ItemsTFC.RED_STEEL_BUCKET, freshWater));
    }

    private void addMilkItems() {
        String milk = "milk";
        CookingForBlockheadsAPI.addMilkItem(getFluidContainer(ItemsTFC.WOODEN_BUCKET, milk));
        CookingForBlockheadsAPI.addMilkItem(getFluidContainer(ItemsTFC.FIRED_JUG, milk));
        CookingForBlockheadsAPI.addMilkItem(getFluidContainer(ItemsTFC.BLUE_STEEL_BUCKET, milk));
        CookingForBlockheadsAPI.addMilkItem(getFluidContainer(ItemsTFC.RED_STEEL_BUCKET, milk));
    }

    private ItemStack getFluidContainer(Item item, String fluid) {
        ItemStack itemStack = new ItemStack(item);
        itemStack.setTagCompound(getFluidTag(fluid));
        return itemStack;
    }

    private NBTTagCompound getFluidTag(String fluid) {
        NBTTagCompound tag = new NBTTagCompound();
        NBTTagCompound subTag = new NBTTagCompound();
        subTag.setString("FluidName", fluid);
        subTag.setInteger("Amount", 1000);
        tag.setTag("Fluid", subTag);
        return tag;
    }

}
