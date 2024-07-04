package dev.pedrohb.cowcannon.commands;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mineacademy.fo.ReflectionUtil;
import org.mineacademy.fo.Valid;
import org.mineacademy.fo.command.SimpleCommand;

import dev.pedrohb.cowcannon.commands.annotations.Parameter;

public class AnnotatedCommand extends SimpleCommand {

  private final Map<String, Method> methods = new HashMap<>();

  protected AnnotatedCommand(String label) {
    super(label);

    this.findMethods();
  }

  private void findMethods() {
    for (Method method : this.getClass().getDeclaredMethods()) {
      if (method.isAnnotationPresent(Parameter.class)) {
        Parameter parameter = method.getDeclaredAnnotation(Parameter.class);
        int modifiers = method.getModifiers();

        Valid.checkBoolean(!Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers), "Method " + method.getName()
            + " in class " + this.getClass().getSimpleName() + " must be public and non-static.");

        this.methods.put(parameter.value(), method);
      }
    }
  }

  @Override
  protected final void onCommand() {
    String param = this.args.length > 0 ? this.args[0] : "";
    Method method = this.methods.get(param.toLowerCase());

    if (method != null) {
      ReflectionUtil.invoke(method, this);
    } else {
      this.tell("Usage: /{label} <"
          + String.join("|",
              this.methods.keySet().stream().filter(methodName -> !methodName.isEmpty()).collect(Collectors.toList()))
          + ">");
    }
  }

  @Override
  protected final List<String> tabComplete() {
    if (this.args.length == 1) {
      return this.completeLastWord(this.methods.keySet());
    }

    return NO_COMPLETE;
  }
}
