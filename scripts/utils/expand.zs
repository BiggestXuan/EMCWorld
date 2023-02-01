#priority 82
import crafttweaker.api.item.IItemStack;

public expand IItemStack{
    public withLevel(l as int) as IItemStack => this.withTag({level: l as int});

    public withMax() as IItemStack => this.withTag({level: 24 as int,prefix: 10 as int});
}