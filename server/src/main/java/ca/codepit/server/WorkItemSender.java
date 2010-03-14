package ca.codepit.server;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import ca.codepit.common.WorkItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * @author Evan Jehu
 *         Date: Mar 13, 2010
 */
public class WorkItemSender {

  // Constants ---------------------------------------------------------------------------------------------- Constants

  private transient static final Log log = LogFactory.getLog(WorkItemSender.class);

  // Instance Variables ---------------------------------------------------------------------------- Instance Variables

  private JmsTemplate template;

  private Destination replyToDestination;

  // Constructors ---------------------------------------------------------------------------------------- Constructors


  // Public Methods ------------------------------------------------------------------------------------ Public Methods

  public void send(final WorkItem item) {

    log.debug("sending work item: " + item);

    template.send(new MessageCreator() {

      public Message createMessage(Session session) throws JMSException {

        Message message = session.createObjectMessage(item);
        message.setJMSReplyTo(replyToDestination);
        return message;
      }
    });
  }

  // Protected Methods ------------------------------------------------------------------------------ Protected Methods


  // Private Methods ---------------------------------------------------------------------------------- Private Methods


  // Getters & Setters ------------------------------------------------------------------------------ Getters & Setters

  public void setReplyToDestination(Destination replyToDestination) {

    this.replyToDestination = replyToDestination;
  }

  public void setTemplate(JmsTemplate template) {

    this.template = template;
  }
}
