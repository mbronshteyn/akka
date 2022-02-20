import akka.actor.typed.ActorSystem;
import behavior.ManagerBehavior;

import java.util.Calendar;

public class PrimeMain {
  public static void main(String[] args) {
    ActorSystem<ManagerBehavior.Command> actorSystem =
        ActorSystem.create(ManagerBehavior.create(), "ManagerActorSystem");
    long start = Calendar.getInstance().getTimeInMillis();
    actorSystem.tell(new ManagerBehavior.InstructionCommand("start") {});
    long finish = Calendar.getInstance().getTimeInMillis();
    System.out.println("Took: "+ (finish - start));
  }
}
