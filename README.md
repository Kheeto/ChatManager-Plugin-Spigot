# ChatManager-Plugin-Spigot
A simple 1.16 plugin to manage the chat with cooldowns and blocking some words
https://www.spigotmc.org/resources/chatmanager.89071/

### Commands
| Command    | What does it do? |
| ------------- |-------------|
| /cmreload | Reloads the configuration |

### Permissions
| Permission name    | What does it do? |
| ------------- |-------------|
| cm.reload | Permission needed for the /cmreload command |
| cm.bypass.cooldown | Bypasses the chat cooldown |
| cm.bypass.blockedword | Allows a player to say a blocked word |

## Config
```
# Permissions:
# cm.bypass.cooldown - Bypass message cooldown
# cm.bypass.blacklist - Make a player able to write a blacklisted word in chat
# cm.reload - Permission to reload the configuration files

enableCooldown: true
blacklistWords: true

# Cooldown between messages
# An user needs the cm.bypass.cooldown permission to bypas the cooldown
Cooldown: 2
# Words that an user without the cm.bypass.blockedwords permission cannot send in chat
BlacklistedWords:
  - fuck
  - shit
Messages:
  Blacklisted: §6[ChatManager] §cDo not swear!
  NoPermission: §6[ChatManager] §cYou do not have the permission to do that
  Cooldown: §6[ChatManager] §cYou cannot do that for another
  Seconds: §cseconds
  Config: §6[ChatManager] §aConfiguration files reloaded
  SenderIsConsole: §6[ChatManager] §cOnly players can use that command

```
