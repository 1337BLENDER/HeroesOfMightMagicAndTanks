package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.resources.Messages_es;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MessageService {
    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public MessageService() {
    }

    /**
     * Find and return one message with given id
     *
     * @param id of message
     * @return message
     */

    public Message getById(int id) {
        return messageRepository.findOne(id);
    }


    /**
     * Find and return all message with given receiver and sender
     *
     * @param receiver of message
     * @param sender   of message
     * @return message
     */
    public Iterable<Message> getBySenderAndReceiver(String sender, String receiver) {
        return messageRepository.findAllBySenderAndReceiverOrderById(sender, receiver);
    }

    public List<Message> getChat(String user1, String user2) {
        List<Message> list1 = new ArrayList<>();
        List<Message> list2 = new ArrayList<>();
        List<Message> result = new ArrayList<>();
        int i=0,j=0;
        for (Message message : getBySenderAndReceiver(user1, user2)) {
            list1.add(message);
        }
        for (Message message : getBySenderAndReceiver(user2, user1)) {
            list2.add(message);
        }
        while(i<list1.size()&&j<list2.size()){
            if(list1.get(i).getId()<list2.get(j).getId()){
                result.add(list1.get(i));
                i++;
            }else {
                result.add(list2.get(j));
                j++;
            }
        }
        if(i<list1.size()){
            while(i<list1.size()){
                result.add(list1.get(i));
                i++;
            }
        }else {
            while(j<list2.size()){
                result.add(list2.get(j));
                j++;
            }
        }
        return result;
    }

    /**
     * Find and return all the messages
     *
     * @return Message
     */

    public Iterable<Message> getAll() {
        return messageRepository.findAll();
    }

    /**
     * save message if it's new or update if it exists
     *
     * @param Message need to be saved
     * @return saved message
     */

    public Message saveOrUpdate(Message Message) {
        return messageRepository.save(Message);
    }

    /**
     * remove message with given id
     *
     * @param id of the message
     */

    public void deleteById(int id) {
        messageRepository.delete(id);
    }

    /**
     * delete all message with given receiver and sender
     *
     * @param receiver of message
     * @param sender   of message
     */
    public void deleteBySenderAndReceiver(String sender, String receiver) {
        messageRepository.deleteAllBySenderAndReceiver(sender, receiver);
    }

    /**
     * Remove all the messages
     */

    public void deleteAll() {
        messageRepository.deleteAll();
    }

    /**
     * Remove given message
     *
     * @param message need to be removed
     */

    public void delete(Message message) {
        messageRepository.delete(message);
    }
}
