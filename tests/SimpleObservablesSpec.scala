import org.specs2.mutable._
import rx.lang.scala.Observable
import scala.collection.mutable.ListBuffer

class SimpleObservablesSpec extends SpecificationWithJUnit {

  val obs = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

  "When listening to a live stream" should {
    val temp = ListBuffer[Int]()
    obs.doOnNext(i => temp += i)
       .subscribe()
    "consume all items emitted" >> {
      temp.size === 10
    }
  }

  "When listening to a live stream" should {
    val totalEvens = obs.filter(_ % 2 == 0)
                        .longCount
                        .sum
                        .toBlocking
                        .head
    "get correct count of evens" >> {
      totalEvens === 5
    }
  }

}
