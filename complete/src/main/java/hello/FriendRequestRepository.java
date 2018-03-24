package hello;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestRepository extends CrudRepository<FriendRequest,Integer> {
    Iterable<FriendRequest> findAllByReceiverAndCanceled(String receiver,Boolean canceled);
    Iterable<FriendRequest> findAllBySenderAndCanceled(String sender,Boolean canceled);
    Iterable<FriendRequest> findAllBySender(String sender);
    FriendRequest findFirstBySenderAndReceiverAndCanceled(String sender,String receiver,Boolean canceled);
    FriendRequest findFirstBySenderAndReceiver(String sender, String receiver);
    void deleteAllBySenderAndReceiver(String sender,String receiver);
}
