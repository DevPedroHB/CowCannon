package dev.pedrohb.cowcannon.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import dev.pedrohb.cowcannon.utils.Common;
import lombok.Getter;

public final class MessageBroadcasterTask implements Runnable {

  @Getter
  private static final MessageBroadcasterTask instance = new MessageBroadcasterTask();

  private List<String> messages = new ArrayList<>();
  private AtomicInteger index;

  private MessageBroadcasterTask() {
    this.messages.add("&#cc44ffHello World.");
    this.messages.add("&cTestando as mensagens automaticas.");
    this.messages.add("&6Bem-vindo ao servidor de Survival!");
    this.messages.add("&eNão esqueça de se registrar em nosso site!");
    this.messages.add("&aVocê pode encontrar recursos úteis em nosso fórum.");
    this.messages.add("&bAtenção: não é permitido x-raying ou hacks!");
    this.messages.add("&dVocê precisa de ajuda? Peça ao staff!");
    this.messages.add("&fNão se esqueça de votar em nosso servidor diariamente!");
    this.messages.add("&9Você pode comprar itens exclusivos em nossa loja!");
    this.messages.add("&7Boa sorte em sua aventura!");
    this.messages.add("&1Lembre-se de respeitar os outros jogadores!");
  }

  @Override
  public void run() {
    String message = getNextMessage();

    broadcastMessage(message);
  }

  private String getNextMessage() {
    int currentIndex = index.getAndIncrement();

    if (currentIndex >= messages.size()) {
      index.set(0);
      currentIndex = 0;
    }

    return messages.get(currentIndex);
  }

  private void broadcastMessage(String message) {
    for (Player player : Bukkit.getOnlinePlayers()) {
      player.sendMessage(Common.colorize(message));
    }
  }
}
