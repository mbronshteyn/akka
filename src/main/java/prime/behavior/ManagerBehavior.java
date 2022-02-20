package prime.behavior;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.SortedSet;
import java.util.TreeSet;

public class ManagerBehavior extends AbstractBehavior<ManagerBehavior.Command> {

  public interface Command extends Serializable {}

  public static class InstructionCommand implements Command {
    private static final long serialVersionUID = 1L;
    private String message;

    public InstructionCommand(String message) {
      this.message = message;
    }

    public String getMessage() {
      return this.message;
    }
  }

  public static class ResultCommand implements Command {
    private static final long serialVersionUID = 1L;
    private BigInteger prime;

    public ResultCommand(BigInteger prime) {
      this.prime = prime;
    }

    public BigInteger getPrime() {
      return this.prime;
    }
  }

  private ManagerBehavior(ActorContext<Command> context) {
    super(context);
  }

  public static Behavior<Command> create() {
    return Behaviors.setup(ManagerBehavior::new);
  }

  private SortedSet<BigInteger> primes = new TreeSet<>();

  @Override
  public Receive<Command> createReceive() {
    return newReceiveBuilder()
        .onMessage(
            InstructionCommand.class,
            command -> {
              if (command.getMessage().equals("start")) {
                for (int i = 0; i < 20; i++) {
                  ActorRef<WorkerBehavior.Command> workerActor =
                          getContext().spawn(WorkerBehavior.create(), "workerActor" + i);
                  workerActor.tell(new WorkerBehavior.Command("start", getContext().getSelf()));
                }
              }
              return this;
            })
        .onMessage(
            ResultCommand.class,
                command -> {
              primes.add(command.getPrime());
              if (primes.size() == 20) {
                primes.forEach(System.out::println);
              }
              return this;
            })
        .build();
  }
}
