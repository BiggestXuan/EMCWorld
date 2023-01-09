
package EMCWorldConfusion.file

import EMCWorldConfusion.scripts.ZSFunction
import java.io.File


/*
*  EMC WORLD MOD
*  @Author Biggest_Xuan
*  2023/01/09
*/

fun isFunctionHead(c:String):Boolean{
    return c.contains("private function ")||c.contains("public function ");
}

fun isFunctionTail(c:String):Boolean{
    return c == "}"
}

fun getAllFunctions(f : File): ArrayList<ZSFunction> {
    val lines = f.readLines()
    var i = 0
    val functions = ArrayList<String>()
    val all = ArrayList<ZSFunction>()
    while (true){
        if(isFunctionHead(lines.get(i))){
            var count = 0
            while (true){
                if(isFunctionTail(lines.get(i+count))){
                    var string = ""
                    for(a in i..count+i){
                        string += lines.get(a)
                    }
                    functions.add(string)
                    break
                }
                count++
            }
        }
        i++
        if(i >= lines.size) break
    }
    for(fc in functions){
        all.add(ZSFunction(fc,f.toPath()))
    }
    return all
}
