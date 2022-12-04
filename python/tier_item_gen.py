# --- coding: utf-8 ---

# Author:Biggest_Xuan

import json

# 输入
tier_item = input("Input Tier Name:")

modid = "emcworld"

# 所有类型
tier = ["wooden", "stone", "iron", "golden", "diamond", "netherite"]


# 获取json名字
def get_json_name(name):
    return name + ".json"


# 获取生成物品名字
def get_name(name, name1):
    return name + "_" + name1


# 获取json
def get_json(name):
    return {
        "parent": "minecraft:item/handheld",
        "textures": {
            "layer0": modid + ":item/" + name
        }
    }


if __name__ == '__main__':
    all = []
    for i in tier:
        name = get_name(i, tier_item)
        all.append("item.emcworld." + name)
        # 生成json
        json.dump(get_json(name), open(get_json_name(name), "w"), indent=4)

    name_obj = {}

    for i in range(6):
        key = all[i]
        name_obj[key] = ""
    json.dump(name_obj, open("lang.json", "w"), indent=4, ensure_ascii=False)
