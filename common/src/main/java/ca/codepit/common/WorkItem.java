package ca.codepit.common;

import java.io.Serializable;

/**
 * @author Evan Jehu
 *         Date: Mar 13, 2010
 */
public class WorkItem implements Serializable {

  // Constants ---------------------------------------------------------------------------------------------- Constants

  private static final long serialVersionUID = 1L;

  // Instance Variables ---------------------------------------------------------------------------- Instance Variables

  private String itemId;

  private String processingNode;

  // Constructors ---------------------------------------------------------------------------------------- Constructors

  public WorkItem(String itemId) {

    this.itemId = itemId;
  }

  // Public Methods ------------------------------------------------------------------------------------ Public Methods

  @Override
  public String toString() {

    return "WorkItem{" +
        "itemId='" + itemId + '\'' +
        ", processingNode='" + processingNode + '\'' +
        '}';
  }

  // Protected Methods ------------------------------------------------------------------------------ Protected Methods


  // Private Methods ---------------------------------------------------------------------------------- Private Methods


  // Getters & Setters ------------------------------------------------------------------------------ Getters & Setters

  public String getProcessingNode() {

    return processingNode;
  }

  public void setProcessingNode(String processingNode) {

    this.processingNode = processingNode;
  }

  public String getItemId() {

    return itemId;
  }
}
