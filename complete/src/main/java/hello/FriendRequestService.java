package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendRequestService {
    private FriendRequestRepository friendRequestRepository;

    @Autowired
    public FriendRequestService(FriendRequestRepository friendRequestRepository) {
        this.friendRequestRepository = friendRequestRepository;
    }

    public FriendRequestService() {
    }

    /**
     * Find and return one friendRequest with given id
     *
     * @param id of friendRequest
     * @return friendRequest
     */

    public FriendRequest getById(int id) {
        return friendRequestRepository.findOne(id);
    }

    /**
     * Find and return all friendRequest with given receiver
     *
     * @param receiver of friendRequest
     * @return friendRequest
     */
    public Iterable<FriendRequest> getByReceiverAndCanceled(String receiver,Boolean canceled){
        return friendRequestRepository.findAllByReceiverAndCanceled(receiver,canceled);
    }

    /**
     * Find and return all friendRequest with given sender
     *
     * @param sender of friendRequest
     * @return friendRequest
     */
    public Iterable<FriendRequest> getBySenderAndCanceled(String sender,Boolean canceled){
        return friendRequestRepository.findAllBySenderAndCanceled(sender,canceled);
    }

    /**
     * Find and return all friendRequest with given sender
     *
     * @param sender of friendRequest
     * @return friendRequest
     */
    public Iterable<FriendRequest> getBySender(String sender){
        return friendRequestRepository.findAllBySender(sender);
    }

    /**
     * Find and return all friendRequest with given receiver and sender
     *
     * @param receiver of friendRequest
     * @param sender of friendRequest
     * @return friendRequest
     */
    public FriendRequest getBySenderAndReceiverAndCanceled(String sender,String receiver,Boolean canceled){
        return friendRequestRepository.findFirstBySenderAndReceiverAndCanceled(sender,receiver,canceled);
    }

    public FriendRequest getBySenderAndReceiver(String sender,String receiver){
        return friendRequestRepository.findFirstBySenderAndReceiver(sender,receiver);
    }

    /**
     * Find and return all the friendRequests
     *
     * @return FriendRequest
     */

    public Iterable<FriendRequest> getAll() {
        return friendRequestRepository.findAll();
    }

    /**
     * save friendRequest if it's new or update if it exists
     *
     * @param FriendRequest need to be saved
     * @return saved friendRequest
     */

    public FriendRequest saveOrUpdate(FriendRequest FriendRequest) {
        return friendRequestRepository.save(FriendRequest);
    }

    /**
     * remove friendRequest with given id
     *
     * @param id of the friendRequest
     */

    public void deleteById(int id) {
        friendRequestRepository.delete(id);
    }

    /**
     * delete all friendRequest with given receiver and sender
     *
     * @param receiver of friendRequest
     * @param sender of friendRequest
     */
    public void deleteBySenderAndReceiver(String sender,String receiver){
        friendRequestRepository.deleteAllBySenderAndReceiver(sender,receiver);
    }

    /**
     * Remove all the friendRequests
     */

    public void deleteAll() {
        friendRequestRepository.deleteAll();
    }

    /**
     * Remove given friendRequest
     *
     * @param friendRequest need to be removed
     */

    public void delete(FriendRequest friendRequest) {
        friendRequestRepository.delete(friendRequest);
    }

}
