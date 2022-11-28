package biggestxuan.emcworld.common.skill;

/**
 *  EMC WORLD MOD
 *  @Author Biggest_Xuan
 *  2022/09/05
 */

import biggestxuan.emcworld.common.capability.EMCWorldCapability;
import biggestxuan.emcworld.api.capability.IPlayerSkillCapability;
import biggestxuan.emcworld.common.items.ProfessionalItem.AddMaxLevelItem;
import net.minecraft.entity.player.PlayerEntity;

public class PlayerSkillModify {
    public static IPlayerSkillCapability getCap(PlayerEntity player){
        return player.getCapability(EMCWorldCapability.PLAYER_LEVEL).orElseThrow(NullPointerException::new);
    }
    public static void skill0(PlayerEntity player,int i1){
        getCap(player).setSkills(0,i1);
    }
    public static void skill1(PlayerEntity player,int i1,int i2){
        getCap(player).setSkills(4,i1);
        getCap(player).setSkills(5,i2);
    }
    public static void skill2(PlayerEntity player,int i1){
        getCap(player).setSkills(8,i1);
    }
    public static void skill3(PlayerEntity player,int i1){
        getCap(player).setSkills(12,i1);
    }
    public static void skill4(PlayerEntity player,int i1,int i2){
        getCap(player).setSkills(16,i1);
        getCap(player).setSkills(17,i2);
    }
    public static void skill5(PlayerEntity player,int i1){
        getCap(player).setSkills(20,i1);
    }
    public static void skill6(PlayerEntity player,int i1){
        getCap(player).setSkills(24,i1);
    }
    public static void skill7(PlayerEntity player,int i1){
        getCap(player).setSkills(28,i1);
    }
    public static void skill8(PlayerEntity player,int i1,int i2,int i3,int i4){
        getCap(player).setSkills(32,i1);
        getCap(player).setSkills(33,i2);
        getCap(player).setSkills(34,i3);
        getCap(player).setSkills(35,i4);
    }
    public static void skill9(PlayerEntity player,int i1,int i2){
        getCap(player).setSkills(36,i1);
        getCap(player).setSkills(37,i2);
    }
    public static void skill10(PlayerEntity player,int i1,int i2){
        getCap(player).setSkills(40,i1);
        getCap(player).setSkills(41,i2);
    }

    //default
    public static void default1_skill0(PlayerEntity player){
        skill0(player,30);
    }
    public static void default1_skill1(PlayerEntity player){
        skill1(player,100000,3000000);
    }
    public static void default1_skill2(PlayerEntity player){
        skill2(player,300);
    }
    public static void default1_skill3(PlayerEntity player){
        skill3(player,1000);
    }
    public static void default1_skill4(PlayerEntity player){
        skill4(player,300000,6000000);
    }
    public static void default1_skill5(PlayerEntity player){
        skill5(player,1000);
    }
    public static void default1_skill6(PlayerEntity player){
        skill6(player,500);
    }
    public static void default1_skill7(PlayerEntity player){
        skill7(player,50000);
    }
    public static void default1_skill8_modify1(PlayerEntity player){
        skill8(player,600000,5000,1500,10000000);
    }
    public static void default1_skill9_modify1(PlayerEntity player){
        skill9(player,1000,500);
    }
    public static void default1_skill10_modify1(PlayerEntity player){
        skill10(player,1,1);
    }
    public static void default1_skill8_modify2(PlayerEntity player){
        skill8(player,600000,2000,5000000,1);
    }
    public static void default1_skill9_modify2(PlayerEntity player){
        skill9(player,500,1);
    }
    public static void default1_skill10_modify2(PlayerEntity player){
        skill10(player,1,1);
    }

    public static void default2_skill0(PlayerEntity player){
        skill0(player,20);
    }
    public static void default2_skill1(PlayerEntity player){
        skill1(player,300000,2000000);
    }
    public static void default2_skill2(PlayerEntity player){
        skill2(player,200);
    }
    public static void default2_skill3(PlayerEntity player){
        skill3(player,1000);
    }
    public static void default2_skill4(PlayerEntity player){
        skill4(player,300000,2000000);
    }
    public static void default2_skill5(PlayerEntity player){
        skill5(player,1000);
    }
    public static void default2_skill6(PlayerEntity player){
        skill6(player,500);
    }
    public static void default2_skill7(PlayerEntity player){
        skill7(player,30000);
    }
    public static void default2_skill8_modify1(PlayerEntity player){
        skill8(player,200000,5000,100000,10000000);
    }
    public static void default2_skill9_modify1(PlayerEntity player){
        skill9(player,500,1);
    }
    public static void default2_skill10_modify1(PlayerEntity player){
        skill10(player,100000,20000000);
    }
    public static void default2_skill8_modify2(PlayerEntity player){
        skill8(player,30000,20000,300000,6000000);
    }
    public static void default2_skill9_modify2(PlayerEntity player){
        skill9(player,1500,1);
    }
    public static void default2_skill10_modify2(PlayerEntity player){
        skill10(player,1000,1);
    }

    public static void default3_skill0(PlayerEntity player){
        skill0(player,15);
    }
    public static void default3_skill1(PlayerEntity player){
        skill1(player,200000,1000000);
    }
    public static void default3_skill2(PlayerEntity player){
        skill2(player,35);
    }
    public static void default3_skill3(PlayerEntity player){
        skill3(player,30000);
    }
    public static void default3_skill4(PlayerEntity player){
        skill4(player,300000,3000000);
    }
    public static void default3_skill5(PlayerEntity player){
        skill5(player,1000);
    }
    public static void default3_skill6(PlayerEntity player){
        skill6(player,500);
    }
    public static void default3_skill7(PlayerEntity player){
        skill7(player,3000);
    }
    public static void default3_skill8_modify1(PlayerEntity player){
        skill8(player,100000,2000,10000000,0);
    }
    public static void default3_skill9_modify1(PlayerEntity player){
        skill9(player,2000,1);
    }
    public static void default3_skill10_modify1(PlayerEntity player){
        skill10(player,1,1);
    }
    public static void default3_skill8_modify2(PlayerEntity player){
        skill8(player,100000,100000,10000000,0);
    }
    public static void default3_skill9_modify2(PlayerEntity player){
        skill9(player,3500,1);
    }
    public static void default3_skill10_modify2(PlayerEntity player){
        skill10(player,1,1);
    }


    public static void makePlayerGetDefaultSkill(PlayerEntity player,AddMaxLevelItem item){
        makePlayerGetDefaultSkill(player,item.getMaxLevel(), item.getModify());
    }

    public static void makePlayerGetDefaultSkill(PlayerEntity player , int level ,int modify){
        IPlayerSkillCapability cap = player.getCapability(EMCWorldCapability.PLAYER_LEVEL).orElseThrow(NullPointerException::new);
        if(cap.getProfession() == 1){
            switch (level){
                case 20:
                    default1_skill1(player);
                    break;
                case 30:
                    default1_skill2(player);
                    break;
                case 40:
                    default1_skill3(player);
                    break;
                case 50:
                    default1_skill4(player);
                    break;
                case 60:
                    default1_skill5(player);
                    break;
                case 70:
                    default1_skill6(player);
                    break;
                case 80:
                    default1_skill7(player);
                    break;
            }
            if(modify == 1 && level == 90){
                default1_skill8_modify1(player);
            }
            if(modify == 2 && level == 90){
                default1_skill8_modify2(player);
            }
            if(level == 100){
                if(cap.getModify() == 1){
                    default1_skill9_modify1(player);
                }
                if(cap.getModify() == 2){
                    default1_skill9_modify2(player);
                }
            }
            if(level == 110){
                if(cap.getModify() == 1){
                    default1_skill10_modify1(player);
                }
                if(cap.getModify() == 2){
                    default1_skill10_modify2(player);
                }
            }
        }
        if(cap.getProfession() == 2){
            switch (level){
                case 20:
                    default2_skill1(player);
                    break;
                case 30:
                    default2_skill2(player);
                    break;
                case 40:
                    default2_skill3(player);
                    break;
                case 50:
                    default2_skill4(player);
                    break;
                case 60:
                    default2_skill5(player);
                    break;
                case 70:
                    default2_skill6(player);
                    break;
                case 80:
                    default2_skill7(player);
                    break;
            }
            if(modify == 1 && level == 90){
                default2_skill8_modify1(player);
            }
            if(modify == 2 && level == 90){
                default2_skill8_modify2(player);
            }
            if(level == 100){
                if(cap.getModify() == 1){
                    default2_skill9_modify1(player);
                }
                if(cap.getModify() == 2){
                    default2_skill9_modify2(player);
                }
            }
            if(level == 110){
                if(cap.getModify() == 1){
                    default2_skill10_modify1(player);
                }
                if(cap.getModify() == 2){
                    default2_skill10_modify2(player);
                }
            }
        }
        if(cap.getProfession() == 3){
            switch (level){
                case 20:
                    default3_skill1(player);
                    break;
                case 30:
                    default3_skill2(player);
                    break;
                case 40:
                    default3_skill3(player);
                    break;
                case 50:
                    default3_skill4(player);
                    break;
                case 60:
                    default3_skill5(player);
                    break;
                case 70:
                    default3_skill6(player);
                    break;
                case 80:
                    default3_skill7(player);
                    break;
            }
            if(modify == 1 && level == 90){
                default3_skill8_modify1(player);
            }
            if(modify == 2 && level == 90){
                default3_skill8_modify2(player);
            }
            if(level == 100){
                if(cap.getModify() == 1){
                    default3_skill9_modify1(player);
                }
                if(cap.getModify() == 2){
                    default3_skill9_modify2(player);
                }
            }
            if(level == 110){
                if(cap.getModify() == 1){
                    default3_skill10_modify1(player);
                }
                if(cap.getModify() == 2){
                    default3_skill10_modify2(player);
                }
            }
        }
    }

    public static void EMCWorldDEBUG(PlayerEntity player){
        default1_skill0(player);
        default1_skill1(player);
        default1_skill2(player);
        default1_skill3(player);
        default1_skill4(player);
        default1_skill5(player);
        default1_skill6(player);
        default1_skill7(player);
    }
    public static void EMCWorldDEBUG1(PlayerEntity player){
        default2_skill0(player);
        default2_skill1(player);
        default2_skill2(player);
        default2_skill3(player);
        default2_skill4(player);
        default2_skill5(player);
        default2_skill6(player);
        default2_skill7(player);
        default2_skill8_modify1(player);
        default2_skill9_modify1(player);
        default2_skill10_modify1(player);
    }
}
