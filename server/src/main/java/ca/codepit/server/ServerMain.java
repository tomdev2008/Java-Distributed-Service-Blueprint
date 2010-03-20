package ca.codepit.server;

import ca.codepit.common.AdministrationMessage;
import ca.codepit.common.WorkItemMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Evan Jehu
 *         Date: Mar 13, 2010
 */
public class ServerMain {

  // Constants ---------------------------------------------------------------------------------------------- Constants

  private transient static final Log log = LogFactory.getLog(ServerMain.class);

  private static ClassPathXmlApplicationContext ctx;

  // Instance Variables ---------------------------------------------------------------------------- Instance Variables

  @Autowired
  private WorkItemSender ws;

  @Autowired
  private ServerAdminMessageSender ams;

  // Constructors ---------------------------------------------------------------------------------------- Constructors

  public ServerMain() {
  }

  // Public Methods ------------------------------------------------------------------------------------ Public Methods

  public void receiveAdminMessage(AdministrationMessage msg) {

    log.info("admin response received: " + msg);
  }

  public void receiveWorkConfirmMessage(WorkItemMessage msg) {

    log.info("work item confirmation received: " + msg);
  }

  public void run() throws InterruptedException {

    log.info("starting server ...");

    int messageCount = 0;

    while(true) {
      Thread.sleep(500);
      messageCount++;
      ws.send(new WorkItemMessage("msg: " + messageCount));
      if(messageCount % 50 == 0) {
        ams.send(new AdministrationMessage("msg: " + messageCount, AdministrationMessage.ACTION.PING));
      }
      if(messageCount % 1000 == 0) {
        ams.send(new AdministrationMessage("msg: " + messageCount, AdministrationMessage.ACTION.EXIT));
      }
    }
  }

  // Protected Methods ------------------------------------------------------------------------------ Protected Methods


  // Private Methods ---------------------------------------------------------------------------------- Private Methods


  // main() method -------------------------------------------------------------------------------------- main() method


  public static void main(String[] args) throws InterruptedException {

    // open/read the application context file
    ctx = new ClassPathXmlApplicationContext("server-context.xml");

    ServerMain main = (ServerMain)ctx.getBean("serverMain");
    main.run();

    // close on exit
    ctx.close();
  }

  // Getters & Setters ------------------------------------------------------------------------------ Getters & Setters
}
