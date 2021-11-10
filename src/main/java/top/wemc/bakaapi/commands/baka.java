package top.wemc.bakaapi.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import top.wemc.bakaapi.Main;

/**
 * @author WYH2004
 * @date 2021/11/10
 **/
public class baka implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 0){
            commandSender.sendMessage("======== { BakaAPI } ========");
            commandSender.sendMessage("API信息: BakaAPI");
            commandSender.sendMessage("API版本: null");
            commandSender.sendMessage("插件作者: WYH2004");
        }
        if (strings.length == 1){
            if (strings[0].equals("reload")){
                if (commandSender.hasPermission("baka.admin.core.reload")){
                    Main.reloadConfigs();
                    commandSender.sendMessage("[BakaAPI] 配置文件已重载");
                }
            }
        }

        return false;
    }
}
