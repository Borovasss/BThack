package com.ferra13671.bthack;

import com.mojang.brigadier.context.CommandContext;
import java.util.Collection;
import net.minecraft.util.Formatting;

@CommandInfo(aliases = "module")
public class ModuleCommand extends CommandBase {
   private static Collection getExamples() {
      return KeyboardEnum.Mouse.getKeyNames().values();
   }

   @Override
   public void build(Helper5<Object> helper5) {
      helper5.then(
         argument("module", CommandArgumentBaseUtils.commandArgumentBase9)
            .then2(
               of("set")
                  .then2(
                     of("enabled").then(argument("enabled", CommandArgumentBaseUtils.commandArgumentBase1).executes(ModuleCommand::run)),
                     of("bind")
                        .then2(
                           of("keyboard")
                              .withHelper5(
                                 argument("key", CommandArgumentBaseUtils.withSupplier1(ModuleCommand::getExamples2)),
                                 argument("type", CommandArgumentBaseUtils.withClass5(ToggleBody.class)).executes(this::execute3)
                              ),
                           of("mouse")
                              .withHelper5(
                                 argument("key", CommandArgumentBaseUtils.withSupplier1(ModuleCommand::getExamples)),
                                 argument("type", CommandArgumentBaseUtils.withClass5(ToggleBody.class)).executes(this::handleCommandContext)
                              )
                        )
                  )
            )
      );
   }

   private void handleToggleable(Toggleable toggleable, String string, Helper36 helper36, ToggleBody toggleBody) {
      toggleable.getModuleKeybind().setValue2(helper36);
      toggleable.getModuleKeybind2().setValue2(toggleBody);
      error(
         String.format(
            "updated bind in module '%s' to '%s' with type '%s'",
            Formatting.AQUA + toggleable.getName() + Formatting.RESET,
            Formatting.AQUA + string + Formatting.RESET,
            Formatting.AQUA + toggleBody.name() + Formatting.RESET
         )
      );
   }

   private static Collection getExamples2() {
      return KeyboardEnum.Keyboard.getKeyNames().values();
   }

   private void handleCommandContext(CommandContext commandContext) {
      String string = (String)commandContext.getArgument("key", String.class);
      this.handleToggleable(
         (Toggleable)commandContext.getArgument("module", Toggleable.class),
         string,
         new Helper36(KeyboardEnum.Mouse.getKeyValue(string), KeyboardEnum.Mouse),
         (ToggleBody)commandContext.getArgument("type", ToggleBody.class)
      );
   }

   private void execute3(CommandContext commandContext) {
      String string = (String)commandContext.getArgument("key", String.class);
      this.handleToggleable(
         (Toggleable)commandContext.getArgument("module", Toggleable.class),
         string,
         new Helper36(KeyboardEnum.Keyboard.getKeyValue(string), KeyboardEnum.Keyboard),
         (ToggleBody)commandContext.getArgument("type", ToggleBody.class)
      );
   }

   private static void run(CommandContext commandContext) {
      ((Api4)commandContext.getArgument("module", Api4.class)).setEnabled((Boolean)commandContext.getArgument("enabled", Boolean.class), false);
   }
}
