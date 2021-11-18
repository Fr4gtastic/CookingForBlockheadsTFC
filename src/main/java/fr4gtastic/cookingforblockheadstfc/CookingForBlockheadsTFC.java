package fr4gtastic.cookingforblockheadstfc;

import fr4gtastic.cookingforblockheadstfc.compat.CompatProvider;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = CookingForBlockheadsTFC.MOD_ID,
    name = CookingForBlockheadsTFC.MOD_NAME,
    dependencies = CookingForBlockheadsTFC.DEPENDENCIES)
public class CookingForBlockheadsTFC {

    public static final String MOD_ID = "cookingforblockheadstfc";
    public static final String MOD_NAME = "CookingForBlockheadsTFC";
    public static final String DEPENDENCIES = "required-after:forge@[14.23.5.2847,);required-after:tfc;required-after:cookingforblockheads";

    @Mod.Instance(MOD_ID)
    public static CookingForBlockheadsTFC INSTANCE;

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        CompatProvider compatProvider = new CompatProvider();
        compatProvider.loadCompat();
    }
}
