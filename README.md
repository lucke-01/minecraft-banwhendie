This plugin allows you ban your players when they die for a configurable time.
It is usefull if you want to increase the difficulty of your server.

Example of configuracion:
#if is true the plugin will be actived and working as usually, if is set to false Replace all options and the plugin will be disabled
activateBanWhenDied: true

#S = seconds, M = minutes, H = hours, D = days
bantime: 1M

#if true the ban goes through ip user, else it will not go through ip
bannedByIp: true

#if true the ban goes through nickname user, else it will not go through nickname
bannedByNickname: true

#Only yml allow so far
databaseType: yml

#main lang in server side
lang: 'en_US'

#this may be usefull if you want your players does not lost their items during ban time
#however you can also increase despawn time in your server configuration adding it (bantime)
preventItemDespawn: false

#Not yet implement
#TODO: activateWhiteList and whiteList (with name of player that won't be affect by this plugin')