#priority 82
import crafttweaker.api.item.IItemStack;

public expand IItemStack{
    public withLevel(l as int) as IItemStack => this.withTag({level: l as int});

    public withMax() as IItemStack => this.withTag({level: 30 as int,prefix: 10 as int,star:8 as int,max_star:8 as int});

    public withArmorLevelMax() as IItemStack => this.withTag({level: 12 as int});
}