package behrad.cn1

/**
 * Created by behrad on 10/28/14.
 */

import akka.cluster.Cluster
import akka.cluster.ClusterEvent._
import akka.actor.{ActorPath, ActorLogging, Actor}
import com.typesafe.config.ConfigFactory

class ClusterListener extends Actor with ActorLogging {

  val config = ConfigFactory.load()

  val cluster = Cluster(context.system)

  override def preStart(): Unit = {
    cluster.subscribe(self, initialStateMode = InitialStateAsEvents, classOf[MemberEvent], classOf[UnreachableMember])
  }

  override def postStop(): Unit = cluster.unsubscribe(self)


  def receive = {
    case MemberUp(member) =>
      log.info("------------------------\nMember is Up: {}", member.address)

    case UnreachableMember(member) =>
      log.info("------------------------\nMember detected as unreachable: {}", member)

    case MemberRemoved(member, previousStatus) =>
      log.info("------------------------\nMember is Removed: {} after {}", member.address, previousStatus)

    case event: MemberEvent =>
      log.info("------------------------\nMember Event {}", event)
  }
}
