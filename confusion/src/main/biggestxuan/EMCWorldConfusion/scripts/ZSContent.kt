package EMCWorldConfusion.scripts

import EMCWorldConfusion.confusion.MappingType


/*
*  EMC WORLD MOD
*  @Author Biggest_Xuan
*  2023/01/09
*/

abstract class ZSContent() {
    abstract fun getType() : MappingType

    abstract fun getContentName() : String
}