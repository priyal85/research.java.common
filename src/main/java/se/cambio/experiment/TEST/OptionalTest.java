package se.cambio.experiment.TEST;

import java.util.Optional;
import java.util.function.Function;

import com.google.common.primitives.Floats;
import com.google.common.primitives.Ints;

public class OptionalTest
{

  public static void main(String[] args)
  {
    fromNullableId(null).ifPresent(System.out::println);
    System.out.println(fromNullableId(new Id("10")).get());
  }

  private static <I> Optional<I> fromNullableId(Id<I> id)
  {
   // Optional<Float> max = Optional.ofNullable(Floats.tryParse(null)).ifPresent((f)->{});
    return Optional.ofNullable(id).map(new DtoIdValueFunction<I>());
  }

  public static class DtoIdValueFunction<T> implements Function<Id<T>, T>
  {

    public T apply(Id<T> t)
    {
      return t.getIdValue();
    }

  }

}

class Id<T>
{
  private T idValue;

  public Id(T idValue)
  {
    this.idValue = idValue;
  }

  public T getIdValue()
  {
    return idValue;
  }
}
