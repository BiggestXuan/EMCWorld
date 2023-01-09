package EMCWorldConfusion.scripts

import EMCWorldConfusion.confusion.MappingType
import EMCWorldConfusion.utils.getRandomArgName
import EMCWorldConfusion.utils.getStringCount
import EMCWorldConfusion.utils.removeHeadBlank


/*
*  EMC WORLD MOD
*  @Author Biggest_Xuan
*  2023/01/09
*/

class ZSArg(ctx:String,function:ZSFunction):ZSContent() {
    var name = ""
    val func = function

    init {
        name = ctx
    }

    override fun getType(): MappingType {
        return MappingType.ARGS
    }

    override fun getContentName(): String {
        return name
    }

    override fun toString(): String {
        return "${func.name}_$name;${getRandomArgName()}"
    }
}

fun getArgs(ctx: String,function: ZSFunction):ArrayList<ZSArg>{
    val args = ArrayList<ZSArg>()
    for(i in getArgsName(ctx)){
        args.add(ZSArg(i,function))
    }
    return args
}

fun getArgsName(ctx:String) : ArrayList<String>{
    val fields = ArrayList<String>()
    val start = ctx.indexOf("(")+1
    val end = ctx.indexOf(")")
    val act = ctx.substring(start,end)
    if(act.length < 3){
        return fields
    }
    var i = 0
    var start1 = 0
    var point = 0
    while(i < getStringCount(act," as ")){
        val ind = act.indexOf(" as ",point)
        fields.add(removeHeadBlank(act.substring(start1,ind)))
        i++
        start1 = act.indexOf(",",point)+1
        point = start1
    }
    return fields
}