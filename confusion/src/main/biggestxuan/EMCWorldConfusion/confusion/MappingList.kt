package EMCWorldConfusion.confusion

import EMCWorldConfusion.scripts.ZSContent
import EMCWorldConfusion.scripts.ZSFunction
import java.io.File


/*
*  EMC WORLD MOD
*  @Author Biggest_Xuan
*  2023/01/09
*/


val list = getAllMappingLine()

fun getMappingName(name : String):String{
    var i = 0
    while (i < list.size){
        val ctx = list.get(i)
        if(ctx.contains(name)){
            return ctx.substring(ctx.indexOf(";")+1)
        }
        i++
    }
    throw NoClassDefFoundError()
}

fun getMappingName(content : ZSContent):String{
    return getMappingName(content.getContentName())
}

fun getAllMappingLine():List<String>{
    val file = File("EMCWorldMapping.txt")
    return file.readLines()
}

enum class MappingType{
    FUNCTION,ARGS,FIELD,CLASS,INSTANCE
}