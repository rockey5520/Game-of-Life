import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;


class Operator {

  public static UnaryOperator<List<String>> unaryOperator = x -> {
    if (x.size() == 0) {
      return new ArrayList<>();
    } else {
      return x.stream().distinct().collect(Collectors.toList());
    }

  };
}