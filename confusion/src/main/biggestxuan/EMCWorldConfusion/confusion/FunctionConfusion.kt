package EMCWorldConfusion.confusion

import EMCWorldConfusion.file.getAllFiles
import EMCWorldConfusion.file.isFunctionHead


/*
*  EMC WORLD MOD
*  @Author Biggest_Xuan
*  2023/01/09
*/

fun FunctionConfusion(){
    val files = getAllFiles()
    for(f in files){
        val con_file = ArrayList<String>()
        val lines = f.readLines()
        val isInFunction = false
        var i = 0
        //todo
    }
}