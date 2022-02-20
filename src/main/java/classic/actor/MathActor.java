package actor;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

public class MathActor extends AbstractActor {
  @Override
  public Receive createReceive() {
    return ReceiveBuilder.create()
        .match(Integer.class, msg -> System.out.println(msg * msg))
        .match(Double.class, this::square)
        .build();
  }

  private void square(double d) {
    System.out.println(d * d);
  }

  public static Props props() {
    return Props.create(MathActor.class);
  }
}
