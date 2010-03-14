package ca.codepit.client;

import java.net.InetAddress;
import java.net.UnknownHostException;

import ca.codepit.common.WorkItem;

/**
 * Class to handle the actual work being done.
 *
 * @author Evan Jehu
 *         Date: Mar 13, 2010
 */
public class WorkItemHandler {

  // Constants ---------------------------------------------------------------------------------------------- Constants


  // Instance Variables ---------------------------------------------------------------------------- Instance Variables

  private String hostname;

  // Constructors ---------------------------------------------------------------------------------------- Constructors

  public WorkItemHandler() throws UnknownHostException {

    hostname = InetAddress.getLocalHost().getHostName();
  }

  // Public Methods ------------------------------------------------------------------------------------ Public Methods

  public WorkItem processQueueItem(WorkItem item) {

    item.setProcessingNode(hostname);

    System.out.println("processing: " + item);

    return item;
  }

  // Protected Methods ------------------------------------------------------------------------------ Protected Methods


  // Private Methods ---------------------------------------------------------------------------------- Private Methods


  // Getters & Setters ------------------------------------------------------------------------------ Getters & Setters
}
