package EMCWorldConfusion.scripts

import EMCWorldConfusion.confusion.MappingType
import EMCWorldConfusion.utils.getRandomMethodName
import java.nio.file.Path

/*
*  EMC WORLD MOD
*  @Author Biggest_Xuan
*  2023/01/09
*/ 

class ZSFunction(ctx:String, filePath: Path):ZSContent(){
    var permission = ""
    var name = ""
    var args = ArrayList<ZSArg>()
    var fields = ArrayList<ZSArg>()
    val path = filePath

    init{
        if(ctx.contains("function")){
            val fIndex = ctx.indexOf(" f")
            permission = ctx.substring(0,fIndex)
            val start = permission.length + 10
            val end = ctx.indexOf("(",start+1)
            name = ctx.substring(start,end)
            args = getArgs(ctx,this)
        }
    }

    override fun getType():MappingType{
        return MappingType.FUNCTION
    }

    override fun getContentName(): String {
        return name
    }

    override fun toString(): String {
        return "$name();${getRandomMethodName()}"
    }

    fun hasArg() : Boolean{
        return args.size != 0
    }
}