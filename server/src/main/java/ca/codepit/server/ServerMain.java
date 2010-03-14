package ca.codepit.server;

import ca.codepit.common.ClientAdminMessage;
import ca.codepit.common.WorkItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Evan Jehu
 *         Date: Mar 13, 2010
 */
public class ServerMain {

  // Constants ---------------------------------------------------------------------------------------------- Constants

  private static ClassPathXmlApplicationContext ctx;

  // Instance Variables ---------------------------------------------------------------------------- Instance Variables

  @Autowired
  private WorkItemSender ws;

  @Autowired
  private AdminMessageSender ams;

  // Constructors ---------------------------------------------------------------------------------------- Constructors

  public ServerMain() {
  }

  // Public Methods ------------------------------------------------------------------------------------ Public Methods

  public void receiveAdminMessage(ClientAdminMessage msg) {

    System.out.println("admin response received: " + msg);
  }

  public void receiveWorkConfirmMessage(WorkItem msg) {

    System.out.println("work item confirmation received: " + msg);
  }

  public void run() throws InterruptedException {

    System.out.println("starting server ...");

    int messageCount = 0;

    while(true) {
      Thread.sleep(500);
      messageCount++;
      ws.send(new WorkItem("msg: " + messageCount));
      if(messageCount % 10 == 0) {
        ams.send(new ClientAdminMessage("msg: " + messageCount, ClientAdminMessage.ACTION.PING));
      }
      if(messageCount % 25 == 0) {
        ams.send(new ClientAdminMessage("msg: " + messageCount, ClientAdminMessage.ACTION.EXIT));
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
