package ca.codepit.client;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import ca.codepit.common.AdministrationMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * Class used to send client generated messages to the server.
 *
 * @author Evan Jehu
 *         Date: Mar 13, 2010
 */
public class ClientAdminMessageSender {

  // Constants ---------------------------------------------------------------------------------------------- Constants

  private transient static final Log log = LogFactory.getLog(ClientAdminMessageSender.class);

  // Instance Variables ---------------------------------------------------------------------------- Instance Variables

  /**
   * Spring template used to abstract the creation and sending of JMS messages.
   */
  private JmsTemplate template;

  // Constructors ---------------------------------------------------------------------------------------- Constructors


  // Public Methods ------------------------------------------------------------------------------------ Public Methods

  /**
   * Send method for sending messages from the client to the server, this can be used to centralize exception logging
   * etc.
   *
   * @param msg
   */
  public void send(final AdministrationMessage msg) {

    log.debug("sending message to admin server: " + msg);

    template.send(new MessageCreator() {

      public Message createMessage(Session session) throws JMSException {

        return session.createObjectMessage(msg);
      }
    });
  }

  // Protected Methods ------------------------------------------------------------------------------ Protected Methods


  // Private Methods ---------------------------------------------------------------------------------- Private Methods


  // Getters & Setters ------------------------------------------------------------------------------ Getters & Setters

  public void setTemplate(JmsTemplate template) {

    this.template = template;
  }
}
