package ca.codepit.common;

import java.io.Serializable;

/**
 * @author Evan Jehu
 *         Date: Mar 13, 2010
 */
public class AdministrationMessage implements Serializable {

  // Constants ---------------------------------------------------------------------------------------------- Constants

  public static enum ACTION {PING, EXIT, CLIENT_ERROR}

  private static final long serialVersionUID = 1L;

  // Instance Variables ---------------------------------------------------------------------------- Instance Variables

  private String messageId;

  private String targetClient = ".*";

  private String respondingClient;

  private ACTION action = ACTION.PING;

  private boolean success = false;

  private Serializable payload = null;

  // Constructors ---------------------------------------------------------------------------------------- Constructors

  public AdministrationMessage(String messageId, ACTION action) {

    this(messageId, action, null);
  }

  public AdministrationMessage(String messageId, ACTION action, Serializable payload) {

    this.messageId = messageId;
    this.action = action;
    this.payload = payload;
  }

  // Public Methods ------------------------------------------------------------------------------------ Public Methods

  public boolean isActionable(String clientId) {

    return clientId.matches(targetClient);
  }

  @Override
  public String toString() {
    return "AdministrationMessage{" +
        "messageId='" + messageId + '\'' +
        ", targetClient='" + targetClient + '\'' +
        ", respondingClient='" + respondingClient + '\'' +
        ", action=" + action +
        ", success=" + success +
        ", payload=" + payload +
        '}';
  }

  // Protected Methods ------------------------------------------------------------------------------ Protected Methods


  // Private Methods ---------------------------------------------------------------------------------- Private Methods


  // Getters & Setters ------------------------------------------------------------------------------ Getters & Setters

  public ACTION getAction() {

    return action;
  }

  public String getMessageId() {

    return messageId;
  }

  public String getRespondingClient() {

    return respondingClient;
  }

  public void setRespondingClient(String respondingClient) {

    this.respondingClient = respondingClient;
  }

  public String getTargetClient() {

    return targetClient;
  }

  public void setTargetClient(String targetClient) {

    this.targetClient = targetClient;
  }

  public boolean isSuccess() {

    return success;
  }

  public void setSuccess(boolean success) {

    this.success = success;
  }

  public Serializable getPayload() {
    return payload;
  }

  public void setPayload(Serializable payload) {
    this.payload = payload;
  }
}
