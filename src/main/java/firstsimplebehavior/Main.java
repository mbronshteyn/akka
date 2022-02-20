import akka.actor.typed.ActorSystem;
import behavior.FirstSimpleBehavior;

public class Main {
  public static void main(String[] args) {
    ActorSystem<String> actorSystem = ActorSystem.create(FirstSimpleBehavior.create(), "FirstActorSystem");
    actorSystem.tell("Hello, are you there?");
    actorSystem.tell("say hello");
    actorSystem.tell("create a child");
    actorSystem.tell("who are you");
  }
}
