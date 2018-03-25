package hello;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<Message,Integer>{
    public Iterable<Message> findAllBySenderAndReceiverOrderById(String sender,String receiver);
    public void deleteAllBySenderAndReceiver(String sender,String receiver);
}
