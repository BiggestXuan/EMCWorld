# --- coding: utf-8 ---

# Author:Biggest_Xuan

import json

# 输入流
ore_name = input("Input ore name:")
ore_cn_name = input("输入中文名称：")

# 全局变量
global modid
modid = "emcworld"

# 相关名字
ore_names = [
    "clump","crystal","dirty_dust","dust","shard"
]

# 一个小拼接
def get_name(prefix,name):
    return prefix+"_"+name

# 构建json文件
def get_json(name):
    return {
    "parent": "item/generated",
    "textures": {
    "layer0": modid+":item/"+name
  }
}

# 获取文件名
def get_json_name(name):
    return name+".json"

# 获取中文名
def gen_cn_name():
    n = ore_cn_name
    return [n+"碎块",n+"晶体","污浊"+n+"粉",n+"粉",n+"碎片"]

# 主方法
if(__name__ == '__main__'):
    names = []
    for x in ore_names:
        name = get_name(x,ore_name)
        names.append("item.emcworld."+name)
        json.dump(get_json(name),open(get_json_name(name),"w"),indent=4) # 输出models/item下的文件。

    name_obj = {}

    for i in range(5):
        key = names[i]
        name_obj[key] = gen_cn_name()[i]
    json.dump(name_obj,open("lang.json","w"),indent=4,ensure_ascii=False) #你需要使用GBK编码，然后再复制到utf-8的语言文件中。或者删除掉ensure后半段，但是会以\u输出。