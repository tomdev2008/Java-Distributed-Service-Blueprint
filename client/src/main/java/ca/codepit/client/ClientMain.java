package ca.codepit.client;

import java.net.InetAddress;
import java.net.UnknownHostException;

import ca.codepit.common.ClientAdminMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * This is the main client class, responsible for creating the Spring context and processing the admin server
 * messages.
 *
 * @author Evan Jehu
 *         Date: Mar 13, 2010
 */
public class ClientMain {

  // Constants ---------------------------------------------------------------------------------------------- Constants

  private transient static final Log log = LogFactory.getLog(ClientMain.class);

  private static ClassPathXmlApplicationContext ctx;

  // Instance Variables ---------------------------------------------------------------------------- Instance Variables

  /**
   * record the host of the client to use as its "id", if multiple hosts are to run on the same computer this
   * will obviously break down.
   */
  private String host;

  @Autowired
  private ClientToAdminMessageSender cams;

  // Constructors ---------------------------------------------------------------------------------------- Constructors

  public ClientMain() {

    try {
      host = InetAddress.getLocalHost().getHostName();
    } catch(UnknownHostException e) {
      host = "unknown";
      e.printStackTrace();
    }
  }

  // Public Methods ------------------------------------------------------------------------------------ Public Methods

  /**
   * Method mapped in the spring context to manage administrative messages such as PING or EXIT
   *
   * @param msg - the client admin message to be checked
   * @return the message is returned to the master server, if it was actioned successfully its success flag will be
   *          set to true.
   */
  public ClientAdminMessage processAdminMessage(ClientAdminMessage msg) {

    // always set the responding client asap
    msg.setRespondingClient(host);

    if(msg.isActionable(host)) {
      log.debug("responding to admin message: " + msg);
      switch(msg.getAction()) {
        case PING:
          // do nothing, just reply
          break;
        case EXIT:
          log.info("\t\t --- EXIT ---");
          ctx.close();
          break;
        default:
          return new ClientAdminMessage(msg.getMessageId(), ClientAdminMessage.ACTION.CLIENT_ERROR, "Unrecognized client command");
      }
      msg.setSuccess(true);
    }
    return msg;
  }

  // Protected Methods ------------------------------------------------------------------------------ Protected Methods


  // Private Methods ---------------------------------------------------------------------------------- Private Methods


  // main() method -------------------------------------------------------------------------------------- main() method


  public static void main(String[] args) throws InterruptedException {

    // open/read the application context file
    ctx = new ClassPathXmlApplicationContext("client-context.xml");
  }

  // Getters & Setters ------------------------------------------------------------------------------ Getters & Setters
}
