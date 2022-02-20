import actor.MathActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;

public class App {
  public static void main(String[] args) {
    System.out.println("App Started!");
    ActorSystem akkaSystem = ActorSystem.create("system");
    ActorRef mathActor = akkaSystem.actorOf(MathActor.props(), "mathActor");
    for (int i = 1; i < 5; i++) {
      mathActor.tell(i, ActorRef.noSender());
    }
  }
}
