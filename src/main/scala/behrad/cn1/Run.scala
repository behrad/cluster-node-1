package behrad.cn1

import akka.cluster.Cluster
import akka.kernel.Bootable
import akka.actor._
import com.typesafe.config.ConfigFactory

/**
 * Created by behrad on 9/13/14.
 */
class Run extends Bootable {

  var system :ActorSystem = null

  def startup = {
    println( "Starting up Akka system" )
    val config = ConfigFactory.load()
    system = ActorSystem( "akka-cluster-reconnect-test" )
    system.actorOf( Props[ClusterListener] )
  }

  def shutdown = {
    println( "Shutting down Akka system" )
    val cluster = Cluster(system)
    cluster.down(cluster.selfAddress)
    cluster.leave(cluster.selfAddress)
//    system.shutdown()
  }

}

object Run extends App {

  override def main(args: Array[String]): Unit = {
    val run = new Run
    run startup()
    sys.ShutdownHookThread {
      println( "++++++++++++++++++++++++++++++++ Exiting" )
      run shutdown()
    }
  }

}