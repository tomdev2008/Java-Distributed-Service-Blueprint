package ca.codepit.client;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;

import ca.codepit.common.WorkItemMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Class to handle the actual work being done.
 *
 * @author Evan Jehu
 *         Date: Mar 13, 2010
 */
public class WorkItemHandler {

  // Constants ---------------------------------------------------------------------------------------------- Constants

  private transient static final Log log = LogFactory.getLog(WorkItemHandler.class);

  // Instance Variables ---------------------------------------------------------------------------- Instance Variables

  private String hostname;

  private Random random = new Random();

  // Constructors ---------------------------------------------------------------------------------------- Constructors

  public WorkItemHandler() throws UnknownHostException {

    hostname = InetAddress.getLocalHost().getHostName();
  }

  // Public Methods ------------------------------------------------------------------------------------ Public Methods

  public WorkItemMessage processQueueItem(WorkItemMessage item) {

    item.setProcessingNode(hostname);

    // ----------------------------------- SNIP -----------------------------------

    int timeToProcess = random.nextInt(1000);

    log.info("pausing " + timeToProcess + " milliseconds to process " + item);

    try {
      Thread.sleep(timeToProcess);
    } catch(InterruptedException e) {
      log.error("failed to pause for message processing, message was: " + item, e);
    }

    // ----------------------------------- SNIP -----------------------------------

    return item;
  }

  // Protected Methods ------------------------------------------------------------------------------ Protected Methods


  // Private Methods ---------------------------------------------------------------------------------- Private Methods


  // Getters & Setters ------------------------------------------------------------------------------ Getters & Setters
}
