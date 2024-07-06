package biggestxuan.emcworld.common.utils;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2023/03/03
 */

import net.minecraft.entity.player.PlayerEntity;

import javax.annotation.Nonnull;
import java.util.UUID;

public class BirthdayUtils {
    private final String name;
    private final UUID uuid;
    private static final CalendarUtils utils = CalendarUtils.INSTANCE;

    public BirthdayUtils(@Nonnull PlayerEntity player){
        this.name = player.getScoreboardName();
        this.uuid = player.getUUID();
    }

    public boolean HappyBirthday(){
        for(Birthday b : Birthday.values()){
            if(utils.month == b.month && utils.day == b.day && name.equals(b.name) && uuid.equals(b.uuid)){
                return true;
            }
        }
        return false;
    }

    private enum Birthday{
        BIGGEST_XUAN("Biggest_Xuan","29328b6c-6f03-4fba-9436-678b696e8aeb",1,26),
        CSZXYMZX("cszxymzx","e99f4af7-c050-4552-9627-6f80b5f0a0d3",10,26),
        DCTOR_0415("Dctor_0415","4b2a8226-3c3a-4d3d-a26d-68b577ae1463",11,21),
        SDXHOP("sdxhop","4f25e2d3-2cd4-45ce-b83d-8a965fe2137b",12,25),
        FUN_WAYSQWQ("Fun_waysQWQ","2b36b3dc-4aaf-44e0-b7fd-96cc61545bfd",4,14),
        UNIECHO("uniEcho","1cb6a52c-8a39-47ac-9618-959ee4d88fca",5,18),
        XIANGSHUSHUMIAO("xiangshushumiao","19cd7e09-e249-4b92-b35a-770b3399a302",10,14),
        LAMB_KISARA("LAMB_Kisara","92a115f6-ce06-4c09-be16-0e9521148f42",9,14),
        ONE_WAN("One_Wan","50f1a6b0-a176-4102-a4bd-86ed2277b4bb",9,28),
        YULUO_1("yuluo_1","1719c509-2b62-47a4-a080-bef535fa1e68",8,19),
        CHARA_SS("Chara_SS","1738cb1b-ea69-4e0f-8678-688aea7e8d1b",8,28),
        TULYE("Tulye","52a0f9c3-5551-4de9-bfe5-16f803f44633",9,8),
        MCYUNXI("MCyunxi","eb91acd8-a70e-4b1d-b1c4-34fc4c8af495",3,28),
        DYTLJ7788("dytlj7788","e690ea7a-8fce-4049-80dd-07158cd6a348",9,2),
        ABUNANA("Abunana","6105dab8-94a6-440f-b797-00d046587eda",1,29),
        ;
        private final String name;
        private final UUID uuid;
        private final int month;
        private final int day;

        Birthday(String name,String uuid,int month,int day){
            this.name = name;
            this.uuid = UUID.fromString(uuid);
            this.month = month;
            this.day = day;
        }
    }
}
