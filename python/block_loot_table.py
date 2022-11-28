# --- coding: utf-8 ---

# Author:Biggest_Xuan

import json

block_name = input("Input block name:")

if(__name__ == "__main__"):
    json_obj = {
    "type": "minecraft:block",
    "pools": [
        {
            "rolls": 1,
            "entries": [
                {
                "type": "minecraft:item",
                "name": "emcworld:"+block_name
                }
            ]
        }
    ]}
    json.dump(json_obj,open(block_name+".json","w"),indent=4)